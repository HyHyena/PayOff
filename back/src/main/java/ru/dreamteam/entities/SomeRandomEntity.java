package ru.dreamteam.entities;

import lombok.Data;

@Data
public class SomeRandomEntity {
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
