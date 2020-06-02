package cn.java.model;

/**
 * Resource entity. @author MyEclipse Persistence Tools
 */

public class Resource implements java.io.Serializable {

	// Fields

	private Integer resourceId;
	private String name;
	private String url;

	// Constructors

	/** default constructor */
	public Resource() {
	}

	/** full constructor */
	public Resource(String name, String url) {
		this.name = name;
		this.url = url;
	}

	// Property accessors

	public Integer getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}