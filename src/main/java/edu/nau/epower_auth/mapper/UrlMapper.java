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

	/**
	 * 根据菜单id，获取所有资源路径url
	 * @param menuId
	 * @return
	 */
	@Select("select ul.id as id, ul.path as path,"
			+ " ul.static_path as url_static_path,"
			+ " ul.is_entrance as url_is_entrance,"
			+ " ul.create_time as url_create_time,"
			+ " ul.update_time as url_update_time,"
			+ " ul.description as url_description" 
			+ " from menu_url mu" 
			+ " left join url ul on mu.url_id = ul.id"
			+ " where mu.menu_id = #{menuId}")
	public List<Url> findUrlByMenuId(@Param("menuId") int menuId);

	@Select("SELECT * FROM url")
	public List<Url> listUrl();
	
	@Select("SELECT * FROM url WHERE id = #{urlId}")
	public Url findUrl(@Param("urlId") int urlId);
	
	@Insert("INSERT INTO url(path, static_path, is_entrance, description)"
			+ " VALUES(#{path}, #{staticPath}, #{isEntrance}, #{description})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id") //返回自增id
	public int insertUrl(Url url);
	
	@Update("UPDATE url SET path = #{path},"
			+ " static_path = #{staticPath},"
			+ " update_time = CURRENT_TIMESTAMP,"
			+ " is_entrance = #{isEntrance},"
			+ " description = #{description}"
			+ " WHERE id = #{id}")
	public int updateUrl(Url url);
	
	@Delete("DELETE FROM url WHERE id = #{urlId}")
	public int deleteUrl(@Param("urlId") int urlId);
	
}
