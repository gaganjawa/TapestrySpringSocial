package com.psl.tapestry.springsocial.pages;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.services.HttpError;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.slf4j.Logger;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * Start page of application SpringSocial2.0.
 */
public class Index {
	
	@Inject
	private Messages messages;

	@Inject
	private Logger logger;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;
	
	@InjectService("facebookService")
	private OAuth2ServiceProvider<Facebook> facebookService;
	
	@Persist
	private Map<String, String> socialProfile;

	@InjectPage
	private About about;
	
	@Persist
	@Property
	private String errorMessage;
	
	public Map<String, String> getSocialProfile() {
		return socialProfile;
	}

	// Handle call with an unwanted context
	Object onActivate(EventContext eventContext) {
		return eventContext.getCount() > 0 ? new HttpError(404,
				"Resource not found") : null;
	}

	Object onActionFromLearnMore() {
		about.setLearn("LearnMore");

		return about;
	}

	@OnEvent(value = EventConstants.SUCCESS, component = "facebook")
	void facebookConnected(final String accessToken) {
		final User profile = facebookService.getApi(accessToken).userOperations().getUserProfile();
<<<<<<< HEAD
		System.out.println("Profile "+profile);
=======
		System.out.println(profile);
>>>>>>> 14571411296bf3dff8a6a8475fababe3d1c9c4e4
		
		errorMessage = "No Error";
		socialProfile = new HashMap<String, String>();
		socialProfile.put("id", profile.getId());
		socialProfile.put("name", profile.getName());
		socialProfile.put("gender", profile.getGender());
<<<<<<< HEAD
		socialProfile.put("email", profile.getEmail());
		socialProfile.put("locale", String.valueOf(profile.getLocale()));
		socialProfile.put("link", profile.getLink());
		
		System.out.println("Social Profile: "+socialProfile);
=======
		socialProfile.put("locale", String.valueOf(profile.getLocale()));
		socialProfile.put("link", profile.getLink());
		
		System.out.println(socialProfile);
>>>>>>> 14571411296bf3dff8a6a8475fababe3d1c9c4e4
	}

	@OnEvent(value = EventConstants.FAILURE, component = "facebook")
	void facebookFailure() {
		socialProfile = null;
		System.out.println(EventConstants.FAILURE);
		errorMessage = messages.format("connection-denied", "Facebook");
	}

	public Date getCurrentTime() {
		return new Date();
	}
}
