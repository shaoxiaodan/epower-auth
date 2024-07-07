package edu.nau.epower_auth;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.nau.epower_auth.dao.Url;
import edu.nau.epower_auth.mapper.UrlMapper;

@SpringBootTest
public class UrlMapperTest {

	@Autowired
	private UrlMapper urlMapper;

	@Test
	public void f() {

		List<Url> urlList = urlMapper.findUrlByMenuId(1);
		if (urlList != null && urlList.size() > 0) {
			for (int i = 0; i < urlList.size(); i++) {
				System.out.println("testFindUrlByMenuId::urlList=" + urlList.get(i));
			}
		}

		assertNotNull(urlList);
	}
}
