package nl.hu.ipass.firstapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.firstapp.model.Country;

public class CountryPostgresDaoImpl extends PostgresBaseDao implements CountryDao {
	
	private List<Country> findByQuery(String queryText) throws SQLException{
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		ArrayList<Country> list = new ArrayList<Country>();
		
		ResultSet rs = stmt.executeQuery(queryText);
		
		while(rs.next()) {
			String code = rs.getString("code");
			String iso3 = rs.getString("iso3");
			String name = rs.getString("name");
			String capital = rs.getString("capital");
			String continent = rs.getString("continent");
			String region = rs.getString("region");
			double surface = rs.getDouble("surfacearea");
			int population = rs.getInt("population");
			String government = rs.getString("governmentform");
			double latitude = rs.getDouble("latitude");
			double longitude = rs.getDouble("longitude");
			
			Country c = new Country(code, iso3, name, capital, continent, region, surface, population, government, latitude, longitude);
			list.add(c);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return list;
	}

	@Override
	public List<Country> findAll() throws SQLException {
		String queryText = "SELECT * FROM country";
		return findByQuery(queryText);
	}

	@Override
	public Country findByCode(String code) throws SQLException {
		Connection conn = getConnection();
		
		String queryText = "SELECT * FROM country WHERE code = ?";
		PreparedStatement pstmt = conn.prepareStatement(queryText);
		pstmt.setString(1, code);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		String iso3 = rs.getString("iso3");
		String name = rs.getString("name");
		String capital = rs.getString("capital");
		String continent = rs.getString("continent");
		String region = rs.getString("region");
		double surface = rs.getDouble("surfacearea");
		int population = rs.getInt("population");
		String government = rs.getString("governmentform");
		double latitude = rs.getDouble("latitude");
		double longitude = rs.getDouble("longitude");
		
		Country c = new Country(code, iso3, name, capital, continent, region, surface, population, government, latitude, longitude);
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return c;
	}

	@Override
	public List<Country> find10LargestPopulations() throws SQLException {
		String queryText = "SELECT * FROM country ORDER BY population DESC LIMIT 10;";
		return findByQuery(queryText);
	}

	@Override
	public List<Country> find10LargestSurfaces() throws SQLException {
		String queryText = "SELECT * FROM country ORDER BY surfacearea DESC LIMIT 10;";
		return findByQuery(queryText);
	}

	@Override
	public boolean save(Country country) throws SQLException {
		try {
			Connection conn = getConnection();
			
			String queryText = "INSERT INTO country (code, iso3, name, capital, continent, "
					+ "region, surfacearea, population, governmentform, latitude, longitude)\r\n" + 
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(queryText);
			
			pstmt.setString(1, country.getCode());
			pstmt.setString(2, country.getIso3());
			pstmt.setString(3, country.getName());
			pstmt.setString(4, country.getCapital());
			pstmt.setString(5, country.getContinent());
			pstmt.setString(6, country.getRegion());
			pstmt.setDouble(7, country.getSurface());
			pstmt.setInt(8, country.getPopulation());
			pstmt.setString(9, country.getGovernment());
			pstmt.setDouble(10, country.getLatitude());
			pstmt.setDouble(11, country.getLongitude());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean save(String code, String nm, String cap, String reg, int sur, int pop) throws SQLException {
		try {
			Connection conn = getConnection();
			
			String queryText = "INSERT INTO country (code, name, capital, region, surfacearea, population)\r\n" + 
					"VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(queryText);
			pstmt.setString(1, code);
			pstmt.setString(2, nm);
			pstmt.setString(3, cap);
			pstmt.setString(4, reg);
			pstmt.setInt(5, sur);
			pstmt.setInt(6, pop);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean update(Country country) throws SQLException {
		try {
			Connection conn = getConnection();
			
			String queryText = "UPDATE country SET iso3=?, name=?, capital=?, continent=?, "
					+ "region=?, surfacearea=?, population=?, governmentform=?, latitude=?, longitude=?\r\n" + 
					"WHERE code=?";
			
			PreparedStatement pstmt = conn.prepareStatement(queryText);
			
			pstmt.setString(1, country.getIso3());
			pstmt.setString(2, country.getName());
			pstmt.setString(3, country.getCapital());
			pstmt.setString(4, country.getContinent());
			pstmt.setString(5, country.getRegion());
			pstmt.setDouble(6, country.getSurface());
			pstmt.setInt(7, country.getPopulation());
			pstmt.setString(8, country.getGovernment());
			pstmt.setDouble(9, country.getLatitude());
			pstmt.setDouble(10, country.getLongitude());
			pstmt.setString(11, country.getCode());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean update(String code, String nm, String cap, String reg, int sur, int pop) throws SQLException {
		try {
			Connection conn = getConnection();
			
			String queryText = "UPDATE country SET name=?, capital=?, region=?, surfacearea=?, population=?\r\n" + 
					"WHERE code=?";
			
			PreparedStatement pstmt = conn.prepareStatement(queryText);
			
			pstmt.setString(1, nm);
			pstmt.setString(2, cap);
			pstmt.setString(3, reg);
			pstmt.setDouble(4, sur);
			pstmt.setInt(5, pop);
			pstmt.setString(6, code);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Country country) throws SQLException {
		try {
			Connection conn = getConnection();
			
			String queryText = "DELETE FROM country WHERE code=?";
			PreparedStatement pstmt = conn.prepareStatement(queryText);
			pstmt.setString(1, country.getCode());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
			return true;
		} catch (Exception e) {
			return false;
		}
	} 
	
	@Override
	public boolean delete(String code) throws SQLException {
		try {
			Connection conn = getConnection();
			
			String queryText = "DELETE FROM country WHERE code=?";
			PreparedStatement pstmt = conn.prepareStatement(queryText);
			pstmt.setString(1, code);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
			return true;
		} catch (Exception e) {
			return false;
		}
	} 
}
