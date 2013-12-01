package nz.co.yellow.pure.quote.ds;

import java.util.List;

import nz.co.yellow.pure.quote.data.QuoteLoadStrategies;
import nz.co.yellow.pure.quote.data.QuoteRequestReq;
import nz.co.yellow.pure.quote.data.QuoteRequestResp;
import nz.co.yellow.pure.quote.data.ServiceProviderQuote;

public interface QuoteRequestDS {

	QuoteRequestResp createQuoteRequest(QuoteRequestReq quoteRequestReq)
			throws Exception;

	QuoteRequestResp updateQuoteRequestStatus(Long quoteRequestId, String status)
			throws Exception;

	void addProviderQuotes(Long quoteReqId,
			List<ServiceProviderQuote> serviceProviderQuotes) throws Exception;

	QuoteRequestResp getQuoteRequestById(Long quoteRequestId,
			QuoteLoadStrategies loadStrategies) throws Exception;

}
