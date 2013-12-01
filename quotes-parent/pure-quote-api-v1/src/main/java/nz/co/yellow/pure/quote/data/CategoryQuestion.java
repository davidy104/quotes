package nz.co.yellow.pure.quote.data;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class CategoryQuestion implements Serializable {

	private Long id;

	private String systemName;

	private String wording;

	private String dataType;

	private String parameters;

	private Integer ordinal;

	private Long category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
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

		private CategoryQuestion built;

		public Builder(String systemName, String wording, String dataType,
				String parameters, Integer ordinal) {
			built = new CategoryQuestion();
			built.systemName = systemName;
			built.wording = wording;
			built.dataType = dataType;
			built.parameters = parameters;
			built.ordinal = ordinal;
		}

		public Builder(String systemName, String wording, String dataType,
				String parameters, Integer ordinal, Long categoryId) {
			built = new CategoryQuestion();
			built.systemName = systemName;
			built.wording = wording;
			built.dataType = dataType;
			built.parameters = parameters;
			built.ordinal = ordinal;
			built.category = categoryId;
		}

		public CategoryQuestion build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
