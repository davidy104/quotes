package nz.co.yellow.pure.quote.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nz.co.yellow.pure.quote.AbstractAPISupport;
import nz.co.yellow.pure.quote.data.ServiceProviderQuote;
import nz.co.yellow.pure.quote.ds.ServiceProviderQuoteDS;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("serviceProviderQuoteAPI")
@Path("/pure/quote/serviceProviderQuote")
public class ServiceProviderQuoteAPIImpl extends AbstractAPISupport implements
		ServiceProviderQuoteAPI {
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

		try {
			resultDto = serviceProviderQuoteDs
					.getServiceProviderQuoteById(serviceProviderQuoteId);
		} catch (Exception e) {
			exceptionHandle(e);
		}

		LOGGER.debug("getServiceProviderQuoteById end");
		return buildResponse(resultDto);
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

		if (StringUtils.isEmpty(serviceProviderQuote.getStatus())) {
			errorMessage = "status can not be null";
			respStatus = Response.Status.BAD_REQUEST;
		} else {
			try {
				result = serviceProviderQuoteDs
						.updateServiceProviderQuoteStatus(
								serviceProviderQuoteId,
								serviceProviderQuote.getStatus());
				id = result.getId();
			} catch (Exception e) {
				exceptionHandle(e);
			}
		}

		LOGGER.debug("updateServiceProviderQuoteStatus end:{}");
		return buildResponse(id);
	}

}
