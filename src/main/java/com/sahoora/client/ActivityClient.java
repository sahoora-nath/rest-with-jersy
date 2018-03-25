package com.sahoora.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sahoora.model.Activity;

public class ActivityClient {

	private Client client;
	public ActivityClient() {
		this.client = ClientBuilder.newClient();
	}
	
	//http://localhost:8080/rest-with-jersey-service/webapi/activities/1234
	public Activity get(String id) {
		WebTarget target = client.target("http://localhost:8080/rest-with-jersey-service/webapi/");
		
		Response response = target.path("activities/" + id).request().get(Response.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + " There was an error on the server");
		}
		return response.readEntity(Activity.class);
	}
	
	public String getJson(String id) {
		WebTarget target = client.target("http://localhost:8080/rest-with-jersey-service/webapi/");
		String response = target.path("activities/" + id).request(MediaType.APPLICATION_JSON).get(String.class);
		return response;
	}
	
	public List<Activity> get() {
		WebTarget target = client.target("http://localhost:8080/rest-with-jersey-service/webapi/");
		List<Activity> response = target.path("activities/").request().get(new GenericType<List<Activity>>() {});
		
		return response;
	}
	
	public Activity create(Activity activity) {
		WebTarget target = client.target("http://localhost:8080/rest-with-jersey-service/webapi/");
		
		Response response = target.path("activities/activity")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(activity, MediaType.APPLICATION_JSON));
		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + " There was an error on the server");
		}
		return response.readEntity(Activity.class);
	}

	/**
	 * HTTP PUT on Test
	 */
	public Activity update(Activity activity) {

		WebTarget target = client.target("http://localhost:8080/rest-with-jersey-service/webapi/");
		Response response = target.path("activities/" + activity.getActivityId())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(activity, MediaType.APPLICATION_JSON));
		
		return response.readEntity(Activity.class);
	}

	public void delete(int id) {
		WebTarget target = client.target("http://localhost:8080/rest-with-jersey-service/webapi/");
		
		Response response = target.path("activities/" + id).request().delete(Response.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + " There was an error on the server");
		}
	}
}
