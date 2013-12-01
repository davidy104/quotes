package nz.co.yellow.pure.quote.test;

import javax.ws.rs.core.MediaType;

import nz.co.yellow.pure.quote.data.ServiceProviderQuote;

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

public class ServiceProviderQuoteClientTest {
	private Client client;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CategoryQuestionClientTest.class);

	public final static String BASE_URL = "http://localhost:8083/yellow/rest/pure/quote";

	@Before
	public void setUp() throws Exception {
		com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
		config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		client = Client.create(config);
		client.addFilter(new LoggingFilter(System.out));
	}

	@Test
	public void testUptStatus() {
		ServiceProviderQuote serviceProviderQuote = new ServiceProviderQuote();
		serviceProviderQuote.setStatus("DECLINE");
		String reqStr = "{\"status\":\"DECLINE\"}";
		WebResource webResource = client.resource(BASE_URL).path(
				"serviceProviderQuote/1");

		ClientResponse clientResp = webResource
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class, reqStr);

		Status statusCode = clientResp.getClientResponseStatus();
		LOGGER.debug("statusCode:{}", statusCode);

		if (statusCode.equals(Status.OK)) {
			Long id = clientResp.getEntity(Long.class);
			LOGGER.debug("get id:{}", id);
		} else {
			String output = clientResp.getEntity(String.class);
			LOGGER.debug("response output:{}", output);
		}
	}

}
