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

import edu.nau.epower_auth.dao.Url;
import edu.nau.epower_auth.service.UrlService;

@Controller
@RequestMapping("/url")
public class UrlController {

	@Autowired
	private UrlService urlService;

	@GetMapping("list")
	public String listUser(ModelMap modelMap) {

		List<Url> urls = urlService.listUrl();
		modelMap.addAttribute("urls", urls);

		return "system/url/list";
	}

	@GetMapping("add")
	public String addPage(Model model) {

		model.addAttribute("addurl", new Url());
		return "system/url/add";
	}

	@PostMapping("addurl")
	public String addUrl(Url url) {

		int add = urlService.addUrl(url);
		return "redirect:list";
	}

	@GetMapping("update")
	public String updatePage(@RequestParam("id") int urlId, Model model) {

		Url url = urlService.getUrl(urlId);
		model.addAttribute("updateurl", url);
		return "system/url/update";
	}

	@PostMapping("updateurl")
	public String updateUrl(Url url) {
		int update = urlService.updateUrl(url);
		return "redirect:list";
	}

	@GetMapping("remove")
	public String removeUser(@RequestParam("id") int urlId) {

		int remove = urlService.removeUrl(urlId);
		return "redirect:list";
	}

}