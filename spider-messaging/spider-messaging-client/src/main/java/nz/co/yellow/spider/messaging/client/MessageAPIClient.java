package nz.co.yellow.spider.messaging.client;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;

import nz.co.yellow.spider.messaging.data.MessageThreadReq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;

@Component
public class MessageAPIClient {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MessageAPIClient.class);

	private Client client;

	@Value("${MessageAPIClientURIConfig.basicURI:http://localhost:8082/yellow/rest/spider/messaging")
	private String basicURI;

	@PostConstruct
	private void init() {
		com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
		config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		client = Client.create(config);
		client.addFilter(new LoggingFilter(System.out));
	}

	public Long createMessageThread(MessageThreadReq messageThread)
			throws Exception {
		LOGGER.debug("createMessageThread start:{}", messageThread);
		Long id = null;
		WebResource webResource = client.resource(basicURI).path(
				"thread/create");

		ClientResponse clientResp = webResource
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, messageThread);

		Status statusCode = clientResp.getClientResponseStatus();
		LOGGER.debug("statusCode:{}", statusCode);

		if (statusCode.equals(Status.OK)) {
			id = clientResp.getEntity(Long.class);
		} else {
			String message = clientResp.getEntity(String.class);
			LOGGER.debug("message:{}", message);
			throw new Exception("createMessageThread error:" + message);
		}
		LOGGER.debug("createMessageThread end:{}");
		return id;
	}
}
