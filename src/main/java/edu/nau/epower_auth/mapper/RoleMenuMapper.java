package edu.nau.epower_auth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

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

	@Select("SELECT * FROM role_menu WHERE role_id = #{roleId} AND menu_id = #{menuId}")
	public RoleMenu getRoleMenu(RoleMenu roleMenu);

	@Insert("INSERT INTO role_menu(role_id, menu_id) VALUES (#{roleId}, #{menuId})")
	public int insertRoleMenu(RoleMenu roleMenu);

	@Delete("DELETE FROM role_menu WHERE role_id = #{roleId} AND menu_id = #{menuId}")
	public int deleteRoleMenu(RoleMenu roleMenu);

	@InsertProvider(type = SqlProvider.class, method = "insertBatch")
	public int insertRoleMenuBatch(List<UserRole> userRoleList);

	@DeleteProvider(type = SqlProvider.class, method = "deleteBatch")
	public int deleteRoleMenuBatch(List<UserRole> userRoleList);

	/*
	 * 批量sql提供者
	 */
	class SqlProvider {

		// 批量添加
		public String insertBatch(Map map) {
			// TODO
			return null;
		}

		// 批量删除
		public String deleteBatch(Map map) {
			// TODO
			return null;
		}

	}
}
