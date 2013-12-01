package nz.co.yellow.pure.quote.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("serial")
@Entity
@Table(name = "ServiceProvider_Quote")
public class ServiceProviderQuoteModel implements Serializable {

	@Id
	@GeneratedValue(generator = "spqSeq")
	@SequenceGenerator(name = "spqSeq", sequenceName = "PROVIDER_QUOTES_SEQ")
	@Column(name = "PROVIDER_QUOTE_ID", insertable = false, updatable = false)
	private Long providerQuoteId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROVIDER_ID", referencedColumnName = "PROVIDER_ID")
	private ServiceProviderModel serviceProvider;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUOTE_REQ_ID", referencedColumnName = "QUOTE_REQ_ID")
	private QuoteRequestModel quoteRequest;

	@Column(name = "MSG_THREAD_ID")
	private Long messageThreadId;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "DATE_REQUIRED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateRequired;

	public Long getProviderQuoteId() {
		return providerQuoteId;
	}

	public void setProviderQuoteId(Long providerQuoteId) {
		this.providerQuoteId = providerQuoteId;
	}

	public ServiceProviderModel getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProviderModel serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public QuoteRequestModel getQuoteRequest() {
		return quoteRequest;
	}

	public void setQuoteRequest(QuoteRequestModel quoteRequest) {
		this.quoteRequest = quoteRequest;
	}

	public Long getMessageThreadId() {
		return messageThreadId;
	}

	public void setMessageThreadId(Long messageThreadId) {
		this.messageThreadId = messageThreadId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateRequired() {
		return dateRequired;
	}

	public void setDateRequired(Date dateRequired) {
		this.dateRequired = dateRequired;
	}

	public static Builder getBuilder(ServiceProviderModel serviceProvider,
			QuoteRequestModel quotesRequest, Long messageThreadId,
			String status, Date dateRequired) {
		return new Builder(serviceProvider, quotesRequest, messageThreadId,
				status, dateRequired);
	}

	public static Builder getBuilder(ServiceProviderModel serviceProvider,
			QuoteRequestModel quotesRequest, Long messageThreadId, String status) {
		return new Builder(serviceProvider, quotesRequest, messageThreadId,
				status);
	}

	public static Builder getBuilder(Long messageThreadId, String status,
			Date dateRequired) {
		return new Builder(messageThreadId, status, dateRequired);
	}

	public static Builder getBuilder(Long messageThreadId, String status) {
		return new Builder(messageThreadId, status);
	}

	public static class Builder {

		private ServiceProviderQuoteModel built;

		public Builder(ServiceProviderModel serviceProvider,
				QuoteRequestModel quoteRequest, Long messageThreadId,
				String status, Date dateRequired) {
			built = new ServiceProviderQuoteModel();
			built.serviceProvider = serviceProvider;
			built.quoteRequest = quoteRequest;
			built.messageThreadId = messageThreadId;
			built.status = status;
			built.dateRequired = dateRequired;
		}

		public Builder(Long messageThreadId, String status) {
			built = new ServiceProviderQuoteModel();
			built.messageThreadId = messageThreadId;
			built.status = status;
		}

		public Builder(Long messageThreadId, String status, Date dateRequired) {
			built = new ServiceProviderQuoteModel();
			built.messageThreadId = messageThreadId;
			built.status = status;
			built.dateRequired = dateRequired;
		}

		public Builder(ServiceProviderModel serviceProvider,
				QuoteRequestModel quoteRequest, Long messageThreadId,
				String status) {
			built = new ServiceProviderQuoteModel();
			built.serviceProvider = serviceProvider;
			built.quoteRequest = quoteRequest;
			built.messageThreadId = messageThreadId;
			built.status = status;
		}

		public ServiceProviderQuoteModel build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
				.append("providerQuoteId", providerQuoteId)
				.append("dateRequired", dateRequired).append("status", status)
				.append("messageThreadId", messageThreadId).toString();

	}
}
