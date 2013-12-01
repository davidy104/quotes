package nz.co.yellow.pure.quote.resource;

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
import nz.co.yellow.pure.quote.data.ServiceProviderReq;
import nz.co.yellow.pure.quote.data.ServiceProviderResp;
import nz.co.yellow.pure.quote.ds.ServiceProviderDS;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("serviceProviderAPI")
@Path("/pure/quote/serviceProvider")
public class ServiceProviderAPIImpl extends AbstractAPISupport implements
		ServiceProviderAPI {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServiceProviderAPIImpl.class);

	@Autowired
	private ServiceProviderDS serviceProviderDs;

	@Override
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createServiceProvider(ServiceProviderReq serviceProvider) {
		LOGGER.debug("createServiceProvider start:{}");
		ServiceProviderResp result = null;
		Long id = null;
		if (StringUtils.isEmpty(serviceProvider.getUserId())) {
			errorMessage = "serviceProvider user id can not be null";
			respStatus = Response.Status.BAD_REQUEST;
		} else {
			try {
				result = serviceProviderDs
						.createServiceProvider(serviceProvider);
				id = result.getId();
			} catch (Exception e) {
				exceptionHandle(e);
			}
		}
		LOGGER.debug("createServiceProvider end:{}");
		return buildResponse(id);
	}

	@Override
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/{providerId}")
	public Response updateServiceProvider(
			@PathParam("providerId") Long providerId,
			ServiceProviderReq serviceProvider) {
		LOGGER.debug("updateServiceProvider start:{}", providerId);
		Long id = null;
		ServiceProviderResp result = null;

		if (StringUtils.isEmpty(serviceProvider.getUserId())) {
			errorMessage = "serviceProvider user id can not be null";
			respStatus = Response.Status.BAD_REQUEST;
		} else {
			try {
				result = serviceProviderDs.updateServiceProvider(id,
						serviceProvider);
				id = result.getId();
			} catch (Exception e) {
				exceptionHandle(e);
			}
		}
		LOGGER.debug("updateServiceProvider end:{}");
		return buildResponse(id);
	}

	@Override
	@GET
	@Produces("application/json")
	@Path("/{providerId}")
	public Response getServiceProviderById(
			@PathParam("providerId") Long providerId) {
		LOGGER.debug("getServiceProviderById start:{}", providerId);
		ServiceProviderResp resultDto = null;

		try {
			resultDto = serviceProviderDs.getServiceProviderById(providerId,
					QuoteLoadStrategies.ALL);
		} catch (Exception e) {
			exceptionHandle(e);
		}
		LOGGER.debug("getServiceProviderById end:{}");
		return buildResponse(resultDto);
	}

	@Override
	public Response getAllProvideQuotesByProviderId(Long providerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
