package nz.co.yellow.pure.quote.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import nz.co.yellow.pure.quote.config.InfrastructureContextConfiguration;
import nz.co.yellow.pure.quote.config.TestDataContextConfiguration;
import nz.co.yellow.pure.quote.data.CategoryQuestion;
import nz.co.yellow.pure.quote.ds.CategoryQuestionDS;

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
public class CategoryQuestionDSTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CategoryQuestionDSTest.class);

	@Autowired
	private CategoryQuestionDS categoryQuestionDs;

	@Test
	public void testCreate() throws Exception {
		CategoryQuestion categoryQuestion = QuotesTestUtils
				.createCategoryQuestion();
		categoryQuestion = categoryQuestionDs
				.createCategoryQuestion(categoryQuestion);
		assertNotNull(categoryQuestion);
		LOGGER.debug("categoryQuestion:{}", categoryQuestion);
	}

	@Test
	public void testGetById() throws Exception {
		CategoryQuestion categoryQuestion = categoryQuestionDs
				.getCategoryQuestionById(1L);
		assertNotNull(categoryQuestion);
		LOGGER.debug("get categoryQuestion:{}", categoryQuestion);
	}

	@Test
	public void testUpdate() throws Exception {
		CategoryQuestion categoryQuestion = categoryQuestionDs
				.getCategoryQuestionById(1L);
		assertNotNull(categoryQuestion);
		categoryQuestion.setSystemName("updated");
		categoryQuestion.setCategory(new Long(1321));
		categoryQuestion = categoryQuestionDs.updateCategoryQuestion(1L,
				categoryQuestion);
		assertNotNull(categoryQuestion);
		LOGGER.debug("after update categoryQuestion:{}", categoryQuestion);
	}

	@Test
	public void testGetByCateId() throws Exception {
		List<CategoryQuestion> categoryQuestions = categoryQuestionDs
				.getCategoryQuestionsByCateId(new Long(1311));
		assertNotNull(categoryQuestions);
		LOGGER.debug("categoryQuestions size:{}", categoryQuestions.size());
		for (CategoryQuestion categoryQuestion : categoryQuestions) {
			LOGGER.debug("categoryQuestion:{}", categoryQuestion);
		}
	}
}
