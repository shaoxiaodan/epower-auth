package edu.nau.epower_auth;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.nau.epower_auth.dao.Permission;
import edu.nau.epower_auth.mapper.PermissionMapper;

@SpringBootTest
public class PermissionMapperTest {

	@Autowired
	private PermissionMapper permissionMapper;
	
	@Test
	public void testfindPermissionByRoleId() {
		List<Permission> pList = permissionMapper.findPermissionByRoleId(3);
		System.out.println("testfindPermissionByRoleId, permission=" + pList);
		assertNotNull(pList);
	}
}
