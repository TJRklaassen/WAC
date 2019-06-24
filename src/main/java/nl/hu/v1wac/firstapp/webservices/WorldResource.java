package nl.hu.v1wac.firstapp.webservices;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nl.hu.v1wac.firstapp.model.Country;
import nl.hu.v1wac.firstapp.model.ServiceProvider;
import nl.hu.v1wac.firstapp.model.WorldService;

@Path("/countries")
public class WorldResource {
	private WorldService service = ServiceProvider.getWorldService();
	
	@GET
	@Produces("application/json")
	public List<Country> getCountries() throws SQLException {
		return service.getAllCountries();
	}	
	
	@GET
	@Path("{code}")
	@Produces("application/json")
	public Response getCountriesByCode(@PathParam("code") String code) throws SQLException {
		try {
			Country country = service.getCountryByCode(code);
			return Response.ok(country).build();
		} catch (Exception e) {
			Map<String, String> messages = new HashMap<String, String>();
			messages.put("error", "Country does not exist!");
			return Response.status(409).entity(messages).build();
		}
	}
	
	@GET
	@Path("/largestsurfaces")
	@Produces("application/json")
	public List<Country> getCountriesBySurfaceSize() throws SQLException {
		return service.get10LargestSurfaces();
	}	
	
	@GET
	@Path("/largestpopulations")
	@Produces("application/json")
	public List<Country> getCountriesByPopulationSize() throws SQLException {
		return service.get10LargestPopulations();
	}
	
	@POST
	@RolesAllowed("user")
	@Produces("application/json")
	public Response createCountry(@FormParam("code") String code,
			@FormParam("name") String name,
			@FormParam("capital") String capital,
			@FormParam("region") String region,
			@FormParam("surface") int surface,
			@FormParam("population") int population) throws SQLException {
		if(!service.createCountry(code, name, capital, region, surface, population)) {
			Map<String, String> messages = new HashMap<String, String>();
			messages.put("error", "Invalid values!");
			return Response.status(409).entity(messages).build();
		}
		
		Map<String, String> messages = new HashMap<String, String>();
		messages.put("success", "Country created.");
		return Response.ok(messages).build();
	}
	
	@PUT
	@RolesAllowed("user")
	@Path("{code}")
	@Produces("application/json")
	public Response updateCountry(@PathParam("code") String code,
			@FormParam("name") String name,
			@FormParam("capital") String capital,
			@FormParam("region") String region,
			@FormParam("surface") int surface,
			@FormParam("population") int population) throws SQLException {
		if(!service.updateCountry(code, name, capital, region, surface, population)) {
			Map<String, String> messages = new HashMap<String, String>();
			messages.put("error", "Country does not exist!");
			return Response.status(409).entity(messages).build();
		}
		
		Map<String, String> messages = new HashMap<String, String>();
		messages.put("success", "Country altered.");
		return Response.ok(messages).build();
	}
	
	@DELETE
	@RolesAllowed("user")
	@Path("{code}")
	@Produces("application/json")
	public Response deleteCountry(@PathParam("code") String code) throws SQLException {
		if(!service.deleteCountry(code)) {
			return Response.status(404).build();
		}
		
		return Response.ok().build();
	}
}
