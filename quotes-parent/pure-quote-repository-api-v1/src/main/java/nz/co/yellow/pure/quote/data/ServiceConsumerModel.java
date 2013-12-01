package nz.co.yellow.pure.quote.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("serial")
@Entity
@Table(name = "Service_Consumer")
public class ServiceConsumerModel implements Serializable {

	@Id
	@GeneratedValue(generator = "csSeq")
	@SequenceGenerator(name = "csSeq", sequenceName = "CONSUMER_SEQ")
	@Column(name = "CONSUMER_ID", insertable = false, updatable = false)
	protected Long consumerId;

	@Column(name = "USER_ID")
	protected String userId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceConsumer")
	private List<QuoteRequestModel> quoteRequestList;

	public Long getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(Long consumerId) {
		this.consumerId = consumerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<QuoteRequestModel> getQuoteRequestList() {
		return quoteRequestList;
	}

	public void setQuoteRequestList(List<QuoteRequestModel> quoteRequestList) {
		this.quoteRequestList = quoteRequestList;
	}

	public void update(String userId) {
		this.userId = userId;
	}

	public static Builder getBuilder(String userId) {
		return new Builder(userId);
	}

	public static class Builder {

		private ServiceConsumerModel built;

		public Builder(String userId) {
			built = new ServiceConsumerModel();
			built.userId = userId;
		}

		public ServiceConsumerModel build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
				.append("consumerId", consumerId).append("userId", userId)
				.toString();

	}
}
