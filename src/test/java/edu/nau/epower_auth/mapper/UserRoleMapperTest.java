package edu.nau.epower_auth.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.nau.epower_auth.dao.UserRole;

@SpringBootTest
public class UserRoleMapperTest {

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Test
	public void testInsertUserRole() {

		int userId = 0;
		int roleId = 0;
		String remarks = "";

//		userId = 1; // aaa
//		userId = 2; // bbb
//		userId = 3; // ccc
		userId = 6; // ddd

//		roleId = 1; // admin
//		roleId = 2; // root
//		roleId = 3; // editor
//		roleId = 4; // tester 1
		roleId = 5; // tester 2

		remarks = new Date().toString();

		UserRole userRole = new UserRole();
		userRole.setUserId(userId);
		userRole.setRoleId(roleId);
		userRole.setRemarks(remarks);

		int insert = userRoleMapper.insertUserRole(userRole);
		System.out.println("testInsertUserRole::insert=" + insert);

		assertEquals(1, insert);
	}

	@Test
	public void testDeleteUserRole() {

		int userId = 0;
		int roleId = 0;

//		userId = 1; // aaa
//		userId = 2; // bbb
//		userId = 3; // ccc
		userId = 6; // ddd

//		roleId = 1; // admin
//		roleId = 2; // root
//		roleId = 3; // editor
//		roleId = 4; // tester 1
		roleId = 5; // tester 2

		UserRole userRole = new UserRole();
		userRole.setUserId(userId);
		userRole.setRoleId(roleId);

		int delete = userRoleMapper.deleteUserRole(userRole);
		System.out.println("testDeleteUserRole::delete=" + delete);

		assertEquals(1, delete);
	}

	@Test
	public void testInsertUserRoleBatch() {

		int userId = 0;
//		userId = 1; //aaa
//		userId = 2; //bbb
//		userId = 3; //ccc
		userId = 6; // ddd

		List<UserRole> userRoleList = new ArrayList<UserRole>();

		for (int i = 1; i <= 5; i++) {
			UserRole ur = new UserRole();
			ur.setUserId(userId);
			ur.setRoleId(i);
			ur.setRemarks("测试批量insert-" + i);
			userRoleList.add(ur);
		}

		int insertBatch = userRoleMapper.insertUserRoleBatch(userRoleList);
		System.out.println("testInsertUserRoleBatch::insertBatch=" + insertBatch);
	}

	@Test
	public void testDeleteUserRoleBatch() {

		int userId = 0;
//		userId = 1; //aaa
//		userId = 2; //bbb
//		userId = 3; //ccc
		userId = 6; // ddd

		List<UserRole> userRoleList = new ArrayList<UserRole>();

		for (int i = 1; i <= 5; i++) {
			UserRole userRole = new UserRole();
			userRole.setUserId(userId);
			userRole.setRoleId(i);
			userRoleList.add(userRole);
		}

		int deleteBatch = userRoleMapper.deleteUserRoleAuthBatch(userRoleList);
		System.out.println("testDeleteUserRoleBatch::deleteBatch=" + deleteBatch);
	}

}
