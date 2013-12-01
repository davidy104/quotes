package nz.co.yellow.pure.quote.data;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class ServiceProviderResp extends ServiceProvider implements
		Serializable {
	protected Long id;

	private List<ServiceProviderQuote> serviceProviderQuotes;

	public List<ServiceProviderQuote> getServiceProviderQuotes() {
		return serviceProviderQuotes;
	}

	public void setServiceProviderQuotes(
			List<ServiceProviderQuote> serviceProviderQuotes) {
		this.serviceProviderQuotes = serviceProviderQuotes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static Builder getBuilder(Long id, String userId, String customerId,
			String boostWeight) {
		return new Builder(id, userId, customerId, boostWeight);
	}

	public static Builder getBuilder(Long id, String userId, String customerId) {
		return new Builder(id, userId, customerId);
	}

	public static Builder getBuilder(String userId, String customerId,
			String boostWeight) {
		return new Builder(userId, customerId, boostWeight);
	}

	public static Builder getBuilder(String userId, String customerId) {
		return new Builder(userId, customerId);
	}

	public static class Builder {

		private ServiceProviderResp built;

		public Builder(Long id, String userId, String customerId,
				String boostWeight) {
			built = new ServiceProviderResp();
			built.id = id;
			built.userId = userId;
			built.customerId = customerId;
			built.boostWeight = boostWeight;
		}

		public Builder(Long id, String userId, String customerId) {
			built = new ServiceProviderResp();
			built.id = id;
			built.userId = userId;
			built.customerId = customerId;
		}

		public Builder(String userId, String customerId, String boostWeight) {
			built = new ServiceProviderResp();
			built.userId = userId;
			built.customerId = customerId;
			built.boostWeight = boostWeight;
		}

		public Builder(String userId, String customerId) {
			built = new ServiceProviderResp();
			built.userId = userId;
			built.customerId = customerId;
		}

		public ServiceProviderResp build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
