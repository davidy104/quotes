package nz.co.yellow.pure.quote.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nz.co.yellow.pure.quote.GenericAPIError;
import nz.co.yellow.pure.quote.QuotesAPIUtils;
import nz.co.yellow.pure.quote.data.ServiceProviderQuote;
import nz.co.yellow.pure.quote.ds.ServiceProviderQuoteDS;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("serviceProviderQuoteAPI")
@Path("/pure/quote/serviceProviderQuote")
public class ServiceProviderQuoteAPIImpl implements ServiceProviderQuoteAPI {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServiceProviderQuoteAPIImpl.class);

	@Autowired
	private ServiceProviderQuoteDS serviceProviderQuoteDs;

	@Override
	@GET
	@Produces("application/json")
	@Path("/{providerQuoteId}")
	public Response getServiceProviderQuoteById(
			@PathParam("providerQuoteId") Long serviceProviderQuoteId) {
		LOGGER.debug("getServiceProviderQuoteById start:{}",
				serviceProviderQuoteId);
		ServiceProviderQuote resultDto = null;
		GenericAPIError genericAPIError = null;
		try {
			resultDto = serviceProviderQuoteDs
					.getServiceProviderQuoteById(serviceProviderQuoteId);
		} catch (Exception e) {
			genericAPIError = QuotesAPIUtils.errorHandle(e);
		}

		LOGGER.debug("getServiceProviderQuoteById end");
		return QuotesAPIUtils.buildResponse(resultDto, genericAPIError);
	}

	@Override
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/{providerQuoteId}")
	public Response updateServiceProviderQuoteStatus(
			@PathParam("providerQuoteId") Long serviceProviderQuoteId,
			ServiceProviderQuote serviceProviderQuote) {
		LOGGER.debug("updateServiceProviderQuoteStatus start:{}",
				serviceProviderQuoteId);
		Long id = null;
		ServiceProviderQuote result = null;
		GenericAPIError genericAPIError = null;
		try {
			if (StringUtils.isEmpty(serviceProviderQuote.getStatus())) {
				throw new Exception("status can not be null");
			} else {
				result = serviceProviderQuoteDs
						.updateServiceProviderQuoteStatus(
								serviceProviderQuoteId,
								serviceProviderQuote.getStatus());
				id = result.getId();
			}
		} catch (Exception e) {
			genericAPIError = QuotesAPIUtils.errorHandle(e);
		}

		LOGGER.debug("updateServiceProviderQuoteStatus end:{}");
		return QuotesAPIUtils.buildResponse(id, genericAPIError);
	}

}
