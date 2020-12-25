package ru.dreamteam.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponsePayoutEntity {

    private String id;
    private String partnerPayoutId;
    private String accountId;
    private String method;
    private String amount;
    private String destination;
    private String status;
    private String date;
    private ErrorEntity errorEntity;

}
