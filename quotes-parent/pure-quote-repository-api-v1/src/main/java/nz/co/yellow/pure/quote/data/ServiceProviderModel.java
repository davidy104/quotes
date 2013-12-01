package nz.co.yellow.pure.quote.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("serial")
@Entity
@Table(name = "Service_Provider")
public class ServiceProviderModel implements Serializable {

	@Id
	@GeneratedValue(generator = "spSeq")
	@SequenceGenerator(name = "spSeq", sequenceName = "PROVIDER_SEQ")
	@Column(name = "PROVIDER_ID", insertable = false, updatable = false)
	protected Long providerId;

	@Column(name = "USER_ID")
	protected String userId;

	@Column(name = "CUSTOMER_ID")
	protected String customerId;

	@Column(name = "CREATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdTime;

	@Column(name = "BOOST_WEIGHT")
	private BigDecimal boostWeight;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceProvider")
	private List<ServiceProviderQuoteModel> providerQuotesList;

	public BigDecimal getBoostWeight() {
		return boostWeight;
	}

	public void setBoostWeight(BigDecimal boostWeight) {
		this.boostWeight = boostWeight;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public List<ServiceProviderQuoteModel> getProviderQuotesList() {
		return providerQuotesList;
	}

	public void setProviderQuotesList(
			List<ServiceProviderQuoteModel> providerQuotesList) {
		this.providerQuotesList = providerQuotesList;
	}

	public void update(String userId, String customerId, BigDecimal boostWeight) {
		this.userId = userId;
		this.customerId = customerId;
		this.boostWeight = boostWeight;
	}

	public static Builder getBuilder(String userId, String customerId,
			Date createdTime, BigDecimal boostWeight) {
		return new Builder(userId, customerId, createdTime, boostWeight);
	}

	public static Builder getBuilder(String userId, String customerId,
			BigDecimal boostWeight) {
		return new Builder(userId, customerId, boostWeight);
	}

	public static Builder getBuilder(String userId, String customerId) {
		return new Builder(userId, customerId);
	}

	public static class Builder {

		private ServiceProviderModel built;

		public Builder(String userId, String customerId, Date createdTime,
				BigDecimal boostWeight) {
			built = new ServiceProviderModel();
			built.createdTime = createdTime;
			built.userId = userId;
			built.customerId = customerId;
			built.boostWeight = boostWeight;
		}

		public Builder(String userId, String customerId, BigDecimal boostWeight) {
			built = new ServiceProviderModel();
			built.boostWeight = boostWeight;
			built.userId = userId;
			built.customerId = customerId;
		}

		public Builder(String userId, String customerId) {
			built = new ServiceProviderModel();
			built.userId = userId;
			built.customerId = customerId;
		}

		public ServiceProviderModel build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
				.append("providerId", providerId).append("userId", userId)
				.append("customerId", customerId)
				.append("createdTime", createdTime)
				.append("boostWeight", boostWeight).toString();

	}
}
