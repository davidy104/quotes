package nz.co.yellow.pure.quote.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import nz.co.yellow.pure.quote.AbstractAPISupport;
import nz.co.yellow.pure.quote.data.CategoryQuestion;
import nz.co.yellow.pure.quote.ds.CategoryQuestionDS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CategoryQuestionAPI implementation see{CategoryQuestionAPI}
 * 
 * @author david
 * 
 */
@Component("categoryQuestionAPI")
@Path("/pure/quote/categoryQuestion")
public class CategoryQuestionAPIImpl extends AbstractAPISupport implements
		CategoryQuestionAPI {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CategoryQuestionAPIImpl.class);

	@Autowired
	private CategoryQuestionDS categoryQuestionDs;

	@Override
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createCategoryQuestion(CategoryQuestion categoryQuestion) {
		LOGGER.debug("createCategoryQuestion start:{}");
		CategoryQuestion result = null;
		Long id = null;
		try {
			result = categoryQuestionDs
					.createCategoryQuestion(categoryQuestion);
			id = result.getId();
		} catch (Exception e) {
			exceptionHandle(e);
		}

		LOGGER.debug("createCategoryQuestion end:{}");
		return buildResponse(id);
	}

	@Override
	@GET
	@Produces("application/json")
	@Path("/{categoryQuestionId}")
	public Response getCategoryQuestionById(
			@PathParam("categoryQuestionId") Long categoryQuestionId) {
		LOGGER.debug("getCategoryQuestionById start:{}", categoryQuestionId);
		CategoryQuestion resultDto = null;

		try {
			resultDto = categoryQuestionDs
					.getCategoryQuestionById(categoryQuestionId);
		} catch (Exception e) {
			exceptionHandle(e);
		}
		LOGGER.debug("getCategoryQuestionById end:{}");
		return buildResponse(resultDto);
	}

	@Override
	@GET
	@Produces("application/json")
	public Response getCategoryQuestionByIds(
			@QueryParam("idents") List<Long> ids) {
		LOGGER.debug("getCategoryQuestionByIds start:{}");

		return null;
	}

	@Override
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/{categoryQuestionId}")
	public Response updateCategoryQuestion(
			@PathParam("categoryQuestionId") Long categoryQuestionId,
			CategoryQuestion categoryQuestion) {
		LOGGER.debug("updateCategoryQuestion start:{}", categoryQuestionId);
		CategoryQuestion resultDto = null;
		Long id = null;
		try {
			resultDto = this.categoryQuestionDs.updateCategoryQuestion(
					categoryQuestionId, categoryQuestion);
			id = resultDto.getId();
		} catch (Exception e) {
			exceptionHandle(e);
		}

		LOGGER.debug("updateCategoryQuestion end:{}");
		return buildResponse(id);
	}

	@Override
	public Response getCategoryQuestionByCategoryId(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
