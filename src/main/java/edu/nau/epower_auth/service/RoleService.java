package edu.nau.epower_auth.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.nau.epower_auth.dao.Role;

/**
 * 角色服务接口
 * 
 * @ClassName: RoleService
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-07 12:30:50
 */
public interface RoleService {

	public List<Role> listRole();

	public int addRole(Role role);

	public int updateRole(Role role);

	public int removeRole(int roleId);

}
