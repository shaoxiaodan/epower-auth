package edu.nau.epower_auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.nau.epower_auth.dao.User;
import edu.nau.epower_auth.dao.UserRole;
import edu.nau.epower_auth.mapper.UserMapper;
import edu.nau.epower_auth.mapper.UserRoleMapper;
import edu.nau.epower_auth.service.UserService;

/**
 * 用户服务实现
 * 
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-06 11:23:49
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	/**
	 * 用户列表
	 */
	@Override
	public List<User> listUser() {
		return userMapper.listUser();
	}

	/**
	 * 添加用户(with事务控制)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int addUser(User user) {
		userMapper.insertUser(user);
		int userId = user.getId();
		return userId;
	}

	/**
	 * 更新用户((with事务控制))
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}

	/**
	 * 删除用户(with事务控制)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int removeUser(int userId) {
		return userMapper.deleteUser(userId);
	}

	/**
	 * 添加用户-角色授权
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int addUserRoleAuth(UserRole userRole) {
		return userRoleMapper.insertUserRole(userRole);
	}

	/**
	 * 删除用户-角色授权
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int removeUserRoleAuth(UserRole userRole) {
		return userRoleMapper.deleteUserRole(userRole);
	}

}
