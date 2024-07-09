package edu.nau.epower_auth.service;

import java.util.List;

import edu.nau.epower_auth.dao.Menu;
import edu.nau.epower_auth.dao.MenuUrl;

/**
 * 菜单服务接口
 * 
 * @ClassName: MenuService
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-07 11:36:40
 */
public interface MenuService {

	// 菜单列表
	public List<Menu> listMenu();

	// 获取菜单
	public Menu getMenu(int menuId);
	
	// 添加菜单
	public int addMenu(Menu menu);

	// 更新菜单
	public int updateMenu(Menu menu);

	// 删除菜单
	public int removeMenu(int menuId);

	// 添加菜单资源路径授权
	public int addMenuUrlAuth(MenuUrl menuUrl);

	// 删除菜单资源路径授权
	public int removeMenuUrlAuth(MenuUrl menuUrl);
}
