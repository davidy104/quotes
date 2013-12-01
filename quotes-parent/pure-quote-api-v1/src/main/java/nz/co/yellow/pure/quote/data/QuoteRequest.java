package nz.co.yellow.pure.quote.data;

import java.util.List;

public class QuoteRequest {

	protected List<ServiceProviderQuote> serviceProviderQuotes;

	protected String status;

	protected Long categoryId;

	protected String categoryPayload;

	protected String dateCreated;

	public List<ServiceProviderQuote> getServiceProviderQuotes() {
		return serviceProviderQuotes;
	}

	public void setServiceProviderQuotes(
			List<ServiceProviderQuote> serviceProviderQuotes) {
		this.serviceProviderQuotes = serviceProviderQuotes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryPayload() {
		return categoryPayload;
	}

	public void setCategoryPayload(String categoryPayload) {
		this.categoryPayload = categoryPayload;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

}
