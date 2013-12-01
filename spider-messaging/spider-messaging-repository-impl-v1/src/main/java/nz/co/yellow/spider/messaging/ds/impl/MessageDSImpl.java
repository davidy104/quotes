package nz.co.yellow.spider.messaging.ds.impl;

import static nz.co.yellow.spider.messaging.data.predicate.MessagePredicates.findByMessageThreadAndParticipant;
import static nz.co.yellow.spider.messaging.data.predicate.ParticipantPredicates.findByUserId;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import nz.co.yellow.spider.messaging.data.MessageModel;
import nz.co.yellow.spider.messaging.data.MessageReq;
import nz.co.yellow.spider.messaging.data.MessageResp;
import nz.co.yellow.spider.messaging.data.MessageThreadReq;
import nz.co.yellow.spider.messaging.data.MessageThreadResp;
import nz.co.yellow.spider.messaging.data.Participant;
import nz.co.yellow.spider.messaging.data.ParticipantModel;
import nz.co.yellow.spider.messaging.data.ThreadModel;
import nz.co.yellow.spider.messaging.data.ThreadMsgLoadStrategies;
import nz.co.yellow.spider.messaging.data.ThreadParticipantModel;
import nz.co.yellow.spider.messaging.data.repository.MessageRepository;
import nz.co.yellow.spider.messaging.data.repository.ParticipantRepository;
import nz.co.yellow.spider.messaging.data.repository.ThreadParticipantRepository;
import nz.co.yellow.spider.messaging.data.repository.ThreadRepository;
import nz.co.yellow.spider.messaging.ds.GeneralConverter.ServiceOperation;
import nz.co.yellow.spider.messaging.ds.MessageDS;
import nz.co.yellow.spider.messaging.ds.NotFoundException;
import nz.co.yellow.spider.messaging.ds.converter.MessageConverter;
import nz.co.yellow.spider.messaging.ds.converter.ParticipantConverter;
import nz.co.yellow.spider.messaging.ds.converter.ThreadConverter;
import nz.co.yellow.spider.messaging.util.MessageUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * Domain service for Messaging
 * @author david
 *
 */
@Service("messageDs")
@Transactional(readOnly = true)
public class MessageDSImpl implements MessageDS {
	@Resource
	private ThreadRepository threadRepository;

	@Resource
	private ParticipantRepository participantRepository;

	@Resource
	private MessageRepository messageRepository;

	@Resource
	private ThreadParticipantRepository threadParticipantRepository;

	@Resource
	private ThreadConverter threadConverter;

	@Resource
	private ParticipantConverter participantConverter;

