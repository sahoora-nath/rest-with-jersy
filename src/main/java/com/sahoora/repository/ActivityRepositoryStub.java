package com.sahoora.repository;

import java.util.ArrayList;
import java.util.List;

import com.sahoora.model.Activity;
import com.sahoora.model.ActivitySearch;
import com.sahoora.model.User;

public class ActivityRepositoryStub implements ActivityRepository {
	/* (non-Javadoc)
	 * @see com.sahoora.repository.ActivityRepository#findAllActivities()
	 */
	@Override
	public List<Activity> findAllActivities() {
		List<Activity> activities = new ArrayList<Activity>();
		
		Activity activity1 = new Activity();
		activity1.setDescription("Swimming");
		activity1.setDuration(30);
		activities.add(activity1);
		
		Activity activity2 = new Activity();
		activity1.setDescription("Cycling");
		activity1.setDuration(45);
		activities.add(activity2);
		
		return activities;
	}
	
	@Override
	public Activity getActivity(String activityId) {
		Activity activity1 = new Activity();
		activity1.setDescription("Swimming");
		activity1.setDuration(30);
		activity1.setActivityId(1234);
		
		User user = new User();
		user.setId(123);
		user.setName("Bob");
		user.setEmail("rs@gmail.com");
		
		activity1.setUser(user);
		return activity1;
	}

	@Override
	public void create(Activity activity) {
		// should insert an activity object into DB.
		
	}

	@Override
	public Activity update(Activity activity) {
		// Search the DB with that ID to see if we have that activity already exists
		//if exists
		//	update the activity
		//else
		//create the activity
		
		return activity;
		
	}

	@Override
	public void delete(int activityId) {
		// delete from activity where id = activityId
	}

	@Override
	public List<Activity> findbyDescription(List<String> descriptions) {
		// TODO select * from Activity where description like '%desc%';
		
		List<Activity> activities = new ArrayList<Activity>();
		
		Activity activity1 = new Activity();
		activity1.setDescription("Running");
		activity1.setActivityId(1234);
		activity1.setDuration(30);
		
		activities.add(activity1);
		return activities;
	}

	@Override
	public List<Activity> findByConstraints(ActivitySearch search) {
		List<Activity> activities = new ArrayList<Activity>();
		
		Activity activity1 = new Activity();
		activity1.setDescription("Running");
		activity1.setActivityId(1234);
		activity1.setDuration(30);
		
		activities.add(activity1);
		return activities;
	}
}
