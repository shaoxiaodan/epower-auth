package edu.nau.epower_auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

	@Select("select m.id as id, m.name as name, m.description as description" + "	from role_menu rm"
			+ "	left join menu m on rm.menu_id = m.id" + "	where rm.role_id = #{roleId}")
	public List<Menu> findMenuByRoleId(@Param("roleId") int roleId);
}
