package edu.nau.epower_auth.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.nau.epower_auth.dao.UserRole;

@SpringBootTest
public class AuthMapperTest {

	@Autowired
	private AuthMapper authMapper;

	@Test
	public void batchInsertUserRole() {

		int userId = 0;
//		userId = 1; //aaa
//		userId = 2; //bbb
//		userId = 3; //ccc
		userId = 6; //ddd
		List<UserRole> urList = new ArrayList<UserRole>();
		for(int i=1; i<=5; i++) {
			UserRole ur = new UserRole();
			ur.setUserId(userId);
			ur.setRoleId(i);
			ur.setRemarks("测试批量insert-" + i);
			urList.add(ur);
		}
		
		int batchInsert = authMapper.addUserRoleAuth(urList);
		System.out.println("batchInsertUserRole::batchInsert=" + batchInsert);
	}
	
	@Test
	public void batchRemoveUserRole() {
		int userId = 0;
//		userId = 1; //aaa
//		userId = 2; //bbb
//		userId = 3; //ccc
		userId = 6; //ddd
		List<UserRole> urList = new ArrayList<UserRole>();
		for(int i=1; i<=5; i++) {
			UserRole ur = new UserRole();
			ur.setUserId(userId);
			ur.setRoleId(i);
			urList.add(ur);
		}
		
		int batchRemove = authMapper.removeUserRoleAuth(urList);
		System.out.println("batchRemoveUserRole::batchRemove=" + batchRemove);
	}
	
	
}
