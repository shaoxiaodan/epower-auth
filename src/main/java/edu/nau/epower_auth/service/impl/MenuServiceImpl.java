package edu.nau.epower_auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nau.epower_auth.dao.Menu;
import edu.nau.epower_auth.dao.MenuUrl;
import edu.nau.epower_auth.mapper.MenuMapper;
import edu.nau.epower_auth.mapper.MenuUrlMapper;
import edu.nau.epower_auth.service.MenuService;

/**
 * 菜单-资源路径接口实现
 * 
 * @ClassName: MenuServiceImpl
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-07 11:42:29
 */
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	private MenuUrlMapper menuUrlMapper;

	@Override
	public List<Menu> listMenu() {
		return menuMapper.listMenu();
	}

	@Override
	public int addMenu(Menu menu) {
		return menuMapper.insertMenu(menu);
	}

	@Override
	public int updateMenu(Menu menu) {
		return menuMapper.updateMenu(menu);
	}

	@Override
	public int removeMenu(int menuId) {
		return menuMapper.deleteMenu(menuId);
	}

	@Override
	public int addMenuUrlAuth(MenuUrl menuUrl) {
		return menuUrlMapper.insertMenuUrl(menuUrl);
	}

	@Override
	public int removeMenuUrlAuth(MenuUrl menuUrl) {
		return menuUrlMapper.deleteMenuUrl(menuUrl);
	}

}
