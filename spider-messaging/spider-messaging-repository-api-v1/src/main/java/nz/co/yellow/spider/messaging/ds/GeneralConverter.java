package nz.co.yellow.spider.messaging.ds;

/**
 * Abstract converter that handles convention between dto and domain model.
 * 
 * @author david
 * 
 * @param <M>
 *            DTO
 * @param <V>
 *            Model
 */
public interface GeneralConverter<M, V> {

	/**
	 * convention logic might be different as per different domain services
	 * 
	 */
	public enum ServiceOperation {
		CREATE, UPDATE, DELETE, QUERY
	}

	/**
	 * To DTO
	 * 
	 * @param sourceObj
	 * @param operation
	 * @param additionalSourceObj
	 * @return
	 */
	M convertFrom(V sourceObj, ServiceOperation operation,
			Object... additionalSourceObj) throws ConvertException;

	V convertTo(M sourceObj, ServiceOperation operation,
			Object... additionalSourceObj) throws ConvertException;
}
