package edu.nau.epower_auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

	@Select("select ul.id as id, ul.path as path" 
			+ " from menu_url mu" 
			+ " left join url ul on mu.url_id = ul.id"
			+ " where mu.menu_id = #{menuId}")
	public List<Url> findUrlByMenuId(@Param("menuId") int menuId);

	@Select("select * from url")
	public List<Url> listUrl();
	
	@Insert("insert into url(path, description) values(#{path}, #{description})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id") //返回自增id
	public int addUrl(Url url);
	
	@Update("update url set path = #{path}, description = #{description} where id = #{id}")
	public int updateUrl(Url url);
	
	@Delete("delete from url where id = #{urlId}")
	public int removeUrl(@Param("urlId") int urlId);
	
}
