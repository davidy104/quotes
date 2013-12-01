package nz.co.yellow.pure.quote.data;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class QuoteSystemPictureReq extends QuoteSystemPicture implements
		Serializable {
	private Long quoteRequestId;

	public Long getQuoteRequestId() {
		return quoteRequestId;
	}

	public void setQuoteRequestId(Long quoteRequestId) {
		this.quoteRequestId = quoteRequestId;
	}

	public static Builder getBuilder(Long quoteRequestId, String pictureRef,
			String caption) {
		return new Builder(quoteRequestId, pictureRef, caption);
	}

	public static class Builder {

		private QuoteSystemPictureReq built;

		public Builder(Long quoteRequestId, String pictureRef, String caption) {
			built = new QuoteSystemPictureReq();
			built.quoteRequestId = quoteRequestId;
			built.pictureRef = pictureRef;
			built.caption = caption;
		}

		public QuoteSystemPictureReq build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
