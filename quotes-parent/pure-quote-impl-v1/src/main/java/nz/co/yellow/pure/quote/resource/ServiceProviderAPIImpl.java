package nz.co.yellow.pure.quote.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nz.co.yellow.pure.quote.GenericAPIError;
import nz.co.yellow.pure.quote.QuotesAPIUtils;
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
public class ServiceProviderAPIImpl implements ServiceProviderAPI {

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
		GenericAPIError genericAPIError = null;

		try {
			if (StringUtils.isEmpty(serviceProvider.getUserId())) {
				throw new Exception("serviceProvider user id can not be null");
			} else {
				result = serviceProviderDs
						.createServiceProvider(serviceProvider);
				id = result.getId();
			}
		} catch (Exception e) {
			genericAPIError = QuotesAPIUtils.errorHandle(e);
		}
		LOGGER.debug("createServiceProvider end:{}");
		return QuotesAPIUtils.buildResponse(id, genericAPIError);
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
		GenericAPIError genericAPIError = null;
		try {
			if (StringUtils.isEmpty(serviceProvider.getUserId())) {
				throw new Exception("serviceProvider user id can not be null");
			} else {
				result = serviceProviderDs.updateServiceProvider(id,
						serviceProvider);
				id = result.getId();
			}
		} catch (Exception e) {
			genericAPIError = QuotesAPIUtils.errorHandle(e);
		}
		LOGGER.debug("updateServiceProvider end:{}");
		return QuotesAPIUtils.buildResponse(id, genericAPIError);
	}

	@Override
	@GET
	@Produces("application/json")
	@Path("/{providerId}")
	public Response getServiceProviderById(
			@PathParam("providerId") Long providerId) {
		LOGGER.debug("getServiceProviderById start:{}", providerId);
		ServiceProviderResp resultDto = null;
		GenericAPIError genericAPIError = null;
		try {
			resultDto = serviceProviderDs.getServiceProviderById(providerId,
					QuoteLoadStrategies.ALL);
		} catch (Exception e) {
			genericAPIError = QuotesAPIUtils.errorHandle(e);
		}
		LOGGER.debug("getServiceProviderById end:{}");
		return QuotesAPIUtils.buildResponse(resultDto, genericAPIError);
	}

	@Override
	public Response getAllProvideQuotesByProviderId(Long providerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
