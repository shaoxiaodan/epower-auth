package edu.nau.epower_auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;

import edu.nau.epower_auth.dao.UserRole;

/**
 * 授权映射
 * 
 * @ClassName: AuthMapper
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-07 01:23:29
 */
public interface AuthMapper {

	/**
	 * 用户-角色授权
	 * 
	 * @return
	 */
	@InsertProvider(type = SqlProvider.class, method = "batchInsertUserRole")
	public int addUserRoleAuth(List<UserRole> userRoleList);

	/**
	 * 用户-角色撤权
	 * 
	 * @return
	 */
	@DeleteProvider(type = AuthSqlProvider.class, method = "batchRemoveUserRole")
	public int removeUserRoleAuth(List<UserRole> userRoleList);

	// 角色-菜单授权
	public int addRoleMenuAuth();

	public int removeRoleMenuAuth();

	// 菜单-资源路径授权

}
