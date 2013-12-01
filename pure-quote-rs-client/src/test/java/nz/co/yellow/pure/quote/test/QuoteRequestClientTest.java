package nz.co.yellow.pure.quote.test;

import java.util.List;

import javax.ws.rs.core.MediaType;

import nz.co.yellow.pure.quote.data.QuoteRequestReq;
import nz.co.yellow.pure.quote.data.QuoteRequestResp;
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

public class QuoteRequestClientTest {

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
	public void testAddProviderQuotesToQuoteRequest(){
		List<ServiceProviderQuote> providerQuotes = QuotesTestUtils.createServiceProviderQuotes();
		WebResource webResource = client.resource(BASE_URL)
				.path("quoteRequest/addProviderQuotes/2");

		ClientResponse clientResp = webResource
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class, providerQuotes);
		Status statusCode = clientResp.getClientResponseStatus();
		LOGGER.debug("statusCode:{}", statusCode);

		if (statusCode.equals(Status.OK)) {
			Long id = clientResp.getEntity(Long.class);
			LOGGER.debug("get id:{}", id);
		} else {
			String output = clientResp.getEntity(String.class);
			LOGGER.debug("response output:{}", output);
		}

		//get by id
		webResource = client.resource(BASE_URL).path(
				"quoteRequest/2");

		clientResp = webResource
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		statusCode = clientResp.getClientResponseStatus();
		LOGGER.debug("statusCode:{}", statusCode);

		if (statusCode.equals(Status.OK)) {
			QuoteRequestResp result = clientResp
					.getEntity(QuoteRequestResp.class);
			LOGGER.debug("get result:{}", result);
		} else {
			String output = clientResp.getEntity(String.class);
			LOGGER.debug("response output:{}", output);
		}
	}

	@Test
	public void testCreate() {
		QuoteRequestReq quoteRequestReq = QuotesTestUtils
				.createQuoteRequestReq();
		// String reqStr =
		// "{\"systemName\":\"general_nature_11\",\"wording\":\"Please indicate the general nature of your inquiry\","
		// +
		// "\"dataType\":\"SimpleSelection\",\"parameters\":\"{\"field_choices\": [[\"Plumbing\", \"Plumbing\"], [\"Spouting\",\"Spouting\"], [\"Gas\", \"Gas\"], [\"Other\", \"Other\"]]}\",\"ordinal\":0,\"category\":1311}";
		WebResource webResource = client.resource(BASE_URL)
				.path("quoteRequest");

		ClientResponse clientResp = webResource
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, quoteRequestReq);
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

	@Test
	public void testGetById() {
		WebResource webResource = client.resource(BASE_URL).path(
				"quoteRequest/1");

		ClientResponse clientResp = webResource
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		Status statusCode = clientResp.getClientResponseStatus();
		LOGGER.debug("statusCode:{}", statusCode);

		if (statusCode.equals(Status.OK)) {
			QuoteRequestResp result = clientResp
					.getEntity(QuoteRequestResp.class);
			LOGGER.debug("get result:{}", result);
		} else {
			String output = clientResp.getEntity(String.class);
			LOGGER.debug("response output:{}", output);
		}
	}

	@Test
	public void testUpdateStatus(){
		QuoteRequestReq quoteRequestReq = new QuoteRequestReq();
		quoteRequestReq.setStatus("IGNORE");
		WebResource webResource = client.resource(BASE_URL)
				.path("quoteRequest/updateStatus/1");

		ClientResponse clientResp = webResource
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class, quoteRequestReq);
		Status statusCode = clientResp.getClientResponseStatus();
		LOGGER.debug("statusCode:{}", statusCode);

		if (statusCode.equals(Status.OK)) {
			Long id = clientResp.getEntity(Long.class);
			LOGGER.debug("get id:{}", id);
		} else {
			String output = clientResp.getEntity(String.class);
			LOGGER.debug("response output:{}", output);
		}

		//get by id
		webResource = client.resource(BASE_URL).path(
				"quoteRequest/1");

		clientResp = webResource
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		statusCode = clientResp.getClientResponseStatus();
		LOGGER.debug("statusCode:{}", statusCode);

		if (statusCode.equals(Status.OK)) {
			QuoteRequestResp result = clientResp
					.getEntity(QuoteRequestResp.class);
			LOGGER.debug("get result:{}", result);
		} else {
			String output = clientResp.getEntity(String.class);
			LOGGER.debug("response output:{}", output);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllProviderQuotesByQuoteId(){
		WebResource webResource = client.resource(BASE_URL).path(
				"quoteRequest/listProviderQuotes/1");

		ClientResponse clientResp = webResource
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		Status statusCode = clientResp.getClientResponseStatus();
		LOGGER.debug("statusCode:{}", statusCode);

		if (statusCode.equals(Status.OK)) {
			List<ServiceProviderQuote> result = clientResp
					.getEntity(List.class);
			LOGGER.debug("get result:{}", result);
		} else {
			String output = clientResp.getEntity(String.class);
			LOGGER.debug("response output:{}", output);
		}
	}

}
