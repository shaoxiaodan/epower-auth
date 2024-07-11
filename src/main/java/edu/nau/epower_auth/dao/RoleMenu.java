package edu.nau.epower_auth.dao;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色-菜单关联类
 * 
 * @ClassName: RoleMenu
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-06 10:21:45
 */
public class RoleMenu implements Serializable {

	private int id;

	private int roleId;

	private int menuId;

	private Date createTime;

	private String description;

	public RoleMenu() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
