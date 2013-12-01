package nz.co.yellow.pure.quote.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("serial")
@Entity
@Table(name = "category_question")
public class CategoryQuestionModel implements Serializable {

	@Id
	@GeneratedValue(generator = "cqSeq")
	@SequenceGenerator(name = "cqSeq", sequenceName = "CATE_QUESTION_SEQ")
	@Column(name = "question_id", insertable = false, updatable = false)
	private Long questionId;

	@Column(name = "system_name")
	private String systemName;

	@Column(name = "wording")
	private String wording;

	@Column(name = "data_type")
	private String dataType;

	@Column(name = "parameters")
	private String parameters;

	@Column(name = "ordinal")
	private Integer ordinal;

	@Column(name = "category_id")
	private Long categoryId;

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getWording() {
		return wording;
	}

	public void setWording(String wording) {
		this.wording = wording;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public Integer getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void update(String systemName, String wording, String dataType,
			String parameters, Integer ordinal, Long categoryId) {
		this.systemName = systemName;
		this.wording = wording;
		this.dataType = dataType;
		this.parameters = parameters;
		this.ordinal = ordinal;
		this.categoryId = categoryId;
	}

	public static Builder getBuilder(String systemName, String wording,
			String dataType, String parameters, Integer ordinal) {
		return new Builder(systemName, wording, dataType, parameters, ordinal);
	}

	public static Builder getBuilder(String systemName, String wording,
			String dataType, String parameters, Integer ordinal, Long categoryId) {
		return new Builder(systemName, wording, dataType, parameters, ordinal,
				categoryId);
	}

	public static class Builder {

		private CategoryQuestionModel built;

		public Builder(String systemName, String wording, String dataType,
				String parameters, Integer ordinal) {
			built = new CategoryQuestionModel();
			built.systemName = systemName;
			built.wording = wording;
			built.dataType = dataType;
			built.parameters = parameters;
			built.ordinal = ordinal;
		}

		public Builder(String systemName, String wording, String dataType,
				String parameters, Integer ordinal, Long categoryId) {
			built = new CategoryQuestionModel();
			built.systemName = systemName;
			built.wording = wording;
			built.dataType = dataType;
			built.parameters = parameters;
			built.ordinal = ordinal;
			built.categoryId = categoryId;
		}

		public CategoryQuestionModel build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
				.append("questionId", questionId)
				.append("systemName", systemName).append("wording", wording)
				.append("dataType", dataType).append("parameters", parameters)
				.append("ordinal", ordinal).append("categoryId", categoryId)
				.toString();

	}
}
