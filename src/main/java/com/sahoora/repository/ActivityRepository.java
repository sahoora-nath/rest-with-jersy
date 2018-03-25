package com.sahoora.repository;

import java.util.List;

import com.sahoora.model.Activity;
import com.sahoora.model.ActivitySearch;

public interface ActivityRepository {

	List<Activity> findAllActivities();

	Activity getActivity(String activityId);

	void create(Activity activity);

	Activity update(Activity activity);

	void delete(int activityId);

	List<Activity> findbyDescription(List<String> descriptions);

	List<Activity> findByConstraints(ActivitySearch search);

}