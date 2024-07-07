package edu.nau.epower_auth.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import edu.nau.epower_auth.dao.UserRole;

public class AuthSqlProvider {

	public String batchInsertUserRole(Map map) {

		List<UserRole> urList = (List<UserRole>) map.get("list");
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO user_role (user_id,role_id,remarks) VALUES ");
		MessageFormat mf = new MessageFormat("(#'{'list[{0}].userId}, #'{'list[{0}].roleId}, #'{'list[{0}].remarks})");

		for (int i = 0; i < urList.size(); i++) {
			sb.append(mf.format(new Object[] { i }));
			if (i < urList.size() - 1)
				sb.append(",");
		}
		return sb.toString();
	}

	public String batchRemoveUserRole(Map map) {

		List<UserRole> urList = (List<UserRole>) map.get("list");
		StringBuilder sb = new StringBuilder();

		sb.append("DELETE FROM user_role WHERE id IN (");
		for (int i = 0; i < urList.size(); i++) {
			sb.append("'").append(urList.get(i).getId()).append("'");
			if (i < urList.size() - 1)
				sb.append(",");
		}
		sb.append(")");
		return sb.toString();
	}
}
