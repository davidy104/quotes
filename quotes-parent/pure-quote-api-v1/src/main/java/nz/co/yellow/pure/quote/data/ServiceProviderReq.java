package nz.co.yellow.pure.quote.data;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class ServiceProviderReq extends ServiceProvider implements Serializable {

	public static Builder getBuilder(String userId, String customerId,
			String boostWeight) {
		return new Builder(userId, customerId, boostWeight);
	}

	public static Builder getBuilder(String userId, String customerId) {
		return new Builder(userId, customerId);
	}

	public static class Builder {

		private ServiceProviderReq built;

		public Builder(String userId, String customerId, String boostWeight) {
			built = new ServiceProviderReq();
			built.userId = userId;
			built.customerId = customerId;
			built.boostWeight = boostWeight;
		}

		public Builder(String userId, String customerId) {
			built = new ServiceProviderReq();
			built.userId = userId;
			built.customerId = customerId;
		}

		public ServiceProviderReq build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
