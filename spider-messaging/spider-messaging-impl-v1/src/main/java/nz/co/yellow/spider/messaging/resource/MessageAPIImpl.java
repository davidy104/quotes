package nz.co.yellow.spider.messaging.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import nz.co.yellow.spider.messaging.GenericAPIError;
import nz.co.yellow.spider.messaging.MessagingAPIUtils;
import nz.co.yellow.spider.messaging.ValidationException;
import nz.co.yellow.spider.messaging.data.Message;
import nz.co.yellow.spider.messaging.data.MessageReq;
import nz.co.yellow.spider.messaging.data.MessageResp;
import nz.co.yellow.spider.messaging.data.MessageThreadReq;
import nz.co.yellow.spider.messaging.data.MessageThreadResp;
import nz.co.yellow.spider.messaging.data.ThreadMsgLoadStrategies;
import nz.co.yellow.spider.messaging.ds.MessageDS;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("messageAPI")
@Path("/spider/messaging")
public class MessageAPIImpl implements MessageAPI {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MessageAPIImpl.class);

	@Autowired
	private MessageDS messageDs;

	@Override
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/thread/create")
	public Response createMessageThread(MessageThreadReq messageThread) {
		LOGGER.debug("createMessageThread start:{}");
		Long id = null;
		MessageThreadResp result = null;
		GenericAPIError genericAPIError = null;
		try {
			result = messageDs.createMsgThread(messageThread);
			id = result.getMessageThreadId();
		} catch (Exception e) {
			genericAPIError = MessagingAPIUtils.errorHandle(e);
		}

		LOGGER.debug("createMessageThread end:{}");
		return MessagingAPIUtils.buildResponse(id, genericAPIError);
	}

	@Override
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/message/create")
	public Response createMessage(MessageReq message) {
		LOGGER.debug("createMessage start:{}");
		Long msgId = null;
		GenericAPIError genericAPIError = null;
		try {
			if (message.getCreator() == null) {
				throw new ValidationException("Message creator can not be null");
			} else if (message.getThreadId() == null) {
				throw new ValidationException("ThreadId can not be null");
			} else {
				MessageResp msgResp = messageDs.addMessageToThread(message);
				msgId = msgResp.getMessageId();
			}
		} catch (Exception e) {
			genericAPIError = MessagingAPIUtils.errorHandle(e);
		}

		LOGGER.debug("createMessage end:{}");
		return MessagingAPIUtils.buildResponse(msgId, genericAPIError);
	}

	@Override
	@GET
	@Produces("application/json")
	@Path("/thread/{threadId}")
	public Response getMessageThreadById(
			@PathParam("threadId") Long messageThreadId) {
		LOGGER.debug("getMessageThreadById start:{}");
		MessageThreadResp messageThread = null;
		GenericAPIError genericAPIError = null;
		try {
			messageThread = messageDs.getMsgThreadById(messageThreadId,
					ThreadMsgLoadStrategies.ALL);

		} catch (Exception e) {
			genericAPIError = MessagingAPIUtils.errorHandle(e);
		}

		LOGGER.debug("getMessageThreadById end:{}", messageThread);
		return MessagingAPIUtils.buildResponse(messageThread, genericAPIError);
	}

	@Override
	@GET
	@Produces("application/json")
	@Path("/message/list")
	public Response getMessagesByThreadAndUser(
			@QueryParam("threadId") Long threadId,
			@QueryParam("userId") String userId) {
		LOGGER.debug("getMessagesByThreadAndUser start:{}");
		List<MessageResp> messages = null;
		GenericAPIError genericAPIError = null;

		try {
			messages = messageDs.getMessageByThreadIdAndParticipantUserId(
					threadId, userId);
		} catch (Exception e) {
			genericAPIError = MessagingAPIUtils.errorHandle(e);
		}

		LOGGER.debug("createMessage end:{}");
		return MessagingAPIUtils.buildResponse(messages, genericAPIError);
	}

	@Override
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/message/update/{messageId}")
	public Response updateMessage(@PathParam("messageId") Long messageId,
			MessageReq message) {
		LOGGER.debug("updateMessage start:{}");
		Message updatedMessage = null;
		GenericAPIError genericAPIError = null;
		String readTime = message.getReadTime();
		String status = message.getStatus();
		try {
			if (StringUtils.isEmpty(readTime) || StringUtils.isEmpty(status)) {
				throw new ValidationException(
						"Status or readTime can not be null for Message update");
			} else {
				updatedMessage = messageDs.updateMessageReadTimeAndStatus(
						messageId, readTime, status);
			}
		} catch (Exception e) {
			genericAPIError = MessagingAPIUtils.errorHandle(e);
		}
		LOGGER.debug("updateMessage end:{}");
		return MessagingAPIUtils.buildResponse(updatedMessage, genericAPIError);
	}

	@Override
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/thread/update/{threadId}")
	public Response updateThreadStatus(@PathParam("messageId") Long threadId,
			MessageThreadReq messageThread) {
		LOGGER.debug("updateThreadStatus start:{}");
		MessageThreadResp updatedMessageThread = null;
		GenericAPIError genericAPIError = null;
		String status = messageThread.getStatus();
		try {
			if (StringUtils.isEmpty(status)) {
				throw new ValidationException(
						"Status can not be null for MessageThread update");
			} else {
				updatedMessageThread = this.messageDs
						.updateMessageThreadStatus(threadId, status);
			}
		} catch (Exception e) {
			genericAPIError = MessagingAPIUtils.errorHandle(e);
		}
		LOGGER.debug("updateThreadStatus end:{}");
		return MessagingAPIUtils.buildResponse(updatedMessageThread,
				genericAPIError);
	}

}
