package edu.nau.epower_auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import edu.nau.epower_auth.dao.Menu;

/**
 * 
 * @ClassName: MenuMapper
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-06 09:24:18
 */
public interface MenuMapper {

	@Select("")
	public List<Menu> findMenuByRoleId(@Param("roleId") int roleId);
}
