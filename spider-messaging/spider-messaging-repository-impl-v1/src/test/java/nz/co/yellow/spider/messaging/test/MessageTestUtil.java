package nz.co.yellow.spider.messaging.test;

import static junit.framework.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import nz.co.yellow.spider.messaging.data.MessageReq;
import nz.co.yellow.spider.messaging.data.MessageThreadReq;
import nz.co.yellow.spider.messaging.data.MessageThreadResp;
import nz.co.yellow.spider.messaging.data.Participant;
import nz.co.yellow.spider.messaging.data.ThreadModel;

public class MessageTestUtil {

	public static void assertThread(MessageThreadResp dto, ThreadModel model) {
		assertEquals(dto.getMessageThreadId(), model.getThreadId());
		assertEquals(dto.getSubject(), model.getTitle());
		assertEquals(dto.getCreatedTime(), model.getCreatedTime());
		assertEquals(dto.getStatus(), model.getStatus());
	}

	public static MessageThreadReq createCompundThread() throws Exception {
		MessageThreadReq thread = MessageThreadReq.getBuilder(
				"Discussion of quote", "2013-09-25T13:52:22.854412",
				"IN_PROGRESS", createMessages()).build();
		return thread;
	}

	public static List<MessageReq> createMessages() {
		// 2 messages from existed participant (9c68)
		// 1 message from new participant (d7d9)
		List<MessageReq> messages = new ArrayList<MessageReq>();
		Participant participant = Participant.getBuilder("9c68").build();
		MessageReq message = MessageReq.getBuilder(
				"2013-09-25T15:43:21.854412", null,
				"Sounds good, are you sure?", "DRAFT", participant).build();
		messages.add(message);
		message = MessageReq.getBuilder("2013-09-25T15:51:21.854412", null,
				"Hello", "DRAFT", participant).build();
		messages.add(message);

		participant = Participant.getBuilder("d7d9").build();
		message = MessageReq.getBuilder("2013-09-25T15:55:41.854412", null,
				"How about $75 for cheap spouting", "DRAFT", participant)
				.build();
		messages.add(message);
		return messages;
	}

	public static MessageReq createMessageFromExistedCreator() {
		Participant participant = Participant.getBuilder("9c68").build();
		MessageReq message = MessageReq.getBuilder(1L,
				"2013-09-25T15:43:21.854412", "Sounds good, are you sure?",
				"DRAFT", participant).build();
		return message;
	}
}
