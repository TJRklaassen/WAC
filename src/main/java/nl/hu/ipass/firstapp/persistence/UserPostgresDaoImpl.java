package nl.hu.ipass.firstapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPostgresDaoImpl extends PostgresBaseDao implements UserDao {

	public String findRoleForUser(String username, String password) throws SQLException {
		try {
			Connection conn = getConnection();
			
			String queryText = "SELECT * FROM users WHERE username = ? AND password = ?";
			PreparedStatement pstmt = conn.prepareStatement(queryText);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			System.out.println(username);
			System.out.println(password);
			
			rs.next();
			String role = rs.getString("role");
			
			rs.close();
			pstmt.close();
			conn.close();
			
			return role;
		} catch(Exception e) {
			return null;
		}
	}

}
