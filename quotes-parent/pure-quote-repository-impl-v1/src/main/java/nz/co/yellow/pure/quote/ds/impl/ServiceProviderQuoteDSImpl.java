package nz.co.yellow.pure.quote.ds.impl;

import javax.annotation.Resource;

import nz.co.yellow.pure.quote.data.ServiceProviderQuote;
import nz.co.yellow.pure.quote.data.ServiceProviderQuoteModel;
import nz.co.yellow.pure.quote.data.repository.ServiceProviderQuoteRepository;
import nz.co.yellow.pure.quote.ds.NotFoundException;
import nz.co.yellow.pure.quote.ds.ServiceProviderQuoteDS;
import nz.co.yellow.pure.quote.ds.converter.ServiceProviderQuoteConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("serviceProviderQuoteDs")
@Transactional(readOnly = true)
public class ServiceProviderQuoteDSImpl implements ServiceProviderQuoteDS {

	@Resource
	private ServiceProviderQuoteConverter serviceProviderQuoteConverter;

	@Resource
	private ServiceProviderQuoteRepository serviceProviderQuoteRepository;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServiceProviderQuoteDSImpl.class);

	@Override
	public ServiceProviderQuote getServiceProviderQuoteById(
			Long serviceProviderQuoteId) throws Exception {
		LOGGER.debug("getServiceProviderQuoteById start:{}",
				serviceProviderQuoteId);
		ServiceProviderQuote result = null;
		ServiceProviderQuoteModel serviceProviderQuoteModel = this
				.getServiceProviderQuoteModelById(serviceProviderQuoteId);
		result = serviceProviderQuoteConverter
				.convertFrom(serviceProviderQuoteModel);
		LOGGER.debug("getServiceProviderQuoteById end:{}", result);
		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public ServiceProviderQuote updateServiceProviderQuoteStatus(
			Long serviceProviderQuoteId, String status) throws Exception {
		LOGGER.debug("updateServiceProviderQuoteStatus start:{}",
				serviceProviderQuoteId);
		ServiceProviderQuote result = null;
		ServiceProviderQuoteModel serviceProviderQuoteModel = this
				.getServiceProviderQuoteModelById(serviceProviderQuoteId);
		serviceProviderQuoteModel.setStatus(status);
		result = serviceProviderQuoteConverter
				.convertFrom(serviceProviderQuoteModel);
		LOGGER.debug("updateServiceProviderQuoteStatus end:{}", result);
		return result;
	}

	private ServiceProviderQuoteModel getServiceProviderQuoteModelById(
			Long serviceProviderQuoteId) throws NotFoundException {
		ServiceProviderQuoteModel serviceProviderQuoteModel = null;
		try {
			serviceProviderQuoteModel = serviceProviderQuoteRepository
					.findOne(serviceProviderQuoteId);
		} catch (Exception e) {
			throw new NotFoundException(
					"ServiceProviderQuoteModel not found with id["
							+ serviceProviderQuoteId + "]");
		}
		return serviceProviderQuoteModel;
	}

}
