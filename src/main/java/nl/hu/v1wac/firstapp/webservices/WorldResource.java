package nl.hu.v1wac.firstapp.webservices;

import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.hu.v1wac.firstapp.model.Country;
import nl.hu.v1wac.firstapp.model.ServiceProvider;
import nl.hu.v1wac.firstapp.model.WorldService;

@Path("/countries")
public class WorldResource {
	
	@GET
	@Produces("application/json")
	public String getCountries() throws SQLException {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (Country c : service.getAllCountries()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			
			job.add("code", c.getCode());
			job.add("iso3", c.getIso3());
			job.add("name", c.getName());
			job.add("continent", c.getContinent());
			job.add("capital", c.getCapital());
			job.add("region", c.getRegion());
			job.add("surface", c.getSurface());
			job.add("population", c.getPopulation());
			job.add("government", c.getGovernment());
			job.add("lat", c.getLatitude());
			job.add("lng", c.getLongitude());
			
			jab.add(job);
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}	
	
	@GET
	@Path("{code}")
	@Produces("application/json")
	public String getCountriesByCode(@PathParam("code") String code) throws SQLException {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		Country c = service.getCountryByCode(code);
		JsonObjectBuilder job = Json.createObjectBuilder();
		
		job.add("code", c.getCode());
		job.add("iso3", c.getIso3());
		job.add("name", c.getName());
		job.add("continent", c.getContinent());
		job.add("capital", c.getCapital());
		job.add("region", c.getRegion());
		job.add("surface", c.getSurface());
		job.add("population", c.getPopulation());
		job.add("government", c.getGovernment());
		job.add("lat", c.getLatitude());
		job.add("lng", c.getLongitude());
		
		jab.add(job);
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("/largestsurfaces")
	@Produces("application/json")
	public String getCountriesBySurfaceSize() throws SQLException {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (Country c : service.get10LargestSurfaces()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			
			job.add("code", c.getCode());
			job.add("iso3", c.getIso3());
			job.add("name", c.getName());
			job.add("continent", c.getContinent());
			job.add("capital", c.getCapital());
			job.add("region", c.getRegion());
			job.add("surface", c.getSurface());
			job.add("population", c.getPopulation());
			job.add("government", c.getGovernment());
			job.add("lat", c.getLatitude());
			job.add("lng", c.getLongitude());
			
			jab.add(job);
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}	
	
	@GET
	@Path("/largestpopulations")
	@Produces("application/json")
	public String getCountriesByPopulationSize() throws SQLException {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (Country c : service.get10LargestPopulations()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			
			job.add("code", c.getCode());
			job.add("iso3", c.getIso3());
			job.add("name", c.getName());
			job.add("continent", c.getContinent());
			job.add("capital", c.getCapital());
			job.add("region", c.getRegion());
			job.add("surface", c.getSurface());
			job.add("population", c.getPopulation());
			job.add("government", c.getGovernment());
			job.add("lat", c.getLatitude());
			job.add("lng", c.getLongitude());
			
			jab.add(job);
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}	
}
