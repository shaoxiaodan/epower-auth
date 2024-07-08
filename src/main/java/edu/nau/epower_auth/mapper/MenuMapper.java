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

import edu.nau.epower_auth.dao.Menu;

/**
 * 菜单映射
 * 
 * @ClassName: MenuMapper
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-06 09:24:18
 */
public interface MenuMapper {

	/**
	 * 根据角色id，获取所有菜单，并装配资源路径url
	 * @param roleId
	 * @return
	 */
	@Select("select m.id as id, m.name as name, m.description as description" 
			+ "	from role_menu rm"
			+ "	left join menu m on rm.menu_id = m.id" 
			+ "	where rm.role_id = #{roleId}")
	@Results(value = 
	{
		@Result(id = true, property = "id", column = "id"), 
		@Result(property = "path", column = "path"),
		@Result(property = "description", column = "description"),
		@Result(property = "urlList", column = "id", 
			many = @Many(select = "edu.nau.epower_auth.mapper.UrlMapper.findUrlByMenuId", fetchType = FetchType.DEFAULT)) })
	public List<Menu> findMenuByRoleId(@Param("roleId") int roleId);

	@Select("select * from menu")
	public List<Menu> listMenu();

	@Insert("insert into menu(name, description) values(#{name}, #{description})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id") // 返回自增id
	public int insertMenu(Menu menu);

	@Update("update menu set name = #{name}, description = #{description} where id = #{id}")
	public int updateMenu(Menu menu);

	@Delete("delete from menu where id = #{menuId}")
	public int deleteMenu(@Param("menuId") int menuId);

}
