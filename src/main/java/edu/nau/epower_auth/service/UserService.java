package edu.nau.epower_auth.service;

import java.util.List;

import edu.nau.epower_auth.dao.User;

public interface UserService {

	public List<User> list();

	public int add(User user);

	public int update(User user);

	public int remove(int userId);

}
