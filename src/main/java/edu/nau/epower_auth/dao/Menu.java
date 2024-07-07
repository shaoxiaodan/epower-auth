package edu.nau.epower_auth.dao;

/**
 * 
 */
import java.util.List;

/**
 * 
 * @ClassName: Menu
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-06 09:01:13
 */
public class Menu {

	private int id;
	
	private String name;
	
	private String description;

	private List<Url> urlList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Url> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<Url> urlList) {
		this.urlList = urlList;
	}
	
}
