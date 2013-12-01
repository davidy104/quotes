package nz.co.yellow.pure.quote.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("serial")
@Entity
@Table(name = "Quote_SystemPicture")
public class QuoteSystemPictureModel implements Serializable {
	@Id
	@GeneratedValue(generator = "qspSeq")
	@SequenceGenerator(name = "qspSeq", sequenceName = "QUOTE_PIC_SEQ")
	@Column(name = "SYS_PIC_ID", insertable = false, updatable = false)
	private Long pictureId;

	@Column(name = "PIC_REF")
	private String pictureRef;

	@Column(name = "CAPTION")
	private String caption;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUOTE_REQ_ID", referencedColumnName = "QUOTE_REQ_ID")
	private QuoteRequestModel quoteRequest;

	public Long getPictureId() {
		return pictureId;
	}

	public void setPictureId(Long pictureId) {
		this.pictureId = pictureId;
	}

	public String getPictureRef() {
		return pictureRef;
	}

	public void setPictureRef(String pictureRef) {
		this.pictureRef = pictureRef;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public QuoteRequestModel getQuoteRequest() {
		return quoteRequest;
	}

	public void setQuoteRequest(QuoteRequestModel quoteRequest) {
		this.quoteRequest = quoteRequest;
	}

	public void update(String pictureRef, String caption,
			QuoteRequestModel quoteRequest) {
		this.pictureRef = pictureRef;
		this.caption = caption;
		this.quoteRequest = quoteRequest;
	}

	public void update(String pictureRef, String caption) {
		this.pictureRef = pictureRef;
		this.caption = caption;
	}

	public static Builder getBuilder(String pictureRef, String caption,
			QuoteRequestModel quotesRequest) {
		return new Builder(pictureRef, caption, quotesRequest);
	}

	public static Builder getBuilder(String pictureRef, String caption) {
		return new Builder(pictureRef, caption);
	}

	public static class Builder {

		private QuoteSystemPictureModel built;

		public Builder(String pictureRef, String caption,
				QuoteRequestModel quoteRequest) {
			built = new QuoteSystemPictureModel();
			built.pictureRef = pictureRef;
			built.caption = caption;
			built.quoteRequest = quoteRequest;
		}

		public Builder(String pictureRef, String caption) {
			built = new QuoteSystemPictureModel();
			built.pictureRef = pictureRef;
			built.caption = caption;
		}

		public QuoteSystemPictureModel build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
				.append("pictureId", pictureId)
				.append("pictureRef", pictureRef).append("caption", caption)
				.toString();

	}
}
