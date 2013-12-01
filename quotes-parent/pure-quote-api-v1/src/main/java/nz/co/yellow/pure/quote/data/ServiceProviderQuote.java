package nz.co.yellow.pure.quote.data;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class ServiceProviderQuote implements Serializable {

	private Long id;

	private Long serviceProviderId;

	private Long messageThreadId;

	private String dateRequired;

	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMessageThreadId() {
		return messageThreadId;
	}

	public void setMessageThreadId(Long messageThreadId) {
		this.messageThreadId = messageThreadId;
	}

	public Long getServiceProviderId() {
		return serviceProviderId;
	}

	public void setServiceProviderId(Long serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	public String getDateRequired() {
		return dateRequired;
	}

	public void setDateRequired(String dateRequired) {
		this.dateRequired = dateRequired;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static Builder getBuilder(Long serviceProviderId,
			Long messageThreadId, String dateRequired, String status) {
		return new Builder(serviceProviderId, messageThreadId, dateRequired,
				status);
	}

	public static Builder getBuilder(Long serviceProviderId,
			Long messageThreadId, String status) {
		return new Builder(serviceProviderId, messageThreadId, status);
	}

	public static Builder getBuilder(Long id, Long serviceProviderId,
			Long messageThreadId, String status) {
		return new Builder(id, serviceProviderId, messageThreadId, status);
	}

	public static class Builder {

		private ServiceProviderQuote built;

		public Builder(Long serviceProviderId, Long messageThreadId,
				String dateRequired, String status) {
			built = new ServiceProviderQuote();
			built.serviceProviderId = serviceProviderId;
			built.messageThreadId = messageThreadId;
			built.dateRequired = dateRequired;
			built.status = status;
		}

		public Builder(Long serviceProviderId, Long messageThreadId,
				String status) {
			built = new ServiceProviderQuote();
			built.serviceProviderId = serviceProviderId;
			built.messageThreadId = messageThreadId;
			built.status = status;
		}

		public Builder(Long id, Long serviceProviderId, Long messageThreadId,
				String status) {
			built = new ServiceProviderQuote();
			built.id = id;
			built.serviceProviderId = serviceProviderId;
			built.messageThreadId = messageThreadId;
			built.status = status;
		}

		public ServiceProviderQuote build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
