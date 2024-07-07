package edu.nau.epower_auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nau.epower_auth.dao.Role;
import edu.nau.epower_auth.mapper.RoleMapper;
import edu.nau.epower_auth.service.RoleService;

/**
 * 角色服务实现
 * 
 * @ClassName: RoleServiceImpl
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-07 12:31:34
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	/**
	 * 角色列表
	 */
	@Override
	public List<Role> listRole() {
		return roleMapper.listRole();
	}

	/**
	 * 添加角色
	 */
	@Override
	public int addRole(Role role) {
		return roleMapper.addRole(role);
	}

	/**
	 * 更新角色
	 */
	@Override
	public int updateRole(Role role) {
		return roleMapper.updateRole(role);
	}

	/**
	 * 删除角色
	 */
	@Override
	public int removeRole(int roleId) {
		return roleMapper.removeRole(roleId);
	}

}
