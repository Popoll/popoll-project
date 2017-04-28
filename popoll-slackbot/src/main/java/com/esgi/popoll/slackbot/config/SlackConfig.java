package com.esgi.popoll.slackbot.config;

import io.fries.slack.webhook.WebHook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;

@Configuration
@RefreshScope
public class SlackConfig {
	
	@Value("${info.slack.webhook-url}")
	private String slackWebHookUrl;
	
	@Bean
	public WebHook slackWebHook() throws MalformedURLException {
		return new WebHook(slackWebHookUrl);
	}
	
	@Bean
	public TriggerAdapter triggerAdapter() {
		return new TriggerAdapter();
	}
	
	@Bean
	public ActionPayloadAdapter actionPayloadAdapter() {
		return new ActionPayloadAdapter();
	}
}
