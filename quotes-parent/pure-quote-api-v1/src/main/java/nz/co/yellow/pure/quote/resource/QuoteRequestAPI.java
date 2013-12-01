package nz.co.yellow.pure.quote.resource;

import java.util.List;

import javax.ws.rs.core.Response;

import nz.co.yellow.pure.quote.data.QuoteRequestReq;
import nz.co.yellow.pure.quote.data.ServiceProviderQuote;

public interface QuoteRequestAPI {

	Response createQuotesRequest(QuoteRequestReq quoteRequest);

	Response getQuoteRequestById(Long quoteReqId);

	Response updateQuoteRequestStatus(Long quoteReqId,
			QuoteRequestReq quoteRequest);

	Response addProviderQuotes(Long quoteReqId,
			List<ServiceProviderQuote> serviceProviderQuotes);

	Response getAllProviderQuotesByQuoteId(Long quoteReqId);
}
