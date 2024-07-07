package edu.nau.epower_auth.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import edu.nau.epower_auth.dao.User;

/**
 * 
 * @ClassName: UserMapper
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-06 09:04:18
 */
public interface UserMapper {

	@Select("selectt * from user where username = #{username}")
	public User findByUserName(@Param("username") String username);

	@Select("select * from user where id = #{userId}")
	public User findById(@Param("userId") int id);

	@Select("select * from user where username = #{username} and password = #{pwd}")
	public User findByUsernameAndPwd(@Param("username") String username, @Param("pwd") String pwd);

}
