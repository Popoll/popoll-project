package com.esgi.popoll.slackbot.config;

import io.fries.slack.webhook.trigger.Trigger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TriggerAdapter {
	
	private static final String UTF_8_ENCODING = "UTF-8";
	private static final String URLENCODED_SEPARATOR = "&";
	private static final String KEY_VALUE_SEPARATOR = "=";
	
	List<String> splitUrlEncodedData(final String urlencodedData) {
		if(urlencodedData == null)
			throw new IllegalArgumentException("urlencodedData cannot be null");
		
		String decodedData;
		
		try {
			decodedData = URLDecoder.decode(urlencodedData, UTF_8_ENCODING);
		}
		catch (UnsupportedEncodingException e) {
			decodedData = urlencodedData;
		}
		
		return Arrays.asList(decodedData.split(URLENCODED_SEPARATOR));
	}
	
	Map<String, String> mapValues(final List<String> splitData) {
		if(splitData == null)
			throw new IllegalArgumentException("splitData cannot be null");
		
		final int KEY_INDEX = 0;
		final int VALUE_INDEX = 1;
		
		final Map<String, String> triggerMap = new HashMap<>();
		
		splitData.forEach(data -> {
			final String[] keyValue = data.split(KEY_VALUE_SEPARATOR);
			triggerMap.put(keyValue[KEY_INDEX], keyValue[VALUE_INDEX]);
		});
		
		return triggerMap;
	}
	
	public Trigger toTrigger(final String urlencodedData) {
		if(urlencodedData == null)
			throw new IllegalArgumentException("urlencodedData cannot be null");
		
		final List<String> splitData = splitUrlEncodedData(urlencodedData);
		final Map<String, String> mappedData = mapValues(splitData);
		
		final Trigger.TriggerBuilder trigger = Trigger.builder();
		
		if(mappedData.containsKey("token"))
			trigger.token(mappedData.get("token"));
		if(mappedData.containsKey("team_id"))
			trigger.teamId(mappedData.get("team_id"));
		if(mappedData.containsKey("team_domain"))
			trigger.teamDomain(mappedData.get("team_domain"));
		if(mappedData.containsKey("channel_id"))
			trigger.channelId(mappedData.get("channel_id"));
		if(mappedData.containsKey("channel_name"))
			trigger.channelName(mappedData.get("channel_name"));
		if(mappedData.containsKey("user_id"))
			trigger.userId(mappedData.get("user_id"));
		if(mappedData.containsKey("user_name"))
			trigger.userName(mappedData.get("user_name"));
		if(mappedData.containsKey("command"))
			trigger.command(mappedData.get("command"));
		if(mappedData.containsKey("text"))
			trigger.text(mappedData.get("text"));
		if(mappedData.containsKey("response_url"))
			trigger.responseUrl(mappedData.get("response_url"));
		
		return trigger.build();
	}
}
