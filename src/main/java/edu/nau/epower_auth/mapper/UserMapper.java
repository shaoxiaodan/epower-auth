package edu.nau.epower_auth.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import edu.nau.epower_auth.dao.User;

public interface UserMapper {

	@Select("selectt * from user where username = #{username}")
	User findByUserName(@Param("username") String username);
	
	@Select("select * from user where id = #{userId}")
	User findById(@Param("userId") int id);
	
	@Select("select * from user where username = #{username} and password = #{pwd}")
	User findByUsernameAndPwd(@Param("username") String username, @Param("pwd") String pwd);
	
}
