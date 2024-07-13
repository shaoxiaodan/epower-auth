package edu.nau.epower_auth.common;

public class Constants {

	private static final String SESSION_LOGIN_USER = "loginuser";
	private static final String SESSION_USER_ROLES = "userroles";
	private static final String SESSION_DEF_ROLE = "defrole";

	public static String getSessionLoginUser() {
		return SESSION_LOGIN_USER;
	}

	public static String getSessionUserRoles() {
		return SESSION_USER_ROLES;
	}

	public static String getSessionDefRole() {
		return SESSION_DEF_ROLE;
	}

}
