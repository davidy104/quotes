package nz.co.yellow.pure.quote.ds.impl;

import static nz.co.yellow.pure.quote.data.predicate.ServiceProviderPredicates.findByProviderIds;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import nz.co.yellow.pure.quote.data.QuoteLoadStrategies;
import nz.co.yellow.pure.quote.data.ServiceProviderModel;
import nz.co.yellow.pure.quote.data.ServiceProviderReq;
import nz.co.yellow.pure.quote.data.ServiceProviderResp;
import nz.co.yellow.pure.quote.data.repository.ServiceProviderRepository;
import nz.co.yellow.pure.quote.ds.NotFoundException;
import nz.co.yellow.pure.quote.ds.ServiceProviderDS;
import nz.co.yellow.pure.quote.ds.converter.ServiceProviderConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("serviceProviderDs")
@Transactional(readOnly = true)
public class ServiceProviderDSImpl implements ServiceProviderDS {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServiceProviderDSImpl.class);

	@Resource
	private ServiceProviderRepository serviceProviderRepository;

	@Resource
	private ServiceProviderConverter serviceProviderConverter;

	@Override
	@Transactional(readOnly = false)
	public ServiceProviderResp createServiceProvider(
			ServiceProviderReq serviceProvider) throws Exception {
		LOGGER.debug("createServiceProvider start:{}", serviceProvider);
		ServiceProviderResp result = null;
		ServiceProviderModel added = serviceProviderConverter
				.convertFromRequest(serviceProvider);
		added = serviceProviderRepository.save(added);
		LOGGER.debug("after createServiceProvider:{}", added);
		result = serviceProviderConverter.convertToResponse(added);
		LOGGER.debug("createServiceProvider end:{}", result);
		return result;
	}

	@Override
	public ServiceProviderResp getServiceProviderById(Long providerId,
			QuoteLoadStrategies loadStrategies) throws Exception {
		LOGGER.debug("getServiceProviderById start:{}", providerId);
		LOGGER.debug("loadStrategies:{}", loadStrategies);
		ServiceProviderModel foundModel = null;
		ServiceProviderResp result = null;
		if (providerId != null) {
			foundModel = serviceProviderRepository.findOne(providerId);
			if (foundModel == null) {
				throw new NotFoundException(
						"ServiceProviderModel can not be found with id["
								+ providerId + "]");
			}
			LOGGER.debug("found model:{}", foundModel);

			result = serviceProviderConverter.convertToResponse(foundModel,
					loadStrategies);

		}
		LOGGER.debug("getServiceProviderById end:{}", result);
		return result;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public ServiceProviderResp updateServiceProvider(Long providerId,
			ServiceProviderReq serviceProvider) throws Exception {
		LOGGER.debug("updateServiceProvider start:{}", serviceProvider);
		LOGGER.debug("providerId:{}", providerId);
		ServiceProviderModel foundModel = null;
		ServiceProviderResp result = null;
		if (providerId == null) {
			throw new NotFoundException(
					"ServiceProviderModel id can not be null for update");
		}
		foundModel = serviceProviderRepository.findOne(providerId);
		if (foundModel == null) {
			throw new NotFoundException(
					"ServiceProviderModel can not be found with id["
							+ providerId + "]");
		}
		LOGGER.debug("found model:{}", foundModel);
		foundModel.update(serviceProvider.getUserId(),
				serviceProvider.getCustomerId(),
				new BigDecimal(serviceProvider.getBoostWeight()));
		result = serviceProviderConverter.convertToResponse(foundModel);
		LOGGER.debug("updateServiceProvider end:{}", result);
		return result;
	}

	@Override
	public List<ServiceProviderResp> getServiceProvidersByIds(List<Long> ids,
			QuoteLoadStrategies loadStrategies) throws Exception {
		LOGGER.debug("getServiceProviderModelsByIds start:{}");
		List<ServiceProviderResp> resultList = null;
		if (ids != null && ids.size() > 0) {
			Iterable<ServiceProviderModel> resultIterable = serviceProviderRepository
					.findAll(findByProviderIds(ids));
			if (resultIterable != null) {
				resultList = new ArrayList<ServiceProviderResp>();
				Iterator<ServiceProviderModel> modelIterator = resultIterable
						.iterator();
				while (modelIterator.hasNext()) {
					resultList.add(serviceProviderConverter.convertToResponse(
							modelIterator.next(), loadStrategies));
				}
				LOGGER.debug("get result, size:{}", resultList.size());
			}
		}
		LOGGER.debug("getServiceProviderModelsByIds end:{}");
		return resultList;
	}

}
