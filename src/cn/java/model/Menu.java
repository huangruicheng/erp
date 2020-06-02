package cn.java.model;

import java.util.Set;

/**
 * Menu entity. @author MyEclipse Persistence Tools
 */

public class Menu implements java.io.Serializable {

	// Fields

	private Integer menuId;
	private Integer parentMenuId;
	private String name;
	private String url;
	
	private Set<Menu> menus;
	

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	/** default constructor */
	public Menu() {
	}

	/** full constructor */
	public Menu(Integer parentMenuId, String name, String url) {
		this.parentMenuId = parentMenuId;
		this.name = name;
		this.url = url;
	}

	// Property accessors

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getParentMenuId() {
		return this.parentMenuId;
	}

	public void setParentMenuId(Integer parentMenuId) {
		this.parentMenuId = parentMenuId;
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