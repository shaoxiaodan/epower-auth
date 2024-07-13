package edu.nau.epower_auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import edu.nau.epower_auth.common.ConstantUtils;
import edu.nau.epower_auth.dao.Url;
import edu.nau.epower_auth.service.UrlService;

/**
 * URL资源控制器
 * 
 * @ClassName: UrlController
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-13 03:19:49
 */
@Controller
@RequestMapping("/url")
public class UrlController {

	@Autowired
	private UrlService urlService;

	/*
	 * URL列表page
	 */
	@GetMapping("list")
	public String listUrl(@RequestParam(defaultValue = "1") int pageNum, ModelMap modelMap) {

		// 列表 + 分页
		PageHelper.startPage(pageNum, ConstantUtils.PAGE_SIZE);
		List<Url> urls = urlService.listUrl();
		PageInfo<Url> pages = new PageInfo<Url>(urls);

		modelMap.addAttribute("urls", urls);
		modelMap.addAttribute("pages", pages);

		return "system/url/list";
	}

	/*
	 * URL添加page
	 */
	@GetMapping("add")
	public String addPage(Model model) {

		// 设置boolean值的isEntrance属性
		Map<String, Object> optMaps = new HashMap<String, Object>();
		optMaps.put("是", 1);
		optMaps.put("否", 0);

		model.addAttribute("addurl", new Url());
		model.addAttribute("optmaps", optMaps);

		return "system/url/add";
	}

	/*
	 * URL添加
	 */
	@PostMapping("addurl")
	public String addUrl(Url url) {
		int add = urlService.addUrl(url);
		return "redirect:list";
	}

	/*
	 * URL更新page
	 */
	@GetMapping("update")
	public String updatePage(@RequestParam("id") int urlId, Model model) {

		// 设置boolean值的isEntrance属性
		Map<String, Object> optMaps = new HashMap<String, Object>();
		optMaps.put("是", 1);
		optMaps.put("否", 0);

		// 获取URL列表
		Url url = urlService.getUrl(urlId);

		model.addAttribute("updateurl", url);
		model.addAttribute("optmaps", optMaps);

		return "system/url/update";
	}

	/*
	 * URL更新
	 */
	@PostMapping("updateurl")
	public String updateUrl(Url url) {
		int update = urlService.updateUrl(url);
		return "redirect:list";
	}

	/*
	 * URL删除
	 */
	@GetMapping("remove")
	public String removeUser(@RequestParam("id") int urlId) {
		int remove = urlService.removeUrl(urlId);
		return "redirect:list";
	}

}
