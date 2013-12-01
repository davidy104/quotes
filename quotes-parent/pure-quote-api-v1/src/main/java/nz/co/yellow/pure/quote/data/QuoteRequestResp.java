package nz.co.yellow.pure.quote.data;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class QuoteRequestResp extends QuoteRequest implements Serializable {
	protected Long id;

	private List<QuoteSystemPictureResp> systemPictures;

	private ServiceConsumerResp serviceConsumer;

	public List<QuoteSystemPictureResp> getSystemPictures() {
		return systemPictures;
	}

	public void setSystemPictures(List<QuoteSystemPictureResp> systemPictures) {
		this.systemPictures = systemPictures;
	}

	public ServiceConsumerResp getServiceConsumer() {
		return serviceConsumer;
	}

	public void setServiceConsumer(ServiceConsumerResp serviceConsumer) {
		this.serviceConsumer = serviceConsumer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static Builder getBuilder(ServiceConsumerResp serviceConsumer,
			List<ServiceProviderQuote> serviceProviderQuotes, String status,
			Long categoryId, String categoryPayload, String dateCreated,
			List<QuoteSystemPictureResp> systemPictures, Long id) {
		return new Builder(serviceConsumer, serviceProviderQuotes, status,
				categoryId, categoryPayload, dateCreated, systemPictures, id);
	}

	public static Builder getBuilder(String status, Long categoryId,
			String categoryPayload, Long id) {
		return new Builder(status, categoryId, categoryPayload, id);
	}

	public static class Builder {

		private QuoteRequestResp built;

		public Builder(ServiceConsumerResp serviceConsumer,
				List<ServiceProviderQuote> serviceProviderQuotes,
				String status, Long categoryId, String categoryPayload,
				String dateCreated,
				List<QuoteSystemPictureResp> systemPictures, Long id) {
			built = new QuoteRequestResp();
			built.serviceConsumer = serviceConsumer;
			built.serviceProviderQuotes = serviceProviderQuotes;
			built.status = status;
			built.categoryId = categoryId;
			built.categoryPayload = categoryPayload;
			built.dateCreated = dateCreated;
			built.systemPictures = systemPictures;
			built.id = id;
		}

		public Builder(String status, Long categoryId, String categoryPayload,
				Long id) {
			built = new QuoteRequestResp();
			built.status = status;
			built.categoryId = categoryId;
			built.categoryPayload = categoryPayload;
			built.id = id;
		}

		public QuoteRequestResp build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
