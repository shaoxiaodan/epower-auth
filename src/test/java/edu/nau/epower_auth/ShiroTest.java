package edu.nau.epower_auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * junit 5测试的使用
 */
@SpringBootTest
public class ShiroTest {

	// shiro默认的一个简单Realm
	private SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

	// shiro默认的安全管理器
	private DefaultSecurityManager defaultSecurityMgr = new DefaultSecurityManager();

	@Test
	public void testAuth() {

		// 注册用户
		simpleAccountRealm.addAccount("aaa", "123");
		simpleAccountRealm.addAccount("bbb", "456");
		simpleAccountRealm.addAccount("ccc", "789");

		// 构建环境
		defaultSecurityMgr.setRealm(simpleAccountRealm);

		// 设置shiro的环境上下文
		SecurityUtils.setSecurityManager(defaultSecurityMgr);

		// 获取当前操作主对象(主体), application user
		Subject subject = SecurityUtils.getSubject();

		// 构造用户输入的账号，密码
		UsernamePasswordToken usernamePassworToken = new UsernamePasswordToken("aaa", "123");

		// 登录认证
		subject.login(usernamePassworToken);

		// 认证结果
		System.out.println("result: " + subject.isAuthenticated());
	}
	
	@Test
	public void testOne() {
		System.out.println("test one...");
	}

	@Test
	public void testTwo() {
		System.out.println("test two...");
	}
	
}
