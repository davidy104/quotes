package nz.co.yellow.pure.quote.data;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class QuoteRequestReq extends QuoteRequest implements Serializable {

	private List<QuoteSystemPictureReq> systemPictures;

	private ServiceConsumerReq serviceConsumer;

	public List<QuoteSystemPictureReq> getSystemPictures() {
		return systemPictures;
	}

	public void setSystemPictures(List<QuoteSystemPictureReq> systemPictures) {
		this.systemPictures = systemPictures;
	}

	public ServiceConsumerReq getServiceConsumer() {
		return serviceConsumer;
	}

	public void setServiceConsumer(ServiceConsumerReq serviceConsumer) {
		this.serviceConsumer = serviceConsumer;
	}

	public static Builder getBuilder(ServiceConsumerReq serviceConsumer,
			List<ServiceProviderQuote> serviceProviderQuotes, String status,
			Long categoryId, String categoryPayload, String dateCreated,
			List<QuoteSystemPictureReq> systemPictures) {
		return new Builder(serviceConsumer, serviceProviderQuotes, status,
				categoryId, categoryPayload, dateCreated, systemPictures);
	}

	public static Builder getBuilder(ServiceConsumerReq serviceConsumer,
			List<ServiceProviderQuote> serviceProviderQuotes, String status,
			Long categoryId, String categoryPayload, String dateCreated) {
		return new Builder(serviceConsumer, serviceProviderQuotes, status,
				categoryId, categoryPayload, dateCreated);
	}

	public static class Builder {

		private QuoteRequestReq built;

		public Builder(ServiceConsumerReq serviceConsumer,
				List<ServiceProviderQuote> serviceProviderQuotes,
				String status, Long categoryId, String categoryPayload,
				String dateCreated, List<QuoteSystemPictureReq> systemPictures) {
			built = new QuoteRequestReq();
			built.serviceConsumer = serviceConsumer;
			built.serviceProviderQuotes = serviceProviderQuotes;
			built.status = status;
			built.categoryId = categoryId;
			built.categoryPayload = categoryPayload;
			built.dateCreated = dateCreated;
			built.systemPictures = systemPictures;
		}

		public Builder(ServiceConsumerReq serviceConsumer,
				List<ServiceProviderQuote> serviceProviderQuotes,
				String status, Long categoryId, String categoryPayload,
				String dateCreated) {
			built = new QuoteRequestReq();
			built.serviceConsumer = serviceConsumer;
			built.serviceProviderQuotes = serviceProviderQuotes;
			built.status = status;
			built.categoryId = categoryId;
			built.categoryPayload = categoryPayload;
			built.dateCreated = dateCreated;
		}

		public QuoteRequestReq build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
