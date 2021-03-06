package nl.hu.ipass.firstapp.persistence;

import java.sql.SQLException;
import java.util.List;

import nl.hu.ipass.firstapp.model.Country;

public interface CountryDao {
	public boolean save(Country country) throws SQLException;
	public List<Country> findAll() throws SQLException;
	public Country findByCode(String code) throws SQLException;
	public List<Country> find10LargestPopulations() throws SQLException;
	public List<Country> find10LargestSurfaces() throws SQLException;
	public boolean update(Country country) throws SQLException;
	public boolean delete(Country country) throws SQLException;
	boolean update(String code, String nm, String cap, String reg, int sur, int pop) throws SQLException;
	boolean delete(String code) throws SQLException;
	boolean save(String code, String nm, String cap, String reg, int sur, int pop) throws SQLException;
}
