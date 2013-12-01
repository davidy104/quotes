package nz.co.yellow.spider.messaging.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import nz.co.yellow.spider.messaging.config.InfrastructureContextConfiguration;
import nz.co.yellow.spider.messaging.config.TestDataContextConfiguration;
import nz.co.yellow.spider.messaging.data.MessageResp;
import nz.co.yellow.spider.messaging.data.MessageThreadReq;
import nz.co.yellow.spider.messaging.data.MessageThreadResp;
import nz.co.yellow.spider.messaging.data.ThreadMsgLoadStrategies;
import nz.co.yellow.spider.messaging.ds.MessageDS;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { InfrastructureContextConfiguration.class,
		TestDataContextConfiguration.class })
public class MessageDSTest {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MessageDSTest.class);

	@Autowired
	private MessageDS messageDs;

	@Test
	public void testGetThreadById() throws Exception {
		MessageThreadResp messageThread = messageDs.getMsgThreadById(1L,
				ThreadMsgLoadStrategies.ALL);
		assertNotNull(messageThread);
		LOGGER.debug("messageThread:{}", messageThread);
	}

	@Test
	public void testAddMessageToThread() throws Exception {
		MessageResp msgResp = messageDs.addMessageToThread(MessageTestUtil
				.createMessageFromExistedCreator());
		assertNotNull(msgResp);
		LOGGER.debug("after add message:{}", msgResp);
	}

	@Test
	public void testUpdateMessage() throws Exception {
		MessageResp updated = messageDs.updateMessageReadTimeAndStatus(1L,
				"2013-10-24T13:54:22.152265", "READ");
		assertNotNull(updated);
		LOGGER.debug("after update message, message:{}", updated);
	}

	@Test
	public void getAllMessagesByThreadIdAndUserId() throws Exception {
		List<MessageResp> messageList = messageDs
				.getMessageByThreadIdAndParticipantUserId(1L, "84f8");
		assertNotNull(messageList);
		for (MessageResp message : messageList) {
			LOGGER.debug("Message:{}", message);
		}
	}

	@Test
	public void createThread() throws Exception {
		MessageThreadReq messageThread = MessageTestUtil.createCompundThread();
		MessageThreadResp messageThreadResp = messageDs
				.createMsgThread(messageThread);
		assertNotNull(messageThreadResp);
		LOGGER.debug("after create MessageThread:{}", messageThreadResp);

		messageThreadResp = messageDs.getMsgThreadById(
				messageThreadResp.getMessageThreadId(),
				ThreadMsgLoadStrategies.ALL);
		assertNotNull(messageThreadResp);
		LOGGER.debug("load all, messageThread:{}", messageThreadResp);
	}

}
