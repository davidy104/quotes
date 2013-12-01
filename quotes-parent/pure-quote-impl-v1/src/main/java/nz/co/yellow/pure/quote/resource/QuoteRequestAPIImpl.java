package nz.co.yellow.pure.quote.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nz.co.yellow.pure.quote.AbstractAPISupport;
import nz.co.yellow.pure.quote.data.QuoteLoadStrategies;
import nz.co.yellow.pure.quote.data.QuoteRequestReq;
import nz.co.yellow.pure.quote.data.QuoteRequestResp;
import nz.co.yellow.pure.quote.data.ServiceProviderQuote;
import nz.co.yellow.pure.quote.ds.QuoteRequestDS;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("quoteRequestAPI")
@Path("/pure/quote/quoteRequest")
public class QuoteRequestAPIImpl extends AbstractAPISupport implements
		QuoteRequestAPI {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(QuoteRequestAPIImpl.class);

	@Autowired
	private QuoteRequestDS quoteRequestDs;

	@Override
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createQuotesRequest(QuoteRequestReq quoteRequest) {
		LOGGER.debug("createQuotesRequest start:{}");
		Long id = null;
		QuoteRequestResp result = null;
		doValidationForCreation(quoteRequest);

		if (StringUtils.isEmpty(errorMessage)) {
			respStatus = Response.Status.BAD_REQUEST;
		} else {
			try {
				result = this.quoteRequestDs.createQuoteRequest(quoteRequest);
				id = result.getId();
			} catch (Exception e) {
				exceptionHandle(e);
			}
		}

		LOGGER.debug("createMessageThread end:{}");
		return buildResponse(id);
	}

	@Override
	@GET
	@Produces("application/json")
	@Path("/{quoteReqId}")
	public Response getQuoteRequestById(@PathParam("quoteReqId") Long quoteReqId) {
		LOGGER.debug("getQuoteRequestById start:{}", quoteReqId);
		QuoteRequestResp result = null;

		try {
			result = this.quoteRequestDs.getQuoteRequestById(quoteReqId,
					QuoteLoadStrategies.ALL);
		} catch (Exception e) {
			exceptionHandle(e);
		}

		LOGGER.debug("getQuoteRequestById end:{}");
		return buildResponse(result);
	}

	@Override
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/updateStatus/{quoteReqId}")
	public Response updateQuoteRequestStatus(
			@PathParam("quoteReqId") Long quoteReqId,
			QuoteRequestReq quotesRequest) {
		LOGGER.debug("updateQuoteRequestStatus start:{}");
		Long id = null;
		QuoteRequestResp result = null;

		try {
			result = this.quoteRequestDs.updateQuoteRequestStatus(quoteReqId,
					quotesRequest.getStatus());
			id = result.getId();
		} catch (Exception e) {
			exceptionHandle(e);
		}

		LOGGER.debug("updateQuoteRequestStatus end:{}");
		return buildResponse(id);
	}

	@Override
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/addProviderQuotes/{quoteReqId}")
	public Response addProviderQuotes(@PathParam("quoteReqId") Long quoteReqId,
			List<ServiceProviderQuote> serviceProviderQuotes) {
		LOGGER.debug("addProviderQuotes start:{}");
		Long id = quoteReqId;

		try {
			this.quoteRequestDs.addProviderQuotes(quoteReqId,
					serviceProviderQuotes);
		} catch (Exception e) {
			exceptionHandle(e);
		}

		LOGGER.debug("updateQuoteRequestStatus end:{}");
		return buildResponse(id);
	}

	@Override
	@GET
	@Produces("application/json")
	@Path("/listProviderQuotes/{quoteReqId}")
	public Response getAllProviderQuotesByQuoteId(
			@PathParam("quoteReqId") Long quoteReqId) {
		LOGGER.debug("getAllProviderQuotesByQuoteId start:{}", quoteReqId);
		QuoteRequestResp quoteRequestResp = null;
		List<ServiceProviderQuote> serviceProviderQuotes = null;

		try {
			quoteRequestResp = this.quoteRequestDs
					.getQuoteRequestById(quoteReqId,
							QuoteLoadStrategies.LOAD_PROVIDER_QUOTE_REQUEST);
			serviceProviderQuotes = quoteRequestResp.getServiceProviderQuotes();
		} catch (Exception e) {
			exceptionHandle(e);
		}
		LOGGER.debug("updateQuoteRequestStatus end:{}");
		return buildResponse(serviceProviderQuotes);
	}

	private void doValidationForCreation(QuoteRequestReq quoteRequest) {
		if (quoteRequest == null) {
			errorMessage = "QuoteRequestReq can not be null";
		} else if (quoteRequest.getServiceConsumer() == null) {
			errorMessage = "serviceConsumer can not be null";
		} else if (StringUtils.isEmpty(quoteRequest.getStatus())) {
			errorMessage = "status can not be null";
		} else if (quoteRequest.getCategoryId() == null) {
			errorMessage = "CategoryId can not be null";
		}
	}

}
