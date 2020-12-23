package ru.dreamteam.entities;

import lombok.Data;

@Data
public class ResponseBalanceEntity {

    private String status;
    private AccountEntity accountEntity;
    private ErrorEntity errorEntity;

}
