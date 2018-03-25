package com.sahoora;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sahoora.model.Activity;
import com.sahoora.model.User;
import com.sahoora.repository.ActivityRepository;
import com.sahoora.repository.ActivityRepositoryStub;

@Path("activities") //http://localhost:8080/rest-with-jersey-service/webapi/activities
public class ActivityResource {

	Logger logger = LoggerFactory.getLogger(ActivityResource.class);
	private ActivityRepository activityRepository = new ActivityRepositoryStub();

	/** HTTP GET demo **/
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Activity> findAllActivities() {
		return activityRepository.findAllActivities();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{activityId}")//http://localhost:8080/rest-with-jersey-service/webapi/activities/111
	public Response getActivitiy(@PathParam ("activityId") String activityId) {
		logger.info("Activity ID requested - {}", activityId);
		if (activityId ==null || activityId.length() < 4) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		Activity activity = activityRepository.getActivity(activityId);
		if(activity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok().entity(activity).build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{activityId}/user")//http://localhost:8080/rest-with-jersey-service/webapi/activities/111/user
	public User getActivitiyUser(@PathParam ("activityId") String activityId) {
		return activityRepository.getActivity(activityId).getUser();
	}
	
	/** HTTP POST demo: POST is used to create **/
	
	/**
	 * POST request with HTTP Form submit
	 * @param formParams
	 * @return
	 */
	@POST
	@Path("activity")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Activity createActivityParams(MultivaluedMap<String, String> formParams) {
		logger.info(formParams.getFirst("description"));
		logger.info(formParams.getFirst("duration"));
		
		Activity activity = new Activity();
		activity.setDescription(formParams.getFirst("description"));
		activity.setDuration(Integer.parseInt(formParams.getFirst("duration")));
		
		activityRepository.create(activity);
		return activity;
	}
	
	/**
	 * HTTP POST with raw message input from Ajax/Jquery in the from of JSON
	 * @param activity
	 * @return
	 */
	@POST
	@Path("activity")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Activity createActivity(Activity activity) {
		logger.info("POST activity desc - {}", activity.getDescription());
		logger.info("POST activity duration - {}", activity.getDuration());
		
		activityRepository.create(activity);
		return activity;
	}
	
	@PUT
	@Path("{activityId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateActivity(Activity activity) {
		logger.info("PUT activity desc - {}", activity.getDescription());
		logger.info("PUT activity duration - {}", activity.getDuration());
		
		activityRepository.update(activity);
		return Response.ok().entity(activity).build();
	}
	
	
	@DELETE
	@Path("{activityId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response deleteActivity(@PathParam("activityId") int activityId) {
		logger.info("DELETE activity ID - {}", activityId);
		
		activityRepository.delete(activityId);
		
		return Response.ok().build();
	}
}
