package nz.co.yellow.spider.messaging.test;

import java.util.List;

import javax.ws.rs.core.MediaType;

import nz.co.yellow.spider.messaging.data.MessageReq;
import nz.co.yellow.spider.messaging.data.MessageResp;
import nz.co.yellow.spider.messaging.data.MessageThreadResp;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;

public class MessagingClientTest {

	private Client client;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MessagingClientTest.class);

	private final static String BASE_URL = "http://localhost:8082/yellow/rest/spider/messaging";

	@Before
	public void setUp() throws Exception {
		com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
		config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		client = Client.create(config);
		client.addFilter(new LoggingFilter(System.out));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetMessagesByThreadIdAndUserId() {
		WebResource webResource = client.resource(BASE_URL)
				.path("message/list").queryParam("threadId", "1")
				.queryParam("userId", "9c68");

		ClientResponse clientResp = webResource
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		Status statusCode = clientResp.getClientResponseStatus();
		LOGGER.debug("statusCode:{}", statusCode);

		if (statusCode.equals(Status.OK)) {
			List<MessageResp> result = clientResp.getEntity(List.class);
			LOGGER.debug("get result:{}", result);
		} else {
			String output = clientResp.getEntity(String.class);
			LOGGER.debug("response output:{}", output);
		}
	}

	@Test
	public void testGetThreadById() {
		WebResource webResource = client.resource(BASE_URL).path("thread/1");

		ClientResponse clientResp = webResource
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		Status statusCode = clientResp.getClientResponseStatus();
		LOGGER.debug("statusCode:{}", statusCode);

		if (statusCode.equals(Status.OK)) {
			MessageThreadResp result = clientResp
					.getEntity(MessageThreadResp.class);
			LOGGER.debug("get result:{}", result);
		} else {
			String output = clientResp.getEntity(String.class);
			LOGGER.debug("response output:{}", output);
		}
	}

	@Test
	public void testCreateThread() throws Exception {
		// MessageThreadReq messageThread =
		// MessageTestUtil.createCompundThread();
		WebResource webResource = client.resource(BASE_URL).path(
				"thread/create");

		String requestStr = "{\"subject\":\"Discussion of quote\",\"createdTime\":\"2013-09-25T13:52:22.854412\",\"status\":\"IN_PROGRESS\","
				+ "\"messages\":[{\"sentTime\":\"2013-09-25T15:43:21.854412\",\"content\":\"Sounds good, are you sure?\",\"status\":\"DRAFT\",\"creator\":{\"userId\":\"9c68\"}},"
				+ "{\"sentTime\":\"2013-09-25T15:51:21.854412\",\"content\":\"Hello\",\"status\":\"DRAFT\",\"creator\":{\"userId\":\"9c68\"}},"
				+ "{\"sentTime\":\"2013-09-25T15:55:41.854412\",\"content\":\"How about $75 for cheap spouting\",\"status\":\"DRAFT\",\"creator\":{\"userId\":\"d7d9\"}}]}";

		ClientResponse clientResp = webResource
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, requestStr);
		Status statusCode = clientResp.getClientResponseStatus();
		LOGGER.debug("statusCode:{}", statusCode);

		if (statusCode.equals(Status.OK)) {
			Long id = clientResp.getEntity(Long.class);
			LOGGER.debug("get result:{}", id);
		} else {
			String output = clientResp.getEntity(String.class);
			LOGGER.debug("response output:{}", output);
		}
	}

	@Test
	public void testCreateMessage() {
		MessageReq message = MessageTestUtil.createMessageFromNewCreator();
		// String reqStr =
		// "{\"sentTime\":\"2013-09-25T15:43:21.854412\",\"content\":\"Sounds good, are you sure?\",\"status\":\"DRAFT\",\"threadId\":1,\"creator\":{\"userId\":\"9c68\"}}";
		WebResource webResource = client.resource(BASE_URL).path(
				"message/create");

		ClientResponse clientResp = webResource
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, message);
		Status statusCode = clientResp.getClientResponseStatus();
		LOGGER.debug("statusCode:{}", statusCode);

		if (statusCode.equals(Status.OK)) {
			Long msgId = clientResp.getEntity(Long.class);
			LOGGER.debug("get msgId:{}", msgId);
		} else {
			String output = clientResp.getEntity(String.class);
			LOGGER.debug("response output:{}", output);
		}
	}
}
