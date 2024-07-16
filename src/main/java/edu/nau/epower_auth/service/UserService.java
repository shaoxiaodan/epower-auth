package edu.nau.epower_auth.service;

import java.util.List;

import edu.nau.epower_auth.dao.User;
import edu.nau.epower_auth.dao.UserRole;

/**
 * 用户服务接口
 * 
 * @ClassName: UserService
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-07 11:25:27
 */
public interface UserService {

	// 用户列表(过滤当前登录用户数据)
	public List<User> listUser(int userId);

	// 获取用户
	public User getUser(int userId);

	// 添加用户
	public int addUser(User user);

	// 更新用户
	public int updateUser(User user);

	// 删除用户
	public int removeUser(int userId);

	// 检查用户角色是否存在
	public UserRole getUserRole(UserRole userRole);

	// 添加用户角色授权
	public int addUserRoleAuth(UserRole userRole);

	// 删除用户角色授权
	public int removeUserRoleAuth(UserRole userRole);

}
