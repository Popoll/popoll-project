package com.esgi.popoll.slackbot.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.fries.slack.webhook.trigger.ActionPayload;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ActionPayloadAdapter {
	
	private static final String UTF_8_ENCODING = "UTF-8";
	private static final String PAYLOAD_PREFIX = "payload=";
	
	String extractJsonPayload(final String urlencodedData) {
		if(urlencodedData == null)
			throw new IllegalArgumentException("urlencodedData cannot be null");
		
		try {
			return URLDecoder.decode(urlencodedData, UTF_8_ENCODING).substring(PAYLOAD_PREFIX.length());
		}
		catch (UnsupportedEncodingException e) {
			return urlencodedData.substring(PAYLOAD_PREFIX.length());
		}
	}
	
	public ActionPayload toActionPayload(String urlencodedData) {
		final String jsonPayload = extractJsonPayload(urlencodedData);
		final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		
		return gson.fromJson(jsonPayload, ActionPayload.class);
	}
}
