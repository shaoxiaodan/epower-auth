package edu.nau.epower_auth.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import edu.nau.epower_auth.dao.User;

public interface UserService {

	public List<User> list();

	public int add(User user);

	public int update(User user);

	public int remove(int userId);

}
