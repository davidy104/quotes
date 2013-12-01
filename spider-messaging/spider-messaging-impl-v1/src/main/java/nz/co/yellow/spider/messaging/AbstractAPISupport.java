package nz.co.yellow.spider.messaging;

import javax.ws.rs.core.Response;

import nz.co.yellow.spider.messaging.ds.NotFoundException;
import nz.co.yellow.spider.messaging.util.MessageUtils;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractAPISupport {

	protected Response.Status respStatus = Response.Status.OK;

	protected String errorMessage = null;

	public static final String UNKNOWN_ERROR_CODE = "Unknown error";

	public static final String BAD_REQUEST_CODE = "Request is incorrect";

	protected void exceptionHandle(Throwable e) {
		respStatus = Response.Status.INTERNAL_SERVER_ERROR;
		StringBuilder errorMsgBuffer = new StringBuilder();
		String errorMsg = e.getMessage() == null ? UNKNOWN_ERROR_CODE : e
				.getMessage();
		String errorCauseStr = MessageUtils.getExceptionInfo(e);
		errorMsgBuffer.append(errorMsg).append(":").append(errorCauseStr);

		if (e instanceof NotFoundException) {
			respStatus = Response.Status.NOT_FOUND;
		}
		errorMessage = errorMsgBuffer.toString();
	}

	protected Response buildResponse(Object entity) {
		Response response = null;
		if (!StringUtils.isEmpty(errorMessage)) {
			response = Response.status(respStatus).entity(errorMessage).build();
		} else {
			response = Response.status(respStatus).entity(entity).build();
		}

		return response;
	}
}
