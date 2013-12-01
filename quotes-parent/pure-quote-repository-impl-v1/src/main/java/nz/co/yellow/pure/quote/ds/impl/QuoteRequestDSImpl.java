package nz.co.yellow.pure.quote.ds.impl;

import static nz.co.yellow.pure.quote.data.predicate.ServiceConsumerPredicates.findByConsumerUserId;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nz.co.yellow.pure.quote.data.QuoteLoadStrategies;
import nz.co.yellow.pure.quote.data.QuoteRequestModel;
import nz.co.yellow.pure.quote.data.QuoteRequestReq;
import nz.co.yellow.pure.quote.data.QuoteRequestResp;
import nz.co.yellow.pure.quote.data.ServiceConsumerModel;
import nz.co.yellow.pure.quote.data.ServiceConsumerReq;
import nz.co.yellow.pure.quote.data.ServiceProviderModel;
import nz.co.yellow.pure.quote.data.ServiceProviderQuote;
import nz.co.yellow.pure.quote.data.ServiceProviderQuoteModel;
import nz.co.yellow.pure.quote.data.repository.QuoteRequestRepository;
import nz.co.yellow.pure.quote.data.repository.ServiceConsumerRepository;
import nz.co.yellow.pure.quote.data.repository.ServiceProviderQuoteRepository;
import nz.co.yellow.pure.quote.data.repository.ServiceProviderRepository;
import nz.co.yellow.pure.quote.ds.NotFoundException;
import nz.co.yellow.pure.quote.ds.QuoteRequestDS;
import nz.co.yellow.pure.quote.ds.converter.QuoteRequestConverter;
import nz.co.yellow.pure.quote.ds.converter.ServiceConsumerConverter;
import nz.co.yellow.pure.quote.ds.converter.ServiceProviderConverter;
import nz.co.yellow.pure.quote.ds.converter.ServiceProviderQuoteConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("quoteRequestDs")
@Transactional(readOnly = true)
public class QuoteRequestDSImpl implements QuoteRequestDS {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(QuoteRequestDSImpl.class);

	@Resource
	private QuoteRequestRepository quoteRequestRepository;

	@Resource
	private QuoteRequestConverter quoteRequestConverter;

	@Resource
	private ServiceProviderQuoteRepository serviceProviderQuoteRepository;

	@Resource
	private ServiceConsumerRepository serviceConsumerRepository;

	@Resource
	private ServiceConsumerConverter serviceConsumerConverter;

	@Resource
	private ServiceProviderQuoteConverter serviceProviderQuoteConverter;

	@Resource
	private ServiceProviderRepository serviceProviderRepository;

	@Resource
	private ServiceProviderConverter serviceProviderConverter;

	@Override
	@Transactional(readOnly = false)
	public QuoteRequestResp createQuoteRequest(QuoteRequestReq quoteRequestReq)
			throws Exception {
		LOGGER.debug("createQuoteRequest start:{}", quoteRequestReq);
		QuoteRequestResp result = null;
		ServiceConsumerModel serviceConsumerModel = null;
		ServiceProviderQuoteModel serviceProviderQuoteModel = null;
		ServiceProviderModel serviceProviderModel = null;
		List<ServiceProviderQuoteModel> serviceProviderQuoteModels = null;
		QuoteRequestModel quoteRequestModel = quoteRequestConverter
				.convertFromRequest(quoteRequestReq);

		ServiceConsumerReq consumer = quoteRequestReq.getServiceConsumer();

		serviceConsumerModel = serviceConsumerRepository
				.findOne(findByConsumerUserId(consumer.getUserId()));
		if (serviceConsumerModel == null) {
			serviceConsumerModel = this.serviceConsumerConverter
					.convertFromRequest(consumer);
			serviceConsumerRepository.save(serviceConsumerModel);
		}

		quoteRequestModel.setServiceConsumer(serviceConsumerModel);

		List<ServiceProviderQuote> serviceProviderQuotes = quoteRequestReq
				.getServiceProviderQuotes();
		if (serviceProviderQuotes != null && serviceProviderQuotes.size() > 0) {
			serviceProviderQuoteModels = new ArrayList<ServiceProviderQuoteModel>();
			for (ServiceProviderQuote serviceProviderQuote : serviceProviderQuotes) {
				serviceProviderModel = getServiceProviderModelById(serviceProviderQuote
						.getServiceProviderId());
				serviceProviderQuoteModel = this.serviceProviderQuoteConverter
						.convertTo(serviceProviderQuote);
				serviceProviderQuoteModel.setQuoteRequest(quoteRequestModel);
				serviceProviderQuoteModel
						.setServiceProvider(serviceProviderModel);
				serviceProviderQuoteModels.add(serviceProviderQuoteModel);
			}
			quoteRequestModel.setProviderQuotesList(serviceProviderQuoteModels);
		}

		quoteRequestModel = quoteRequestRepository.save(quoteRequestModel);
		result = this.quoteRequestConverter
				.convertToResponse(quoteRequestModel);
		LOGGER.debug("createQuoteRequest end:{}", result);
		return result;
	}

