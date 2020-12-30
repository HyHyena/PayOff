package ru.dreamteam.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "platform")
@Getter
@Setter
public class PlatformPropertiesHolder {
    private String algorithm;
    private String secret;
    private String XPartnerApiKey;
    private String partnerPayoutId;
    private Long accountId;

    private String externalHost;

}
