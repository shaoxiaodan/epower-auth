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

	/**
	 * 根据用户id，查出所有角色，并装配菜单menu
	 * 
	 * @param userId
	 * @return
	 */
	@Select("select r.id as id, r.name as name, r.description as description" 
			+ " from user_role ur"
			+ " left join role r on ur.role_id = r.id" 
			+ " where ur.user_id = #{userId}")
	@Results(value = 
			{
					@Result(id = true, property = "id", column = "id"), 
					@Result(property = "name", column = "name"),
					@Result(property = "description", column = "description"),
					@Result(property = "menuList", column = "id", 
						many = @Many(select = "edu.nau.epower_auth.mapper.MenuMapper.findMenuByRoleId", fetchType = FetchType.DEFAULT)) })
	public List<Role> findRoleByUserId(@Param("userId") int userId);

	@Select("select * from role")
	public List<Role> listRole();

	@Insert("insert into role(name, description) values(#{name}, #{description})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id") // 返回自增id
	public int addRole(Role role);

	@Update("update role set name = #{name}, description = #{description} where id = #{id}")
	public int updateRole(Role role);

	@Delete("delete from role where id = #{roleId}")
	public int removeRole(@Param("roleId") int roleId);

}
