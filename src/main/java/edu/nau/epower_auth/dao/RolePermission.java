package edu.nau.epower_auth.dao;

/**
 * 
 * @ClassName: RolePermission
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-06 01:29:54
 */
public class RolePermission {

	private int id;
	
	private int roleId;
	
	private int permissionId;

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

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}
	
	
}
