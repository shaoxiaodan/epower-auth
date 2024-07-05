package edu.nau.epower_auth;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.nau.epower_auth.dao.Permission;
import edu.nau.epower_auth.dao.Role;
import edu.nau.epower_auth.mapper.RoleMapper;

@SpringBootTest
public class RoleMapperTest {

	@Autowired
	private RoleMapper roleMapper;
	
	@Test
	public void testFindRoleListByUserId() {
		
		List<Role> roleList = roleMapper.findRoleListByUserId(3);
		System.out.println("testFindRoleListByUserId, roleList=" + roleList);
		
		if(roleList != null) {
			
			Role role = null;
			List<Permission> permissionList = null;
			
			for(int i=0;i<roleList.size();i++) {
				
				role = roleList.get(i);
				permissionList = role.getPermissionList();
				
				System.out.println("** roleName=" + role.getName());

				if(permissionList != null) {
					
					Permission permission = null;
					String pName = "";
					String pUrl = "";
					for(int j=0;j<permissionList.size();j++) {
						permission = permissionList.get(j);
						pName = permission.getName();
						pUrl = permission.getUrl();
						System.out.println("** pName=" + pName + "\tpUrl=" + pUrl);
					}
				}
			}
		}
		
		
		assertNotNull(roleList);
	}
	
}
