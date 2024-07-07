package edu.nau.epower_auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import edu.nau.epower_auth.dao.Url;

/**
 * 资源路径映射
 * 
 * @ClassName: UrlMapper
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-06 09:22:06
 */
public interface UrlMapper {

	@Select("select ul.id as id, ul.path as path" + " from menu_url mu" + " left join url u on mu.url_id = u.id"
			+ " where mu.menu_id = #{menuId}")
	public List<Url> findUrlByMenuId(@Param("menuId") int menuId);

}
