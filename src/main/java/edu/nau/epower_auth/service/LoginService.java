package edu.nau.epower_auth.service;

import edu.nau.epower_auth.dao.User;

public interface LoginService {

	public User findUserByUserName(String userName);

	public User findUserByUserNameAndPwd(String userName, String passWord);
}
