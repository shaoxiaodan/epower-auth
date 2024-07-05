package edu.nau.epower_auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import edu.nau.epower_auth.dao.Permission;

public interface PermissionMapper {

	@Select("select p.id as id, p.name as name, p.url as url "
			+ "from role_permission rp "
			+ "left join permission p on rp.permission_id = p.id "
			+ "where rp.role_id = #{roleId}")
	List<Permission> findPermissionByRoleId(@Param("roleId") int roleId);
}
