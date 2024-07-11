package edu.nau.epower_auth.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import edu.nau.epower_auth.dao.MenuUrl;

/**
 * 菜单-资源路径映射
 * 
 * @ClassName: MenuUrlMapper
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-07 07:25:22
 */
public interface MenuUrlMapper {

	@Select("SELECT * FROM menu_url WHERE menu_id = #{menuId}")
	public List<MenuUrl> getMenuUrl(int menuId);

	@Insert("insert into menu_url(menu_id, url_id) values(#{menuId}, #{urlId})")
	public int insertMenuUrl(MenuUrl menuUrl);

	@Delete("delete from menu_url where menu_id = #{menuId}")
	public int deleteMenuUrl(MenuUrl menuUrl);

	@InsertProvider(type = SqlProvider.class, method = "insertBatch")
	public int insertMenuUrlBatch(List<MenuUrl> menuUrlList);

	@DeleteProvider(type = SqlProvider.class, method = "deleteBatch")
	public int deleteMenuUrlBatch(List<MenuUrl> menuUrlList);

	/**
	 * SQL提供者
	 * 
	 * @ClassName: SqlProvider
	 * @Description: TODO
	 * @author Xiaodan Shao(xs94@nau.edu)
	 * @date 2024-07-07 07:25:47
	 */
	class SqlProvider {

		// 批量添加
		public String insertBatch(Map map) {
			List<MenuUrl> muList = (List<MenuUrl>) map.get("list");
			StringBuilder sb = new StringBuilder();
			sb.append("insert into menu_url (menu_id,url_id) values ");
			MessageFormat mf = new MessageFormat("(#'{'list[{0}].menuId}, #'{'list[{0}].urlId})");

			for (int i = 0; i < muList.size(); i++) {
				sb.append(mf.format(new Object[] { i }));
				if (i < muList.size() - 1)
					sb.append(",");
			}
			return sb.toString();
		}

		// 批量删除
		public String deleteBatch(Map map) {
			List<MenuUrl> muList = (List<MenuUrl>) map.get("list");
			StringBuilder sb = new StringBuilder();

			sb.append("delete from menu_url where menu_id in (");
			for (int i = 0; i < muList.size(); i++) {
				sb.append("'").append(muList.get(i).getMenuId()).append("'");
				if (i < muList.size() - 1)
					sb.append(",");
			}
			sb.append(")");
			return sb.toString();
		}

	}
}
