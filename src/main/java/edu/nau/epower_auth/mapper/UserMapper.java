package edu.nau.epower_auth.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
	public User findByUserId(@Param("userId") int id);

	@Select("select * from user where username = #{userName}")
	public User findByUserName(@Param("userName") String username);

	@Select("select * from user where username = #{userName} and password = #{pwd}")
	public User findByUserNameAndPwd(@Param("userName") String username, @Param("pwd") String password);

}
