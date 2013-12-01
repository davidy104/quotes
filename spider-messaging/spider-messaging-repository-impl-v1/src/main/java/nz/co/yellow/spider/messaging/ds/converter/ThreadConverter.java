package nz.co.yellow.spider.messaging.ds.converter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nz.co.yellow.spider.messaging.data.MessageModel;
import nz.co.yellow.spider.messaging.data.MessageResp;
import nz.co.yellow.spider.messaging.data.MessageThreadReq;
import nz.co.yellow.spider.messaging.data.MessageThreadResp;
import nz.co.yellow.spider.messaging.data.Participant;
import nz.co.yellow.spider.messaging.data.ParticipantModel;
import nz.co.yellow.spider.messaging.data.ThreadModel;
import nz.co.yellow.spider.messaging.data.ThreadMsgLoadStrategies;
import nz.co.yellow.spider.messaging.data.ThreadParticipantModel;
import nz.co.yellow.spider.messaging.ds.APIConverter;
import nz.co.yellow.spider.messaging.ds.ConvertException;
import nz.co.yellow.spider.messaging.ds.GeneralConverter.ServiceOperation;
import nz.co.yellow.spider.messaging.util.MessageUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("threadConverter")
public class ThreadConverter implements
		APIConverter<MessageThreadReq, ThreadModel, MessageThreadResp> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ThreadConverter.class);

	@Resource
	private MessageConverter messageConverter;

	@Resource
	private ParticipantConverter participantConverter;

	@Override
	public MessageThreadResp convertToResponse(ThreadModel model,
			ServiceOperation operation, Object... additionalSourceObj)
			throws ConvertException {
		LOGGER.debug("convert to dto start:{}", model);
		ThreadMsgLoadStrategies loadStrategies = ThreadMsgLoadStrategies.ALL;
		MessageThreadResp threadDto = MessageThreadResp.getBuilder(
				model.getTitle(), model.getStatus()).build();
		threadDto.setMessageThreadId(model.getThreadId());

		if (model.getCreatedTime() != null) {
			threadDto.setCreatedTime(MessageUtils.dateToStr(model
					.getCreatedTime()));
		}

		if (additionalSourceObj != null && additionalSourceObj.length > 0) {
			loadStrategies = (ThreadMsgLoadStrategies) additionalSourceObj[0];
		}

		if (loadStrategies == ThreadMsgLoadStrategies.ALL) {
			this.loadMessages(model, threadDto, operation);
			this.loadParticipants(model, threadDto, operation);
		} else if (loadStrategies == ThreadMsgLoadStrategies.LOAD_MESSAGE) {
			this.loadMessages(model, threadDto, operation);
		} else if (loadStrategies == ThreadMsgLoadStrategies.LOAD_PARTICIPANT) {
			this.loadParticipants(model, threadDto, operation);
		}

		LOGGER.debug("after convertTo DTO:{}", threadDto);
		return threadDto;
	}

	@Override
	public ThreadModel convertFromRequest(MessageThreadReq request,
			ServiceOperation operation, Object... additionalSourceObj)
			throws ConvertException {
		LOGGER.debug("convert to model start:{}", request);
		ThreadModel model = null;
		try {
			model = ThreadModel.getBuilder(request.getSubject(),
					request.getStatus()).build();

			if (!StringUtils.isEmpty(request.getCreatedTime())) {
				model.setCreatedTime(MessageUtils.strToDate(request
						.getCreatedTime()));
			}

		} catch (Exception e) {
			throw new ConvertException("MessageThread covert to model error", e);
		}
		LOGGER.debug("after convert:{}", model);
		return model;
	}

	private void loadMessages(ThreadModel threadModel,
			MessageThreadResp threadDto, ServiceOperation operation)
			throws ConvertException {
		List<MessageModel> messageModels = threadModel.getMessages();
		List<MessageResp> messages = null;
		if (messageModels != null && messageModels.size() > 0) {
			messages = new ArrayList<MessageResp>();
			for (MessageModel messageModel : messageModels) {
				messages.add(this.messageConverter.convertToResponse(
						messageModel, operation));
			}
			threadDto.setMessages(messages);
		}
	}

	private void loadParticipants(ThreadModel threadModel,
			MessageThreadResp threadDto, ServiceOperation operation)
			throws ConvertException {
		List<ThreadParticipantModel> threadParticipantModels = threadModel
				.getTheadParticipants();
		List<Participant> participants = null;
		if (threadParticipantModels != null
				&& threadParticipantModels.size() > 0) {
			participants = new ArrayList<Participant>();
			for (ThreadParticipantModel threadParticipantModel : threadParticipantModels) {
				ParticipantModel participantModel = threadParticipantModel
						.getParticipantModel();
				participants.add(this.participantConverter.convertTo(
						participantModel, operation));
			}
			threadDto.setParticipants(participants);
		}
	}

}
