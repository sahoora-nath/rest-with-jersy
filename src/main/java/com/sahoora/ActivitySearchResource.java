package com.sahoora;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sahoora.model.Activity;
import com.sahoora.model.ActivitySearch;
import com.sahoora.repository.ActivityRepository;
import com.sahoora.repository.ActivityRepositoryStub;

@Path("search/activities") //http://localhost:8080/rest-with-jersey-service/webapi/search/activities
public class ActivitySearchResource {
	Logger logger = LoggerFactory.getLogger(ActivitySearchResource.class);
	private ActivityRepository activityRepository = new ActivityRepositoryStub();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response searchForActivities(@QueryParam(value="description") List<String> descriptions ) {
		logger.info("Search desc: {}", descriptions);
		List<Activity> activities = activityRepository.findbyDescription(descriptions);
		
		if(activities == null || activities.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {}).build() ;
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response searchForActivities(ActivitySearch search) {
		logger.info("Search by Object: {}, from duration-{}, to duration-{}", search.getDescriptions(), search.getDurationFrom(), search.getDurationTo());
		
		List<Activity> activities = activityRepository.findByConstraints(search);
		
		if(activities == null || activities.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {}).build() ;
	}
}
