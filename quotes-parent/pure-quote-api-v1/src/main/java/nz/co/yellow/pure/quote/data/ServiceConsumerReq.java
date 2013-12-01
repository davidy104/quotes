package nz.co.yellow.pure.quote.data;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class ServiceConsumerReq implements Serializable {

	private Long quoteRequestId;

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getQuoteRequestId() {
		return quoteRequestId;
	}

	public void setQuoteRequestId(Long quoteRequestId) {
		this.quoteRequestId = quoteRequestId;
	}

	public static Builder getBuilder(String userId, Long quoteRequestId) {
		return new Builder(userId, quoteRequestId);
	}

	public static Builder getBuilder(String userId) {
		return new Builder(userId);
	}

	public static class Builder {

		private ServiceConsumerReq built;

		public Builder(String userId, Long quoteRequestId) {
			built = new ServiceConsumerReq();
			built.userId = userId;
			built.quoteRequestId = quoteRequestId;
		}

		public Builder(String userId) {
			built = new ServiceConsumerReq();
			built.userId = userId;
		}

		public ServiceConsumerReq build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
