package edu.nau.epower_auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

	@Select("select * from user where id = #{userId}")
	public User findUserByUserId(@Param("userId") int id);

	@Select("select * from user where username = #{userName}")
	public User findUserByUserName(@Param("userName") String username);

	@Select("select * from user where username = #{userName} and password = #{pwd}")
	public User findUserByUserNameAndPwd(@Param("userName") String username, @Param("pwd") String password);

	@Select("select * from user")
	public List<User> listUser();
	
	@Insert("insert into user(username, password, create_time) values(#{username}, #{password}, #{createTime})")
	public int insertUser(User user);

	@Update("update user set username = #{username}, password = #{password} where id = #{id}")
	public int updateUser(User user);

	@Delete("delete from user where id = #{userId}")
	public int removeUser(@Param("userId") int id);

}
