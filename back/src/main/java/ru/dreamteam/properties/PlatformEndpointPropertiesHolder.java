package ru.dreamteam.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "platform.endpoint")
@Getter
@Setter
public class PlatformEndpointPropertiesHolder {
    private String payout;
    private String balance;
    private String status;
}
