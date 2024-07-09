package edu.nau.epower_auth.service;

import java.util.List;

import edu.nau.epower_auth.dao.Role;
import edu.nau.epower_auth.dao.RoleMenu;

/**
 * 角色服务接口
 * 
 * @ClassName: RoleService
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-07 12:30:50
 */
public interface RoleService {

	// 角色列表
	public List<Role> listRole();

	// 获取角色
	public Role getRole(int roleId);
	
	// 添加角色
	public int addRole(Role role);

	// 更新角色
	public int updateRole(Role role);

	// 删除角色
	public int removeRole(int roleId);

	// 添加角色菜单授权
	public int addRoleMenuAuth(RoleMenu roleMenu);

	// 删除角色菜单授权
	public int removeRoleMenuAuth(RoleMenu roleMenu);

}
