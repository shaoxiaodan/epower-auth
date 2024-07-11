package edu.nau.epower_auth.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import edu.nau.epower_auth.dao.Menu;
import edu.nau.epower_auth.dao.Url;
import edu.nau.epower_auth.service.MenuService;
import edu.nau.epower_auth.service.UrlService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@Autowired
	private UrlService urlService;

	@GetMapping("list")
	public String listMenu(@RequestParam(defaultValue = "1") int pageNum, ModelMap modelMap) {

		int pageSize = 10;

		PageHelper.startPage(pageNum, pageSize);
		List<Menu> menus = menuService.listMenu();
		PageInfo<Menu> pages = new PageInfo<Menu>(menus);

		modelMap.addAttribute("menus", menus);
		modelMap.addAttribute("pages", pages);

		return "system/menu/list";
	}

	@GetMapping("add")
	public String addPage(Model model) {

		model.addAttribute("addmenu", new Menu());
		return "system/menu/add";
	}

	@PostMapping("addmenu")
	public String addMenu(Menu menu) {

		int add = menuService.addMenu(menu);
		return "redirect:list";
	}

	@GetMapping("update")
	public String updatePage(@RequestParam("mid") int menuId, Model model) {

		Menu menu = menuService.getMenu(menuId);
		model.addAttribute("updatemenu", menu);
		return "system/menu/update";
	}

	@PostMapping("updatemenu")
	public String updateMenu(Menu menu) {
		int update = menuService.updateMenu(menu);
		return "redirect:list";
	}

	@GetMapping("remove")
	public String removeMenu(@RequestParam("mid") int menuId) {

		int remove = menuService.removeMenu(menuId);
		return "redirect:list";
	}

	@GetMapping("auth")
	public String authPage(@RequestParam("mid") int menuId, Model model) {

		// 根据menu id获取菜单 - left url
		Menu menu = menuService.getMenu(menuId);
		model.addAttribute("menu", menu);

		// 获取URL列表
		List<Url> allUrls = urlService.listUrl();
		model.addAttribute("urls", allUrls);

		// 根据menu id获取菜单的所有URL - right url
		List<Url> menuUrls = urlService.findUrlByMenuId(menuId);
		model.addAttribute("menuurls", menuUrls);

		// 如果menu下面存在有URL数据，先获取URL的id
		List<Integer> rUrlIds = null;
		if (menuUrls != null && menuUrls.size() > 0) {
			rUrlIds = new ArrayList<Integer>();
			for (Url menuUrl : menuUrls) {
				rUrlIds.add(menuUrl.getId());
			}
		}

		// URL列表数据去重
		// 如果menu下面存在有URL数据，移除全面URL列表中的存在数据
		if (rUrlIds != null && rUrlIds.size() > 0) {
			Url urlTmp = null;
			for (int i = 0; i < allUrls.size(); i++) {
				urlTmp = allUrls.get(i);
				for (int j = 0; j < rUrlIds.size(); j++) {
					if (urlTmp.getId() == rUrlIds.get(j)) {
						allUrls.remove(i);
						rUrlIds.remove(j);
						break; // 跳出当前rUrlIds循环
					}
				}
			}
		}

		// 用role id创建menu映射对象，返回前端并绑定表单
//		RoleMenu roleMenu = new RoleMenu();
//		roleMenu.setRoleId(roleId);
//		model.addAttribute("addmenu", roleMenu);

		return "system/menu/auth";
	}

}
