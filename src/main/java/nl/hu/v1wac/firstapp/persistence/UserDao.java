package nl.hu.v1wac.firstapp.persistence;

import java.sql.SQLException;

public interface UserDao {
	public String findRoleForUser(String username, String password) throws SQLException;
}
