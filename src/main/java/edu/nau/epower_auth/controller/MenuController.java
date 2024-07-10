package edu.nau.epower_auth.controller;

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
import edu.nau.epower_auth.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

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
	public String updatePage(@RequestParam("id") int menuId, Model model) {

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
	public String removeMenu(@RequestParam("id") int menuId) {

		int remove = menuService.removeMenu(menuId);
		return "redirect:list";
	}

}
