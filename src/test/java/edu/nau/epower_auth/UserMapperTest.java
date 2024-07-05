package edu.nau.epower_auth;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.nau.epower_auth.dao.User;
import edu.nau.epower_auth.mapper.UserMapper;

@SpringBootTest
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void testFindById() {
		User user = userMapper.findById(1);
		System.out.println("testFindById, user=" + user);
		assertNotNull(user);
//		assertNull(user);
	}
	
	@Test
	public void testFindByName() {
		User user = userMapper.findByUserName("bbb");
		System.out.println("testFindByName, user=" + user);
		assertNotNull(user);
	}
	
	@Test
	public void testFindByNameAndPwd() {
		User user = userMapper.findByUsernameAndPwd("", "");
		assertNotNull(user);
	}
	
	
}
