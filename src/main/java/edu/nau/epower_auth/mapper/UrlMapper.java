package edu.nau.epower_auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import edu.nau.epower_auth.dao.Url;

public interface UrlMapper {

	@Select("select ul.id as id, ul.name as name, ul.path as path"
			+ " from menu_url mu"
			+ " left join url u on mu.url_id = u.id"
			+ " where mu.menu_id = #{menuId}")
	public List<Url> findUrlByMenuId(@Param("menuId") int menuId);
	
}
