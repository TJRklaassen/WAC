package nl.hu.ipass.firstapp.persistence;

import java.sql.SQLException;

public interface UserDao {
	public String findRoleForUser(String username, String password) throws SQLException;
}
