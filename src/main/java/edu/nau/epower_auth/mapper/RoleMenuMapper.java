package edu.nau.epower_auth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;

import edu.nau.epower_auth.dao.RoleMenu;
import edu.nau.epower_auth.dao.UserRole;

/**
 * 角色-菜单映射
 * 
 * @ClassName: RoleMenuMapper
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-07 11:38:23
 */
public interface RoleMenuMapper {

	@Insert("insert into role_menu(role_id, menu_id) values(#{roleId}, #{menuId})")
	public int insertRoleMenu(RoleMenu roleMenu);

	@Delete("delete from role_menu where role_id = #{roleId} and menu_id = #{menuId}")
	public int deleteRoleMenu(RoleMenu roleMenu);

	@InsertProvider(type = SqlProvider.class, method = "insertBatch")
	public int insertRoleMenuBatch(List<UserRole> userRoleList);

	@DeleteProvider(type = SqlProvider.class, method = "deleteBatch")
	public int deleteRoleMenuBatch(List<UserRole> userRoleList);

	/**
	 * sql提供者
	 * 
	 * @ClassName: SqlProvider
	 * @Description: TODO
	 * @author Xiaodan Shao(xs94@nau.edu)
	 * @date 2024-07-07 11:39:00
	 */
	class SqlProvider {

		// 批量添加
		public String insertBatch(Map map) {
			return null;
		}

		// 批量删除
		public String deleteBatch(Map map) {
			return null;
		}

	}
}
