package nz.co.yellow.spider.messaging.ds;

import nz.co.yellow.spider.messaging.ds.GeneralConverter.ServiceOperation;

/**
 * 
 * @author david
 * 
 * @param <M>
 *            request
 * @param <V>
 *            domain model
 * @param <T>
 *            response
 */
public interface APIConverter<M, V, T> {
	T convertToResponse(V model, ServiceOperation operation,
			Object... additionalSourceObj) throws ConvertException;

	V convertFromRequest(M request, ServiceOperation operation,
			Object... additionalSourceObj) throws ConvertException;
}