	private ServiceProviderModel getServiceProviderModelById(Long providerId)
			throws NotFoundException {
		if (providerId == null) {
			throw new NotFoundException("providerId can not be null");
		}
		ServiceProviderModel serviceProviderModel = serviceProviderRepository
				.findOne(providerId);
		if (serviceProviderModel == null) {
			throw new NotFoundException(
					"ServiceProviderModel not found with id[" + providerId
							+ "]");
		}
		return serviceProviderModel;
	}

	@Override
	@Transactional(readOnly = false)
	public QuoteRequestResp updateQuoteRequestStatus(Long quoteRequestId,
			String status) throws Exception {
		LOGGER.debug("updateQuoteRequest start:{}", quoteRequestId);
		QuoteRequestResp result = null;
		QuoteRequestModel quoteRequestModel = this
				.getQuoteRequestModelById(quoteRequestId);

		quoteRequestModel.setStatus(status);
		result = this.quoteRequestConverter
				.convertToResponse(quoteRequestModel);
		LOGGER.debug("updateQuoteRequest end:{}", result);
		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public void addProviderQuotes(Long quoteReqId,
			List<ServiceProviderQuote> serviceProviderQuotes) throws Exception {
		LOGGER.debug("addProviderQuotes start:{}", quoteReqId);
		QuoteRequestModel quoteRequestModel = this
				.getQuoteRequestModelById(quoteReqId);
		ServiceProviderModel serviceProviderModel = null;

		for (ServiceProviderQuote serviceProviderQuote : serviceProviderQuotes) {
			serviceProviderModel = this
					.getServiceProviderModelById(serviceProviderQuote
							.getServiceProviderId());
			ServiceProviderQuoteModel serviceProviderQuoteModel = serviceProviderQuoteConverter
					.convertTo(serviceProviderQuote);
			serviceProviderQuoteModel.setServiceProvider(serviceProviderModel);
			serviceProviderQuoteModel.setQuoteRequest(quoteRequestModel);
			serviceProviderQuoteRepository.save(serviceProviderQuoteModel);
		}

	}

	private QuoteRequestModel getQuoteRequestModelById(Long quoteRequestId)
			throws NotFoundException {
		QuoteRequestModel quoteRequestModel = quoteRequestRepository
				.findOne(quoteRequestId);
		if (quoteRequestModel == null) {
			throw new NotFoundException("QuoteRequestModel not found with id["
					+ quoteRequestId + "]");
		}
		return quoteRequestModel;
	}

	@Override
	public QuoteRequestResp getQuoteRequestById(Long quoteRequestId,
			QuoteLoadStrategies loadStrategies) throws Exception {
		LOGGER.debug("getQuoteRequestById start:{}", quoteRequestId);
		QuoteRequestResp result = null;
		QuoteRequestModel quoteRequestModel = this
				.getQuoteRequestModelById(quoteRequestId);
		result = this.quoteRequestConverter.convertToResponse(
				quoteRequestModel, QuoteLoadStrategies.ALL);
		LOGGER.debug("getQuoteRequestById end:{}", result);
		return result;
	}

}
