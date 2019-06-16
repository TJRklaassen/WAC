package nl.hu.v1wac.firstapp.persistence;

import java.sql.SQLException;
import java.util.List;

import nl.hu.v1wac.firstapp.model.Country;

public interface CountryDao {
	public boolean save(Country country) throws SQLException;
	public List<Country> findAll() throws SQLException;
	public Country findByCode(String code) throws SQLException;
	public List<Country> find10LargestPopulations() throws SQLException;
	public List<Country> find10LargestSurfaces() throws SQLException;
	public boolean update(Country country) throws SQLException;
	public boolean delete(Country country) throws SQLException;
}
