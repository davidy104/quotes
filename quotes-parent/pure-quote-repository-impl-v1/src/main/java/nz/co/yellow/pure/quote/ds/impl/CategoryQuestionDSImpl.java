package nz.co.yellow.pure.quote.ds.impl;

import static nz.co.yellow.pure.quote.data.predicate.CategoryQuestionPredicates.findByCategoryId;
import static nz.co.yellow.pure.quote.data.predicate.CategoryQuestionPredicates.findByQuestionIds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import nz.co.yellow.pure.quote.data.CategoryQuestion;
import nz.co.yellow.pure.quote.data.CategoryQuestionModel;
import nz.co.yellow.pure.quote.data.repository.CategoryQuestionRepository;
import nz.co.yellow.pure.quote.ds.CategoryQuestionDS;
import nz.co.yellow.pure.quote.ds.NotFoundException;
import nz.co.yellow.pure.quote.ds.converter.CategoryQuestionConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("categoryQuestionDs")
@Transactional(readOnly = true)
public class CategoryQuestionDSImpl implements CategoryQuestionDS {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CategoryQuestionDSImpl.class);

	@Resource
	private CategoryQuestionConverter categoryQuestionConverter;

	@Resource
	private CategoryQuestionRepository categoryQuestionRepository;

	@Override
	@Transactional(readOnly = false)
	public CategoryQuestion createCategoryQuestion(
			CategoryQuestion categoryQuestion) throws Exception {
		LOGGER.debug("createCategoryQuestion start:{}", categoryQuestion);
		CategoryQuestion addedDto = null;
		CategoryQuestionModel model = categoryQuestionConverter
				.convertTo(categoryQuestion);
		CategoryQuestionModel result = categoryQuestionRepository.save(model);
		LOGGER.debug("after save:{}", result);
		addedDto = categoryQuestionConverter.convertFrom(model);
		LOGGER.debug("after convert:{}", addedDto);
		LOGGER.debug("createCategoryQuestion end:{}");
		return addedDto;
	}

	@Override
	@Transactional(readOnly = false)
	public CategoryQuestion updateCategoryQuestion(Long categoryQuestionId,
			CategoryQuestion categoryQuestion) throws Exception {
		LOGGER.debug("updateCategoryQuestion start:{}", categoryQuestion);
		LOGGER.debug("categoryQuestionId:{}", categoryQuestionId);

		CategoryQuestion updatedDto = null;

		CategoryQuestionModel foundOne = categoryQuestionRepository
				.findOne(categoryQuestionId);
		if (foundOne == null) {
			throw new NotFoundException(
					"CategoryQuestionModel can not be found with id["
							+ categoryQuestionId + "]");
		}

		foundOne.update(categoryQuestion.getSystemName(),
				categoryQuestion.getWording(), categoryQuestion.getDataType(),
				categoryQuestion.getParameters(),
				categoryQuestion.getOrdinal(), categoryQuestion.getCategory());

		LOGGER.debug("after update:{}", foundOne);
		updatedDto = categoryQuestionConverter.convertFrom(foundOne);
		return updatedDto;
	}

	@Override
	public CategoryQuestion getCategoryQuestionById(Long categoryQuestionId)
			throws Exception {
		LOGGER.debug("getCategoryQuestionById start:{}", categoryQuestionId);
		CategoryQuestionModel foundOne = null;
		CategoryQuestion foundDto = null;
		foundOne = categoryQuestionRepository.findOne(categoryQuestionId);
		if (foundOne == null) {
			throw new NotFoundException(
					"CategoryQuestionModel can not be found with id["
							+ categoryQuestionId + "]");
		}
		LOGGER.debug("find categoryQuestionModel:{}", foundOne);
		foundDto = categoryQuestionConverter.convertFrom(foundOne);
		return foundDto;
	}

	@Override
	public List<CategoryQuestion> getCategoryQuestionsByCateId(Long categoryId)
			throws Exception {
		LOGGER.debug("getCategoryQuestionsByCateId start:{}", categoryId);
		List<CategoryQuestion> resultDtoList = null;

		Iterable<CategoryQuestionModel> categoryQuestionModelIterable = categoryQuestionRepository
				.findAll(findByCategoryId(categoryId));
		if (categoryQuestionModelIterable != null) {
			Iterator<CategoryQuestionModel> modelIterator = categoryQuestionModelIterable
					.iterator();
			resultDtoList = new ArrayList<CategoryQuestion>();
			while (modelIterator.hasNext()) {
				CategoryQuestionModel model = modelIterator.next();
				resultDtoList.add(categoryQuestionConverter.convertFrom(model));
			}
			LOGGER.debug("get resultList size:{}", resultDtoList.size());
		}

		LOGGER.debug("getCategoryQuestionsByIds end:{}");
		return resultDtoList;
	}

	@Override
	public List<CategoryQuestion> getCategoryQuestionsByIds(
			List<Long> cateQuestionIds) throws Exception {
		LOGGER.debug("getCategoryQuestionsByIds start:{}", cateQuestionIds);
		List<CategoryQuestion> resultDtoList = null;

		Iterable<CategoryQuestionModel> categoryQuestionModelIterable = categoryQuestionRepository
				.findAll(findByQuestionIds(cateQuestionIds));
		if (categoryQuestionModelIterable != null) {
			Iterator<CategoryQuestionModel> modelIterator = categoryQuestionModelIterable
					.iterator();
			resultDtoList = new ArrayList<CategoryQuestion>();
			while (modelIterator.hasNext()) {
				CategoryQuestionModel model = modelIterator.next();
				resultDtoList.add(categoryQuestionConverter.convertFrom(model));
			}
			LOGGER.debug("get resultList size:{}", resultDtoList.size());
		}
		LOGGER.debug("getCategoryQuestionsByIds end:{}");
		return resultDtoList;
	}

}
