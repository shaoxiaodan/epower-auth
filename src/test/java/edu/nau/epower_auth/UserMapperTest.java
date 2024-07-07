package edu.nau.epower_auth;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.nau.epower_auth.dao.User;
import edu.nau.epower_auth.mapper.UserMapper;

/**
 * 
 * @ClassName: UserMapperTest
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-06 09:51:01
 */
@SpringBootTest
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testFindUserByUserId() {

		int userId = 0;
		userId = 1; // user = aaa
//		userId = 2; //user = bbb
//		userId = 3; //user = ccc

		User user = userMapper.findUserByUserId(userId);
		System.out.println("testFindByUserId:: user=" + user);
		assertNotNull(user);
	}

	@Test
	public void testFindUserByUserName() {

		String userName = "";
		userName = "aaa";
//		userName = "bbb";
//		userName = "ccc";

		User user = userMapper.findUserByUserName(userName);
		System.out.println("testFindByUserName::user=" + user);
		assertNotNull(user);
	}

	@Test
	public void testFindUserByUserNameAndPwd() {

		String userName = "";
		String pwd = "";

		userName = "aaa";
		pwd = "123";

		User user = userMapper.findUserByUserNameAndPwd(userName, pwd);
		assertNotNull(user);
	}

}
