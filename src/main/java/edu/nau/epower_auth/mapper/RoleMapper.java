package edu.nau.epower_auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import edu.nau.epower_auth.dao.Role;

public interface RoleMapper {

	@Select("select r.id as id, r.name as name, r.description as description "
			+ "from user_role ur "
			+ "left join role r on ur.role_id = r.id "
			+ "where ur.user_id = #{userId}")
	@Results(
			value = {
					@Result(id = true, property = "id", column = "id"),
					@Result(property = "name", column = "name"),
					@Result(property = "description", column = "description"),
					@Result(property = "permissionList", column = "id",
					many = @Many(select = "edu.nau.dks_epower.mapper.PermissionMapper.findPermissionByRoleId", fetchType = FetchType.DEFAULT)
					)
			}
	)
	List<Role> findRoleListByUserId(@Param("userId") int userId);
	
}
