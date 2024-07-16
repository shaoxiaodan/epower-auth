package edu.nau.epower_auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import edu.nau.epower_auth.dao.Role;
import edu.nau.epower_auth.dao.User;

/**
 * 用户映射
 * 
 * @ClassName: UserMapper
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-06 09:04:18
 */
public interface UserMapper {

	@Select("SELECT * FROM user WHERE id = #{userId}")
	public User findUserByUserId(@Param("userId") int id);

	@Select("SELECT * FROM user WHERE username = #{userName}")
	public User findUserByUserName(@Param("userName") String username);

	@Select("SELECT * FROM user WHERE username = #{userName} AND password = #{pwd}")
	public User findUserByUserNameAndPwd(@Param("userName") String username, @Param("pwd") String password);

	@Select("SELECT * FROM user")
	public List<User> listUser();

	@Select("SELECT * FROM user WHERE id != #{userId}")
	public List<User> listUserNotMe(@Param("userId") int userId);

	@SelectProvider(type = SqlProvider.class, method = "listUserButMeBatch")
	public List<User> listUserNotMeAndRoot(int userId, Role defRole);

	@Insert("INSERT INTO user(username, password, description) VALUES (#{username}, #{password}, #{description})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id") // 返回自增id
	public int insertUser(User user);

	@Update("UPDATE user SET username = #{username}, password = #{password}, description = #{description}, update_time = CURRENT_TIMESTAMP WHERE id = #{id}")
	public int updateUser(User user);

	@Delete("delete FROM user WHERE id = #{userId}")
	public int deleteUser(@Param("userId") int id);

	/*
	 * 批量SQL提供者
	 */
	class SqlProvider {
		public String listUserButMeBatch(int userId, Role defRole) {
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from user ");
			sql.append(" where 1=1 ");
			sql.append(" and id not in ( ");
			sql.append(" select u.id from user u ");
			sql.append(" left join user_role ur on u.id = ur.user_id ");
			sql.append(" left join role r on ur.role_id = r.id ");
			sql.append(" where 1=1 ");
			sql.append(" and u.id != " + userId);
			sql.append(" and r.level < " + defRole.getLevel());
			sql.append(" ) ");
			sql.append(" and id !=" + userId);

//			System.out.println(" >>>>> listUserButMeBatch -- sql=" + sql.toString());
			return sql.toString();
		}
	}

}
