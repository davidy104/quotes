package nz.co.yellow.pure.quote.data;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class QuoteSystemPictureResp extends QuoteSystemPicture implements
		Serializable {

	private Long id;

	private Long quoteRequestId;

	public Long getQuoteRequestId() {
		return quoteRequestId;
	}

	public void setQuoteRequestId(Long quoteRequestId) {
		this.quoteRequestId = quoteRequestId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static Builder getBuilder(Long id, String pictureRef, String caption) {
		return new Builder(id, pictureRef, caption);
	}

	public static class Builder {

		private QuoteSystemPictureResp built;

		public Builder(Long id, String pictureRef, String caption) {
			built = new QuoteSystemPictureResp();
			built.id = id;
			built.pictureRef = pictureRef;
			built.caption = caption;
		}

		public QuoteSystemPictureResp build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
