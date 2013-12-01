package nz.co.yellow.pure.quote.data.support;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nz.co.yellow.pure.quote.data.QuoteRequestModel;
import nz.co.yellow.pure.quote.data.QuoteSystemPictureModel;
import nz.co.yellow.pure.quote.data.ServiceConsumerModel;
import nz.co.yellow.pure.quote.data.ServiceProviderModel;
import nz.co.yellow.pure.quote.data.support.EntityBuilder.EntityBuilderManager;
import nz.co.yellow.pure.quote.util.QuotesUtils;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@SuppressWarnings("unused")
public class InitialDataSetup {
	private TransactionTemplate transactionTemplate;

	@PersistenceContext
	private EntityManager entityManager;

	private ServiceProviderModel serviceProvider1;
	private ServiceProviderModel serviceProvider2;
	private ServiceProviderModel serviceProvider3;
	private ServiceProviderModel serviceProvider4;

	private QuoteRequestModel quoteRequest1;
	private QuoteRequestModel quoteRequest2;

	private ServiceConsumerModel serviceConsumer1;
	private ServiceConsumerModel serviceConsumer2;

	private QuoteSystemPictureModel systemPicture1;
	private QuoteSystemPictureModel systemPicture2;
	private QuoteSystemPictureModel systemPicture3;
	private QuoteSystemPictureModel systemPicture4;

