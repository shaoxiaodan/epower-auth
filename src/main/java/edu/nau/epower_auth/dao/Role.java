package edu.nau.epower_auth.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 角色类
 * 
 * @ClassName: Role
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-06 09:53:55
 */
public class Role implements Serializable {

	private int id;

	private String name;

	private String description;

	private List<Menu> menuList;

	public Role() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

}
