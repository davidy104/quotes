package nz.co.yellow.spider.messaging.resource;

import javax.ws.rs.core.Response;

import nz.co.yellow.spider.messaging.data.MessageReq;
import nz.co.yellow.spider.messaging.data.MessageThreadReq;

public interface MessageAPI {

	Response createMessageThread(MessageThreadReq messageThread);

	Response createMessage(MessageReq message);

	Response getMessageThreadById(Long messageThreadId);

	Response getMessagesByThreadAndUser(Long threadId, String userId);

	Response updateMessage(Long messageId, MessageReq message);

	Response updateThreadStatus(Long threadId, MessageThreadReq messageThread);

}
