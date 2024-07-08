package edu.nau.epower_auth.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.nau.epower_auth.dao.MenuUrl;

/**
 * 菜单-资源路径单元测试
 * 
 * @ClassName: MenuUrlMapperTest
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-07 07:30:26
 */
@SpringBootTest
public class MenuUrlMapperTest {

	@Autowired
	private MenuUrlMapper menuUrlMapper;

	@Test
	public void testInsertMenuUrl() {
		
		int menuId = 0;
		int urlId = 0;

//		menuId = 1; // 用户管理
//		menuId = 2; // 角色管理
//		menuId = 3; // 菜单管理
//		menuId = 4; // URL管理
//		menuId = 5; // 用户授权
//		menuId = 6; // 角色授权
		menuId = 8; // 测试菜单1
		
//		urlId = 1; // url=/sys/user/list
//		urlId = 2; // url=/sys/user/add
//		urlId = 3; // url=/sys/user/update
//		urlId = 4; // url=/sys/user/delete
//		urlId = 13; // url=/sys/url/list
//		urlId = 14; // url=/sys/url/add
//		urlId = 15; // url=/sys/url/update
//		urlId = 16; // url=/sys/url/delete
//		urlId = 19; // url=/test1
//		urlId = 20; // url=/test2
//		urlId = 21; // url=/test3
		urlId = 22; // url=/test4
		
		MenuUrl menuUrl = new MenuUrl();
		menuUrl.setMenuId(menuId);
		menuUrl.setUrlId(urlId);
		
		int insert = menuUrlMapper.insertMenuUrl(menuUrl);
		System.out.println("testInsertMenuUrl::insert=" + insert);
		assertEquals(1, insert);
	}
	
	@Test
	public void testDeleteMenuUrl() {
		
		int menuId = 0;
		int urlId = 0;

//		menuId = 1; // 用户管理
//		menuId = 2; // 角色管理
//		menuId = 3; // 菜单管理
//		menuId = 4; // URL管理
//		menuId = 5; // 用户授权
//		menuId = 6; // 角色授权
		menuId = 8; // 测试菜单1
		
//		urlId = 1; // url=/sys/user/list
//		urlId = 2; // url=/sys/user/add
//		urlId = 3; // url=/sys/user/update
//		urlId = 4; // url=/sys/user/delete
//		urlId = 13; // url=/sys/url/list
//		urlId = 14; // url=/sys/url/add
//		urlId = 15; // url=/sys/url/update
//		urlId = 16; // url=/sys/url/delete
//		urlId = 19; // url=/test1
//		urlId = 20; // url=/test2
//		urlId = 21; // url=/test3
		urlId = 22; // url=/test4
		
		MenuUrl menuUrl = new MenuUrl();
		menuUrl.setMenuId(menuId);
		menuUrl.setUrlId(urlId);
		
		int delete = menuUrlMapper.deleteMenuUrl(menuUrl);
		System.out.println("testDeleteMenuUrl::delete=" + delete);
		assertEquals(1, delete);
	}
}