	@Resource
	private MessageConverter messageConverter;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MessageDSImpl.class);

	@Override
	@Transactional(readOnly = false)
	public MessageThreadResp createMsgThread(MessageThreadReq request)
			throws Exception {
		LOGGER.debug("createMsgThread start:{}", request);
		MessageThreadResp result = null;
		ParticipantModel participantModel = null;
		ThreadParticipantModel threadParticipantModel = null;
		MessageModel messageModel = null;

		ThreadModel msgThreadModel = threadConverter.convertFromRequest(
				request, ServiceOperation.CREATE);
		List<MessageReq> messages = request.getMessages();

		if (messages != null && messages.size() > 0) {
			for (MessageReq message : messages) {
				messageModel = messageConverter.convertFromRequest(message,
						ServiceOperation.QUERY);
				Participant participant = message.getCreator();

				participantModel = this.getParticipantByUserId(participant
						.getUserId());

				if (participantModel == null) {
					// participant not existed, create and then build relation
					// with Thread
					participantModel = participantConverter.convertFrom(
							participant, ServiceOperation.QUERY);
					participantModel.addMessage(messageModel);
					participantModel = participantRepository
							.save(participantModel);

					threadParticipantModel = ThreadParticipantModel.getBuilder(
							msgThreadModel, participantModel).build();

				} else if (!this.ifParticipantHasRelationWithThread(
						participantModel, msgThreadModel)) {
					// if existed has not relation with Thread, than build
					// it
					threadParticipantModel = ThreadParticipantModel.getBuilder(
							msgThreadModel, participantModel).build();
				}
				messageModel.setCreatedBy(participantModel);
				messageModel.setThread(msgThreadModel);
				if (threadParticipantModel != null) {
					msgThreadModel
							.addThreadParticipantModel(threadParticipantModel);
				}
				msgThreadModel.addMessage(messageModel);
			}
		}

		threadRepository.save(msgThreadModel);
		result = threadConverter.convertToResponse(msgThreadModel,
				ServiceOperation.QUERY, ThreadMsgLoadStrategies.NONE);
		LOGGER.debug("createMsgThread end:{}", result);
		return result;
	}

	private ParticipantModel getParticipantByUserId(String userId) {
		LOGGER.debug("userId:{}", userId);
		return participantRepository.findOne(findByUserId(userId));
	}

	@Override
	@Transactional(readOnly = false)
	public MessageResp addMessageToThread(MessageReq msgReq) throws Exception {
		LOGGER.debug("addMessageToThread start:{}", msgReq);
		MessageResp result = null;
		MessageModel msgModel = null;
		ParticipantModel participantModel = null;
		ThreadParticipantModel threadParticipant = null;
		Long threadId = msgReq.getThreadId();
		// check if MsgThread existed as per id
		ThreadModel threadModel = threadRepository.findOne(threadId);
		if (threadModel == null) {
			throw new NotFoundException("No ThreadModel found with id: "
					+ threadId);
		}

		// persistent messageModel
		msgModel = messageConverter.convertFromRequest(msgReq,
				ServiceOperation.CREATE);
		Participant participantDto = msgReq.getCreator();

		// check if participant already existed
		participantModel = this.getParticipantByUserId(participantDto
				.getUserId());

		if (participantModel == null) {
			// for new participant
			participantModel = participantConverter.convertFrom(participantDto,
					ServiceOperation.CREATE);
			participantModel.addMessage(msgModel);
			participantRepository.save(participantModel);
			threadParticipant = ThreadParticipantModel.getBuilder(threadModel,
					participantModel).build();

		} else {
			LOGGER.debug("found existed participantModel:{}", participantModel);
			if (!this.ifParticipantHasRelationWithThread(participantModel,
					threadModel)) {
				threadParticipant = ThreadParticipantModel.getBuilder(
						threadModel, participantModel).build();
			}
		}
		msgModel.setCreatedBy(participantModel);
		msgModel.setThread(threadModel);
		if (threadParticipant != null) {
			threadModel.addThreadParticipantModel(threadParticipant);
		}
		messageRepository.save(msgModel);
		LOGGER.debug("messageModel saved:{}", msgModel);

		result = this.messageConverter.convertToResponse(msgModel,
				ServiceOperation.QUERY);
		return result;
	}

	@Override
	public MessageThreadResp getMsgThreadById(Long threadId,
			ThreadMsgLoadStrategies loadStrategies) throws Exception {
		LOGGER.debug("getMsgThreadById start:{}", threadId);
		LOGGER.debug("load strategies:{}", loadStrategies);
		loadStrategies = loadStrategies == null ? ThreadMsgLoadStrategies.NONE
				: loadStrategies;
		MessageThreadResp thread = null;
		ThreadModel msgThreadModel = threadRepository.findOne(threadId);
		if (msgThreadModel == null) {
			throw new NotFoundException(
					"No SpiderMsgThreadModel found with id: " + threadId);
		}
		LOGGER.debug("get SpiderMsgThreadModel:{}", msgThreadModel);
		thread = threadConverter.convertToResponse(msgThreadModel,
				ServiceOperation.QUERY, loadStrategies);
		LOGGER.debug("getMsgThreadById end:{}", thread);
		return thread;
	}

	@Override
	@Transactional(readOnly = false)
	public MessageResp updateMessageReadTimeAndStatus(Long messageId,
			String readTime, String status) throws Exception {
		LOGGER.debug("updateMessageReadTime start:{}", messageId);
		LOGGER.debug("readTime:{}", readTime);
		MessageResp result = null;
		MessageModel foundModel = this.messageRepository.findOne(messageId);
		if (foundModel == null) {
			throw new NotFoundException("Message not found with id["
					+ messageId + "]");
		}
		if (!StringUtils.isEmpty(readTime)) {
			foundModel.setReadTime(MessageUtils.strToDate(readTime));
		}
		if (!StringUtils.isEmpty(status)) {
			foundModel.setStatus(status);
		}
		result = this.messageConverter.convertToResponse(foundModel,
				ServiceOperation.QUERY);
		LOGGER.debug("updateMessageReadTime end:{}", result);
		return result;
	}

	@Override
	public List<MessageResp> getMessageByThreadIdAndParticipantUserId(
			Long messageThreadId, String userId) throws Exception {
		LOGGER.debug("getMessageByThreadIdAndParticipant start:{}",
				messageThreadId);
		LOGGER.debug("userId:{}", userId);
		List<MessageResp> resultList = null;
		ThreadModel msgThreadModel = threadRepository.findOne(messageThreadId);
		if (msgThreadModel == null) {
			throw new NotFoundException("ThreadModel not found with id: "
					+ messageThreadId);
		}
		LOGGER.debug("get ThreadModel:{}", msgThreadModel);

		if (this.getParticipantByUserId(userId) == null) {
			throw new NotFoundException(
					"ParticipantModel not found with userId: " + userId);
		}

		Iterable<MessageModel> iterable = this.messageRepository
				.findAll(findByMessageThreadAndParticipant(messageThreadId,
						userId));
		if (iterable != null) {
			Iterator<MessageModel> iterator = iterable.iterator();
			resultList = new ArrayList<MessageResp>();
			while (iterator.hasNext()) {
				resultList.add(this.messageConverter.convertToResponse(
						iterator.next(), ServiceOperation.QUERY));
			}
		}

		LOGGER.debug("getMessageByThreadIdAndParticipant end:{}");
		return resultList;
	}

	@Override
	@Transactional(readOnly = false)
	public MessageThreadResp updateMessageThreadStatus(Long threadId,
			String status) throws Exception {
		LOGGER.debug("updateMessageThreadStatus start:{}", threadId);
		MessageThreadResp messageThread = null;
		ThreadModel msgThreadModel = null;

		msgThreadModel = this.threadRepository.findOne(threadId);

		if (msgThreadModel == null) {
			throw new NotFoundException("MessageThread not found with id("
					+ threadId + ")");
		}

		msgThreadModel.setStatus(status);
		messageThread = threadConverter.convertToResponse(msgThreadModel,
				ServiceOperation.QUERY);
		LOGGER.debug("updateMessageThreadStatus end:{}");
		return messageThread;
	}

	private boolean ifParticipantHasRelationWithThread(
			ParticipantModel participantModel, ThreadModel threadModel) {
		List<ThreadParticipantModel> threadParticipantList = threadModel
				.getTheadParticipants();
		if (threadParticipantList == null || threadParticipantList.size() == 0) {
			return false;
		}

		for (ThreadParticipantModel threadParticipantModel : threadParticipantList) {
			ParticipantModel existedParticipant = threadParticipantModel
					.getParticipantModel();
			if (existedParticipant != null
					&& existedParticipant.getParticipantId() == participantModel
							.getParticipantId()) {
				return true;
			}
		}
		return false;
	}

}