	public InitialDataSetup(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void initialize() {
		EntityBuilderManager.setEntityManager(this.entityManager);

		this.transactionTemplate.execute(new TransactionCallback<Void>() {

			@Override
			public Void doInTransaction(TransactionStatus status) {
				if (dataIsAlreadyPresent()) {
					return null;
				}
				// initiate categoryQuestion
				{
					new CategoryQuestionModelBuilder() {
						{
							create("general_nature_01",
									"Please indicate the general nature of your inquiry",
									"SimpleSelection",
									"{\"field_choices\": [[\"Plumbing\", \"Plumbing\"], [\"Spouting\",\"Spouting\"], [\"Gas\", \"Gas\"], [\"Other\", \"Other\"]]}",
									0, new Long(1311));

						}
					}.build();

					new CategoryQuestionModelBuilder() {
						{
							create("have_welder_01",
									"Do you have your own welder?",
									"SimpleRadio",
									"{\"field_choices\": [[\"Yes\", \"Yes\"], [\"No\", \"No\"]]}",
									0, new Long(1311));

						}
					}.build();

					new CategoryQuestionModelBuilder() {
						{
							create("how_much_wood_01",
									"How much wood (in kilos per day) would a woodchuck chuck if a woodchuck could chuck wood?",
									"Number",
									"{\"field_max_value\": 1000, \"field_min_value\": 0}",
									0, new Long(1311));

						}
					}.build();

					new CategoryQuestionModelBuilder() {
						{
							create("favourite_color_01",
									"What is your favourite colour?",
									"ShortText", "", 0, new Long(1311));

						}
					}.build();
				}

				// initiate consumers
				{
					InitialDataSetup.this.serviceConsumer1 = new ServiceConsumerModelBuilder() {
						{
							create("9c68");
						}
					}.build();

					InitialDataSetup.this.serviceConsumer2 = new ServiceConsumerModelBuilder() {
						{
							create("99c9");
						}
					}.build();
				}

				// initiate provider
				{
					InitialDataSetup.this.serviceProvider1 = new ServiceProviderModelBuilder() {
						{
							create("84f8", "diamond-water-gas", new Date(),
									BigDecimal.ZERO);
						}
					}.build();

					InitialDataSetup.this.serviceProvider2 = new ServiceProviderModelBuilder() {
						{
							create("efaa", "laser-plumbing-titan", new Date(),
									BigDecimal.ZERO);
						}
					}.build();

					InitialDataSetup.this.serviceProvider3 = new ServiceProviderModelBuilder() {
						{
							create("99c7", "rheem-new-zealand-avondale",
									new Date(), BigDecimal.ZERO);
						}
					}.build();

					InitialDataSetup.this.serviceProvider4 = new ServiceProviderModelBuilder() {
						{
							create("7caf", "marshall-plumbers-point",
									new Date(), BigDecimal.ZERO);
						}
					}.build();
				}

				// initiate quoteSystemPictures
				{
					InitialDataSetup.this.systemPicture1 = new QuoteSystemPictureModelBuilder() {
						{
							create("http://uploaded-images.gallifrey.yellow.co.nz/21184_scamp.jpg",
									"A cute kitten, not related to my spouting");
						}
					}.build();

					InitialDataSetup.this.systemPicture2 = new QuoteSystemPictureModelBuilder() {
						{
							create("http://uploaded-images.gallifrey.yellow.co.nz/Hdstreetfighter.jpg",
									"hadouken");
						}
					}.build();

					InitialDataSetup.this.systemPicture3 = new QuoteSystemPictureModelBuilder() {
						{
							create("http://uploaded-images.gallifrey.yellow.co.nz/cute cats pictures 1.jpg",
									"meow");
						}
					}.build();

					InitialDataSetup.this.systemPicture4 = new QuoteSystemPictureModelBuilder() {
						{
							create("http://uploaded-images.gallifrey.yellow.co.nz/cute cats 1027.jpg",
									"meow");
						}
					}.build();
				}

				// initiate quoteRequest
				{
					InitialDataSetup.this.quoteRequest1 = new QuoteRequestModelBuilder() {
						{
							create(QuotesUtils
									.strToDateIgnoreException("2013-09-25T10:51:10.953296"),
									"DRAFT",
									new Long(1311),
									"{\"have_welder_01\": \"No\", \"how_much_wood_01\": 12.0, \"general_nature_01\": \"Plumbing\", \"favourite_color_01\": \"sky blue\"}",
									serviceConsumer1);
							addQuoteSystemPictureModels(systemPicture1,
									systemPicture2);
						}
					}.build();

					InitialDataSetup.this.quoteRequest2 = new QuoteRequestModelBuilder() {
						{
							create(QuotesUtils
									.strToDateIgnoreException("2013-06-12T11:31:10.953296"),
									"COMPLETED",
									new Long(1311),
									"{\"have_welder_01\": \"Yes\", \"how_much_wood_01\": 12.0, \"general_nature_01\": \"Spouting\", \"favourite_color_01\": \"red\"}",
									serviceConsumer2);
							addQuoteSystemPictureModels(systemPicture3,
									systemPicture4);
						}
					}.build();
				}

				// init ServiceProviderQuoteRequestModel
				{
					new ServiceProviderQuoteRequestModelBuilder() {
						{
							create(serviceProvider1,
									quoteRequest1,
									1L,
									"IGNORED",
									QuotesUtils
											.strToDateIgnoreException("2013-06-12T11:31:10.953296"));
						}
					}.build();

					new ServiceProviderQuoteRequestModelBuilder() {
						{
							create(serviceProvider2,
									quoteRequest1,
									2L,
									"DECLINED",
									QuotesUtils
											.strToDateIgnoreException("2013-06-08T09:21:40.953296"));
						}
					}.build();

					new ServiceProviderQuoteRequestModelBuilder() {
						{
							create(serviceProvider3,
									quoteRequest2,
									3L,
									"READ",
									QuotesUtils
											.strToDateIgnoreException("2013-03-22T01:11:10.953296"));
						}
					}.build();

					new ServiceProviderQuoteRequestModelBuilder() {
						{
							create(serviceProvider4,
									quoteRequest2,
									4L,
									"RESPONDING",
									QuotesUtils
											.strToDateIgnoreException("2013-01-02T04:54:10.953296"));
						}
					}.build();
				}
				return null;
			}

			private boolean dataIsAlreadyPresent() {
				return InitialDataSetup.this.entityManager
						.createQuery(
								"select count(c.questionId) from CategoryQuestionModel c",
								Long.class).getSingleResult().longValue() > 0;
			}
		});
		EntityBuilderManager.clearEntityManager();
	}

	private static Date getDateFromFormat(DateFormat dateFormatter,
			String dateStr) {
		Date date = null;
		try {
			date = dateFormatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
