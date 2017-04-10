package com.esgi.popoll.slackbot.config;

import io.fries.slack.webhook.trigger.Trigger;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TriggerAdapterTest {
	
	private final String URLENCODED_DATA = "token=xtZTRU1GVN7ewp54Jzs0Mzv6&team_id=T33M0A69R&team_domain=esgi-4al&channel_id=D32L5K7AL&channel_name=directmessage&user_id=U34E31XM3&user_name=mrkloan&command=%2Fpopoll&text=test&response_url=https%3A%2F%2Fhooks.slack.com%2Fcommands%2FT33M0F58R%2F162575023729%2Fx7SB7Z2Uu75GnfsGdSWNYszP";
	private final List<String> SPLIT_DATA = Arrays.asList(
		"token=xtZTRU1GVN7ewp54Jzs0Mzv6",
		"team_id=T33M0A69R",
		"team_domain=esgi-4al",
		"channel_id=D32L5K7AL",
		"channel_name=directmessage",
		"user_id=U34E31XM3",
		"user_name=mrkloan",
		"command=/popoll",
		"text=test",
		"response_url=https://hooks.slack.com/commands/T33M0F58R/162575023729/x7SB7Z2Uu75GnfsGdSWNYszP"
	);
	
	private TriggerAdapter triggerAdapter;
	
	@Before
	public void initTriggerAdapter() {
		this.triggerAdapter = new TriggerAdapter();
	}
	
	@Test
	public void shouldSplitTheUrlEncodedString() {
		final List<String> splitString = triggerAdapter.splitUrlEncodedData(URLENCODED_DATA);
		
		assertThat(splitString).containsExactlyElementsOf(SPLIT_DATA);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenSplittingFromNull() {
		triggerAdapter.splitUrlEncodedData(null);
	}
	
	@Test
	public void shouldCreateAKeyValueMap() {
		final Map<String, String> triggerMap = triggerAdapter.mapValues(SPLIT_DATA);
		
		assertThat(triggerMap.get("token")).isEqualTo("xtZTRU1GVN7ewp54Jzs0Mzv6");
		assertThat(triggerMap.get("team_id")).isEqualTo("T33M0A69R");
		assertThat(triggerMap.get("team_domain")).isEqualTo("esgi-4al");
		assertThat(triggerMap.get("channel_id")).isEqualTo("D32L5K7AL");
		assertThat(triggerMap.get("channel_name")).isEqualTo("directmessage");
		assertThat(triggerMap.get("user_id")).isEqualTo("U34E31XM3");
		assertThat(triggerMap.get("user_name")).isEqualTo("mrkloan");
		assertThat(triggerMap.get("command")).isEqualTo("/popoll");
		assertThat(triggerMap.get("text")).isEqualTo("test");
		assertThat(triggerMap.get("response_url")).isEqualTo("https://hooks.slack.com/commands/T33M0F58R/162575023729/x7SB7Z2Uu75GnfsGdSWNYszP");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenMappingFromNull() {
		triggerAdapter.mapValues(null);
	}
	
	@Test
	public void shouldCreateATriggerFromUrlEncodedData() {
		final Trigger trigger = triggerAdapter.toTrigger(URLENCODED_DATA);
		
		assertThat(trigger.getToken()).isEqualTo("xtZTRU1GVN7ewp54Jzs0Mzv6");
		assertThat(trigger.getTeamId()).isEqualTo("T33M0A69R");
		assertThat(trigger.getTeamDomain()).isEqualTo("esgi-4al");
		assertThat(trigger.getChannelId()).isEqualTo("D32L5K7AL");
		assertThat(trigger.getChannelName()).isEqualTo("directmessage");
		assertThat(trigger.getUserId()).isEqualTo("U34E31XM3");
		assertThat(trigger.getUserName()).isEqualTo("mrkloan");
		assertThat(trigger.getCommand()).isEqualTo("/popoll");
		assertThat(trigger.getText()).isEqualTo("test");
		assertThat(trigger.getResponseUrl()).isEqualTo("https://hooks.slack.com/commands/T33M0F58R/162575023729/x7SB7Z2Uu75GnfsGdSWNYszP");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenCreatingFromNull() {
		triggerAdapter.toTrigger(null);
	}
}