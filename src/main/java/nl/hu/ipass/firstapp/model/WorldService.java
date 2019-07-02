package nl.hu.ipass.firstapp.model;

import java.sql.SQLException;
import java.util.List;

import nl.hu.ipass.firstapp.persistence.CountryPostgresDaoImpl;

public class WorldService {
	private CountryPostgresDaoImpl countryDao = new CountryPostgresDaoImpl();
	
	public List<Country> getAllCountries() throws SQLException {
		return countryDao.findAll();
	}
	
	public List<Country> get10LargestPopulations() throws SQLException {
		return countryDao.find10LargestPopulations();
	}

	public List<Country> get10LargestSurfaces() throws SQLException {
		return countryDao.find10LargestSurfaces();
	}
	
	public Country getCountryByCode(String code) throws SQLException {
		return countryDao.findByCode(code);
	}
	
	public boolean createCountry(String code, String name, String capital, String region, int surface, int population) throws SQLException {
		return countryDao.save(code, name, capital, region, surface, population);
	}
	
	public boolean createCountry(Country country) throws SQLException {
		return countryDao.save(country);
	}
	
	public boolean updateCountry(Country country) throws SQLException {
		return countryDao.update(country);
	}

	public boolean updateCountry(String code, String name, String capital, String region, int surface, int population) throws SQLException {
		return countryDao.update(code, name, capital, region, surface, population);
	}
	
	public boolean deleteCountry(String code) throws SQLException {
		return countryDao.delete(code);
	}
}
