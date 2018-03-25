package com.sahoora.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sahoora.model.Activity;
import com.sahoora.model.ActivitySearch;

public class ActivityClientTest {

	private ActivityClient client;
	
	@Before
	public void setUp() throws Exception {
		client = new ActivityClient();
	}

	
	@Test
	public void testGet() {
		Activity activity = client.get("1234");
		assertNotNull(activity);
		assertEquals(1234,  activity.getActivityId());
	}
	
	@Test(expected=RuntimeException.class)
	public void testGet_BadRequest() {
		Activity activity = client.get("123");
		assertNotNull(activity);
		assertEquals(1234,  activity.getActivityId());
	}
	
	@Test
	public void testGet_JSON() {
		String activity = client.getJson("1234");
		assertNotNull(activity);
	}
	
	@Test
	public void testGet_List() {
		List<Activity> activities = client.get();
		assertNotNull(activities);
	}
	
	@Test
	public void testCreate() {
		Activity activity = new Activity();
		activity.setDescription("running");
		activity.setDuration(10);
		
		Activity resposeAct = client.create(activity);
		assertNotNull(resposeAct);
	}
	
	@Test
	public void testPut() {
		Activity activity = new Activity();
		activity.setActivityId(3456);
		activity.setDescription("Yoga");
		activity.setDuration(60);
		
		activity = client.update(activity);
		assertNotNull(activity);
	}
	
	@Test
	public void testDelete() {
		client.delete(1234);
		//assertNotNull(activity);
	}
	
	@Test
	public void testSearch() {
		ActivitySearchClient searchClient = new ActivitySearchClient();
		String params="description";
		List<String> searchValues = new ArrayList<String>();
		searchValues.add("swimming");
		searchValues.add("running");
		
		List<Activity> activities = searchClient.search(params, searchValues);
		assertNotNull(activities);
	}
	
	@Test
	public void testSearchObject() {
		
		ActivitySearchClient searchClient = new ActivitySearchClient();
		
		List<String> searchValues = new ArrayList<String>();
		searchValues.add("swimming");
		searchValues.add("running");
		
		ActivitySearch search = new ActivitySearch();
		search.setDescriptions(searchValues);
		search.setDurationFrom(10);
		search.setDurationTo(40);
		
		List<Activity> activities = searchClient.search(search);
		assertNotNull(activities);
		
	}
}
