package edu.nau.epower_auth.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import edu.nau.epower_auth.dao.UserRole;

/**
 * 用户-角色映射
 * 
 * @ClassName: UserRoleMapper
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-07 10:50:18
 */
public interface UserRoleMapper {

	@Select("SELECT * FROM user_role WHERE user_id = #{userId} AND role_id = #{roleId}")
	public UserRole getUserRole(UserRole userRole);

	@Insert("INSERT INTO user_role(user_id, role_id) VALUES(#{userId}, #{roleId})")
	public int insertUserRole(UserRole userRole);

	@Delete("DELETE FROM user_role WHERE user_id = #{userId} AND role_id = #{roleId}")
	public int deleteUserRole(UserRole userRole);

	@InsertProvider(type = SqlProvider.class, method = "insertBatch")
	public int insertUserRoleBatch(List<UserRole> userRoleList);

	@DeleteProvider(type = SqlProvider.class, method = "deleteBatch")
	public int deleteUserRoleBatch(List<UserRole> userRoleList);

	/**
	 * 批量SQL处理提供者 - 内部类
	 * 
	 * @ClassName: SqlProvider
	 * @Description: TODO
	 * @author Xiaodan Shao(xs94@nau.edu)
	 * @date 2024-07-07 10:58:44
	 */
	class SqlProvider {
		// 批量添加
		public String insertBatch(Map map) {

			List<UserRole> urList = (List<UserRole>) map.get("list");
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO user_role (user_id,role_id) VALUES ");
			MessageFormat mf = new MessageFormat("(#'{'list[{0}].userId}, #'{'list[{0}].roleId})");

			for (int i = 0; i < urList.size(); i++) {
				sb.append(mf.format(new Object[] { i }));
				if (i < urList.size() - 1)
					sb.append(",");
			}
			return sb.toString();
		}

		// 批量删除
		public String deleteBatch(Map map) {

			List<UserRole> urList = (List<UserRole>) map.get("list");
			StringBuilder sb = new StringBuilder();

			sb.append("DELETE FROM user_role WHERE id IN (");
			for (int i = 0; i < urList.size(); i++) {
				sb.append("'").append(urList.get(i).getId()).append("'");
				if (i < urList.size() - 1)
					sb.append(",");
			}
			sb.append(")");
			return sb.toString();
		}
	}
}
