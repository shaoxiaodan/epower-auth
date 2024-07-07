package edu.nau.epower_auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.nau.epower_auth.dao.User;
import edu.nau.epower_auth.mapper.UserMapper;
import edu.nau.epower_auth.service.UserService;

/**
 * 用户服务层
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

	/**
	 * 添加用户方法(带事务控制)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int add(User user) {
		userMapper.insertUser(user);
		int userId = user.getId();
		return userId;
	}

}
