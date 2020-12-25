package ru.dreamteam.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseBalanceEntity {

    private String status;
    private AccountEntity accountEntity;
    private ErrorEntity errorEntity;

}
