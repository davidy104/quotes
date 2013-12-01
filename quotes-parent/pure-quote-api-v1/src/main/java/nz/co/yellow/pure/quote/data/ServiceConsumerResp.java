package nz.co.yellow.pure.quote.data;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class ServiceConsumerResp implements Serializable {

	protected Long id;

	protected String userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public static Builder getBuilder(String userId) {
		return new Builder(userId);
	}

	public static class Builder {

		private ServiceConsumerResp built;

		public Builder(String userId) {
			built = new ServiceConsumerResp();
			built.userId = userId;
		}

		public ServiceConsumerResp build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
