package com.sahoora.client;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.sahoora.model.Activity;
import com.sahoora.model.ActivitySearch;

public class ActivitySearchClient {
	private Client client;
	public ActivitySearchClient() {
		this.client = ClientBuilder.newClient();
	}
	
	//http://localhost:8080/rest-with-jersey-service/webapi/search/activities?description=swimming&description=running
	public List<Activity> search(String params, List<String> searchValues) {
		URI uri = UriBuilder.fromUri("http://localhost:8080/rest-with-jersey-service/webapi")
				.path("search/activities")
				.queryParam(params, searchValues)
				.build();
		WebTarget target = client.target(uri);
		
		List<Activity> response = target.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Activity>>() {});
		
		return response;
	}

	public List<Activity> search(ActivitySearch search) {

		URI uri = UriBuilder.fromUri("http://localhost:8080/rest-with-jersey-service/webapi")
				.path("search/activities")
				.build();
		
		WebTarget target = client.target(uri);
		Response response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(search, MediaType.APPLICATION_JSON));
		
		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + " Error on Server.");
		}
		
		return response.readEntity(new GenericType<List<Activity>>() {});
	}
	
	
}
