package nz.co.yellow.pure.quote.test;

import static org.junit.Assert.assertNotNull;
import nz.co.yellow.pure.quote.config.InfrastructureContextConfiguration;
import nz.co.yellow.pure.quote.config.TestDataContextConfiguration;
import nz.co.yellow.pure.quote.data.QuoteLoadStrategies;
import nz.co.yellow.pure.quote.data.ServiceProviderReq;
import nz.co.yellow.pure.quote.data.ServiceProviderResp;
import nz.co.yellow.pure.quote.ds.ServiceProviderDS;

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
public class ServiceProviderDSTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServiceProviderDSTest.class);

	@Autowired
	private ServiceProviderDS serviceProviderDs;

	@Test
	public void testCreate() throws Exception {
		ServiceProviderReq serviceProviderReq = QuotesTestUtils
				.createServiceProviderReq();
		ServiceProviderResp serviceProviderResp = serviceProviderDs
				.createServiceProvider(serviceProviderReq);
		assertNotNull(serviceProviderResp);
		LOGGER.debug("serviceProviderResp:{}", serviceProviderResp);
	}

	@Test
	public void testGet() throws Exception {
		ServiceProviderResp serviceProviderResp = serviceProviderDs
				.getServiceProviderById(1L, QuoteLoadStrategies.ALL);
		assertNotNull(serviceProviderResp);
		LOGGER.debug("serviceProviderResp:{}", serviceProviderResp);
	}

}
