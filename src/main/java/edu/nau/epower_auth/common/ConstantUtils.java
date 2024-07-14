package edu.nau.epower_auth.common;

/**
 * 常量工具类
 * 
 * @ClassName: ConstantUtils
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-13 03:59:32
 */
public class ConstantUtils {

	// session中的用户登录信息
	public static final String SESSION_LOGIN_USER = "loginuser";

	// session中的用户所有角色
	public static final String SESSION_USER_ROLES = "userroles";

	// session中的用户默认角色
	public static final String SESSION_DEF_ROLE = "defrole";

	// session中的用户可访问URL
	public static final String SESSION_USER_URLS = "userurls";

	// 每页的显示数据量
	public static final int PAGE_SIZE = 10;

	// 分页获取的名称
	public static final String PAGE_INFO = "pageinfo";

}
