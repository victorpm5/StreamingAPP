package com.isdcm.streamingapp.rest.com.isdcm.streamingapp.rest.properties;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rest")
public class RestProperties {
	
	@NonNull
	private String apiKey;
	
	@NonNull
	private String apiTag;
	
	
}
