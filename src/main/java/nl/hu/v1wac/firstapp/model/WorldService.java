package nl.hu.v1wac.firstapp.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nl.hu.v1wac.firstapp.persistence.CountryPostgresDaoImpl;

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
}
