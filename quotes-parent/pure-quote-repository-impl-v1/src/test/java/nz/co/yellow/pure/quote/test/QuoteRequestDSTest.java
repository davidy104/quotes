package nz.co.yellow.pure.quote.test;

import static org.junit.Assert.assertNotNull;
import nz.co.yellow.pure.quote.config.InfrastructureContextConfiguration;
import nz.co.yellow.pure.quote.config.TestDataContextConfiguration;
import nz.co.yellow.pure.quote.data.QuoteLoadStrategies;
import nz.co.yellow.pure.quote.data.QuoteRequestReq;
import nz.co.yellow.pure.quote.data.QuoteRequestResp;
import nz.co.yellow.pure.quote.ds.QuoteRequestDS;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { InfrastructureContextConfiguration.class,
		TestDataContextConfiguration.class })
public class QuoteRequestDSTest {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(QuoteRequestDSTest.class);

	@Autowired
	private QuoteRequestDS quoteRequestDs;

	@Test
	public void testCreate() throws Exception {
		QuoteRequestReq quoteRequestReq = QuotesTestUtils
				.createQuoteRequestReq();
		QuoteRequestResp quoteRequestResp = quoteRequestDs
				.createQuoteRequest(quoteRequestReq);
		assertNotNull(quoteRequestResp);
		LOGGER.debug("quoteRequestResp:{}", quoteRequestResp);
	}

	@Test
	public void testGet() throws Exception {
		QuoteRequestResp quoteRequestResp = quoteRequestDs.getQuoteRequestById(
				1L, QuoteLoadStrategies.ALL);
		assertNotNull(quoteRequestResp);
		LOGGER.debug("quoteRequestResp:{}", quoteRequestResp);
	}

}
