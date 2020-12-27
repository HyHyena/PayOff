package ru.dreamteam.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayoutRequestToPlatformEntity implements Json{
    private String amount;
    private String currency;
    private String partnerPayoutId;
    private String destinationId;
    private Long accountId;
    private Long accountGroupId;
    private String method;
    private String destination;
    private Extra extra;
    private String notificationUrl;
    private String description;
    private String customField;
    private Customer customer;
}
