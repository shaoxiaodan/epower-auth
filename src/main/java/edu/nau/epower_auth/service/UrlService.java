package edu.nau.epower_auth.service;

import java.util.List;

import edu.nau.epower_auth.dao.Url;

/**
 * 资源路径服务接口
 * 
 * @ClassName: UrlService
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-07 01:03:46
 */
public interface UrlService {

	public List<Url> listUrl();

	public Url getUrl(int urlId);

	public int addUrl(Url url);

	public int updateUrl(Url url);

	public int removeUrl(int urlId);
}
