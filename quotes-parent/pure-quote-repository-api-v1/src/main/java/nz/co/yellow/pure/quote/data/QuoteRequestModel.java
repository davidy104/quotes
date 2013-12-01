package nz.co.yellow.pure.quote.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("serial")
@Entity
@Table(name = "Quote_Request")
public class QuoteRequestModel implements Serializable {
	@Id
	@GeneratedValue(generator = "qrSeq")
	@SequenceGenerator(name = "qrSeq", sequenceName = "QUOTEREQUEST_SEQ")
	@Column(name = "QUOTE_REQ_ID", insertable = false, updatable = false)
	private Long quoteRequestId;

	@Column(name = "CREATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "CATEGORY_ID")
	private Long categoryId;

	@Column(name = "CATEGORY_PAYLOAD")
	private String categoryPayload;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONSUMER_ID", referencedColumnName = "CONSUMER_ID")
	private ServiceConsumerModel serviceConsumer;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST }, mappedBy = "quoteRequest")
	private List<ServiceProviderQuoteModel> providerQuotesList;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST }, mappedBy = "quoteRequest")
	private List<QuoteSystemPictureModel> quoteSysPictureList;

	public void addServiceProviderQuoteModel(
			ServiceProviderQuoteModel serviceProviderQuoteModel) {
		if (providerQuotesList == null) {
			providerQuotesList = new ArrayList<ServiceProviderQuoteModel>();
		}
		providerQuotesList.add(serviceProviderQuoteModel);
	}

	public void addQuoteSystemPictureModel(
			QuoteSystemPictureModel quoteSystemPictureModel) {
		if (quoteSysPictureList == null) {
			quoteSysPictureList = new ArrayList<QuoteSystemPictureModel>();
		}
		quoteSysPictureList.add(quoteSystemPictureModel);
	}

	public Long getQuoteRequestId() {
		return quoteRequestId;
	}

	public void setQuoteRequestId(Long quoteRequestId) {
		this.quoteRequestId = quoteRequestId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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

	public ServiceConsumerModel getServiceConsumer() {
		return serviceConsumer;
	}

	public void setServiceConsumer(ServiceConsumerModel serviceConsumer) {
		this.serviceConsumer = serviceConsumer;
	}

	public List<ServiceProviderQuoteModel> getProviderQuotesList() {
		return providerQuotesList;
	}

	public void setProviderQuotesList(
			List<ServiceProviderQuoteModel> providerQuotesList) {
		this.providerQuotesList = providerQuotesList;
	}

	public List<QuoteSystemPictureModel> getQuoteSysPictureList() {
		return quoteSysPictureList;
	}

	public void setQuoteSysPictureList(
			List<QuoteSystemPictureModel> quoteSysPictureList) {
		this.quoteSysPictureList = quoteSysPictureList;
	}

	public static Builder getBuilder(Date createdTime, String status,
			Long categoryId, String categoryPayload,
			ServiceConsumerModel serviceConsumer) {
		return new Builder(createdTime, status, categoryId, categoryPayload,
				serviceConsumer);
	}

	public static Builder getBuilder(String status, Long categoryId,
			String categoryPayload) {
		return new Builder(status, categoryId, categoryPayload);
	}

	public static class Builder {

		private QuoteRequestModel built;

		public Builder(Date createdTime, String status, Long categoryId,
				String categoryPayload, ServiceConsumerModel serviceConsumer) {
			built = new QuoteRequestModel();
			built.createdTime = createdTime;
			built.categoryId = categoryId;
			built.categoryPayload = categoryPayload;
			built.status = status;
			built.serviceConsumer = serviceConsumer;
		}

		public Builder(String status, Long categoryId, String categoryPayload) {
			built = new QuoteRequestModel();
			built.categoryId = categoryId;
			built.categoryPayload = categoryPayload;
			built.status = status;
		}

		public QuoteRequestModel build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
				.append("quoteRequestId", quoteRequestId)
				.append("createdTime", createdTime).append("status", status)
				.append("categoryId", categoryId)
				.append("categoryPayload", categoryPayload).toString();

	}
}
