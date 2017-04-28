package com.esgi.popoll.slackbot.config;

import io.fries.slack.webhook.trigger.ActionPayload;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ActionPayloadAdapterTest {
	
	private static final String URLENCODED_PAYLOAD = "payload=%7B%22actions%22%3A%5B%7B%22name%22%3A%22Oui%22%2C%22type%22%3A%22button%22%2C%22value%22%3A%22Oui%22%7D%5D%2C%22callback_id%22%3A%22static_callback_id%22%2C%22team%22%3A%7B%22id%22%3A%22T33M0A58R%22%2C%22domain%22%3A%22esgi-4al%22%7D%2C%22channel%22%3A%7B%22id%22%3A%22D33L5K7AL%22%2C%22name%22%3A%22directmessage%22%7D%2C%22user%22%3A%7B%22id%22%3A%22U34E31XM3%22%2C%22name%22%3A%22mrkloan%22%7D%2C%22action_ts%22%3A%221491862605.754508%22%2C%22message_ts%22%3A%221491860815.000002%22%2C%22attachment_id%22%3A%221%22%2C%22token%22%3A%22xtZSVP1GVN7ewp54Jzs0Mzv6%22%2C%22is_app_unfurl%22%3Afalse%2C%22response_url%22%3A%22https%3A%5C%2F%5C%2Fhooks.slack.com%5C%2Factions%5C%2FT33M0A58R%5C%2F167365873988%5C%2FimFSOBSvp87YUFN1VJusYZjA%22%7D";
	private static final String JSON_PAYLOAD = "{\"actions\":[{\"name\":\"Oui\",\"type\":\"button\",\"value\":\"Oui\"}],\"callback_id\":\"static_callback_id\",\"team\":{\"id\":\"T33M0A58R\",\"domain\":\"esgi-4al\"},\"channel\":{\"id\":\"D33L5K7AL\",\"name\":\"directmessage\"},\"user\":{\"id\":\"U34E31XM3\",\"name\":\"mrkloan\"},\"action_ts\":\"1491862605.754508\",\"message_ts\":\"1491860815.000002\",\"attachment_id\":\"1\",\"token\":\"xtZSVP1GVN7ewp54Jzs0Mzv6\",\"is_app_unfurl\":false,\"response_url\":\"https:\\/\\/hooks.slack.com\\/actions\\/T33M0A58R\\/167365873988\\/imFSOBSvp87YUFN1VJusYZjA\"}";
	
	private ActionPayloadAdapter actionPayloadAdapter;
	
	@Before
	public void initActionPayloadAdapter() {
		this.actionPayloadAdapter = new ActionPayloadAdapter();
	}
	
	@Test
	public void shouldExtractJSONPayload() {
		final String jsonPayload = actionPayloadAdapter.extractJsonPayload(URLENCODED_PAYLOAD);
		
		assertThat(jsonPayload).isEqualTo(JSON_PAYLOAD);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenExtractingJsonFromNull() {
		actionPayloadAdapter.extractJsonPayload(null);
	}
	
	@Test
	public void shouldCreateAnActionPayloadFromUrlEncodedData() {
		final ActionPayload actionPayload = actionPayloadAdapter.toActionPayload(URLENCODED_PAYLOAD);
		
		assertThat(actionPayload.getToken()).isEqualTo("xtZSVP1GVN7ewp54Jzs0Mzv6");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenAdapatingFromNull() {
		actionPayloadAdapter.toActionPayload(null);
	}
}