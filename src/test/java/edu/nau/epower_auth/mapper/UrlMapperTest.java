package edu.nau.epower_auth.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.nau.epower_auth.dao.Url;

/**
 * 
 * @ClassName: UrlMapperTest
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-06 10:28:34
 */
@SpringBootTest
public class UrlMapperTest {

	@Autowired
	private UrlMapper urlMapper;

	@Test
	public void testFindUrlByMenuId() {

		int menuId = 0;
//		menuId = 1; //用户管理菜单
		menuId = 2; // 角色管理菜单
//		menuId = 3; //菜单管理菜单
//		menuId = 4; //URL管理菜单
//		menuId = 5; //用户授权菜单
//		menuId = 6; // 角色授权菜单
//		menuId = 7; // null

		List<Url> urlList = urlMapper.findUrlByMenuId(menuId);
		if (urlList != null && urlList.size() > 0) {
			Url url = null;
			for (int i = 0; i < urlList.size(); i++) {
				url = urlList.get(i);
				System.out.println("testFindUrlByMenuId::urlList=" + url.getId() + "\t" + url.getPath() + "\t"
						+ url.getDescription());
			}
		}

		assertNotNull(urlList);
	}
}
