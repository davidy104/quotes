package nz.co.yellow.pure.quote.ds;


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
	T convertToResponse(V model, Object... additionalSourceObj)
			throws ConvertException;

	V convertFromRequest(M request, Object... additionalSourceObj)
			throws ConvertException;
}
