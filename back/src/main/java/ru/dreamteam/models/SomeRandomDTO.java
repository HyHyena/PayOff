package ru.dreamteam.models;

import lombok.Data;

@Data
public class SomeRandomDTO {
    Double amount;
    String currency;
    String partnerPayoutId;
    String destinationId;
    Long accountId;
    Long accountGroupId;
    String method;
    String destination;
    String notificationUrl;
    String description;
    String customField;
}
