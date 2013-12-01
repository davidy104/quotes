package nz.co.yellow.pure.quote.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import nz.co.yellow.pure.quote.data.CategoryQuestion;
import nz.co.yellow.pure.quote.data.QuoteRequestReq;
import nz.co.yellow.pure.quote.data.ServiceConsumerReq;
import nz.co.yellow.pure.quote.data.ServiceProviderQuote;
import nz.co.yellow.pure.quote.data.ServiceProviderReq;

public class QuotesTestUtils {

	public static CategoryQuestion createCategoryQuestion() {
		return CategoryQuestion
				.getBuilder(
						"general_nature_11",
						"Please indicate the general nature of your inquiry",
						"SimpleSelection",
						"{\"field_choices\": [[\"Plumbing\", \"Plumbing\"], [\"Spouting\",\"Spouting\"], [\"Gas\", \"Gas\"], [\"Other\", \"Other\"]]}",
						0, new Long(1311)).build();
	}

	public static ServiceProviderReq createServiceProviderReq() {
		return ServiceProviderReq.getBuilder("8498", "diamond-water-gas-new",
				BigDecimal.ZERO.toString()).build();
	}

	public static QuoteRequestReq createQuoteRequestReq() {
		ServiceConsumerReq newConsumer = createServiceConsumerReq();
		List<ServiceProviderQuote> serviceProviderQuotes = createServiceProviderQuotes();
		return QuoteRequestReq
				.getBuilder(
						newConsumer,
						serviceProviderQuotes,
						"DRAFT",
						new Long(1311),
						"{\"have_welder_01\": \"No\", \"how_much_wood_01\": 12.0, \"general_nature_01\": \"Plumbing\", \"favourite_color_01\": \"sky blue\"}",
						"2013-08-22T02:11:10.953296").build();
	}

	public static ServiceConsumerReq createServiceConsumerReq() {
		return ServiceConsumerReq.getBuilder("9c58").build();
	}

	public static List<ServiceProviderQuote> createServiceProviderQuotes() {
		List<ServiceProviderQuote> serviceProviderQuotes = new ArrayList<ServiceProviderQuote>();
		ServiceProviderQuote serviceProviderQuote = ServiceProviderQuote
				.getBuilder(1L, 1L, "2013-03-22T01:11:10.953296", "READ")
				.build();
		serviceProviderQuotes.add(serviceProviderQuote);
		serviceProviderQuote = ServiceProviderQuote.getBuilder(2L, 2L,
				"2013-06-12T04:11:10.953296", "RESPONDING").build();
		serviceProviderQuotes.add(serviceProviderQuote);
		return serviceProviderQuotes;
	}
}
