package edu.nau.epower_auth.service.impl;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.nau.epower_auth.dao.User;
import edu.nau.epower_auth.service.UserService;

@SpringBootTest
public class UserServiceImplTest {

	@Autowired
	private UserService userServicel;

	@Test
	void testAdd() {
//		fail("Not yet implemented");
		User user = new User();
		user.setUsername("Mybatis");
		user.setPassword("123456");
		user.setCreateTime(new Date());

		try {
			/**
			 * 异常操作 测试事务控制是否生效
			 */
			int errorCode = 12 / 0;

			int insert = userServicel.add(user);
			int userId = user.getId();
			System.out.println("UserServiceImplTest::insert=" + insert + "\tuserId=" + userId);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
