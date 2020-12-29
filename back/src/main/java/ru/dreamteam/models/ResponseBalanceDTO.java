package ru.dreamteam.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseBalanceDTO {

    private String status;
    private Account account;
    private Error error;

}
