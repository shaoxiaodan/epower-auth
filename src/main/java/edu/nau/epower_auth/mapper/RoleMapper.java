package edu.nau.epower_auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import edu.nau.epower_auth.dao.Role;

/**
 * 角色映射
 * 
 * @ClassName: RoleMapper
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-06 09:56:26
 */
public interface RoleMapper {

	/*
	 * 根据用户id，查出所有角色，并装配菜单menu
	 */
	@Select("SELECT r.id as id, r.name as name, r.description as description, r.create_time as create_time, r.update_time as update_time, r.is_root as is_root" 
			+ " FROM user_role ur"
			+ " LEFT JOIN role r on ur.role_id = r.id" 
			+ " WHERE ur.user_id = #{userId}")
	@Results(value = 
			{
					@Result(id = true, property = "id", column = "id"), 
					@Result(property = "name", column = "name"),
					@Result(property = "description", column = "description"),
					@Result(property = "menuList", column = "id", 
						many = @Many(select = "edu.nau.epower_auth.mapper.MenuMapper.findMenuByRoleId", fetchType = FetchType.DEFAULT)) })
	public List<Role> findRoleByUserId(@Param("userId") int userId);

	@Select("SELECT * FROM role")
	public List<Role> listRole();

	@Select("SELECT * FROM role where id = #{roleId}")
	public Role findRole(@Param("roleId") int roleId);
	
	@Insert("INSERT INTO role(name, description) VALUES (#{name}, #{description})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id") // 返回自增id
	public int addRole(Role role);

	@Update("UPDATE role SET name = #{name}, description = #{description}, update_time = CURRENT_TIMESTAMP WHERE id = #{id}")
	public int updateRole(Role role);

	@Delete("DELETE FROM role WHERE id = #{roleId}")
	public int removeRole(@Param("roleId") int roleId);

}
