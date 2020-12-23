package ru.dreamteam.entities;

import lombok.Data;

@Data
public class AccountEntity {

    private String id;
    private String currency;
    private Long balance;
    private Long balanceFrozen;

}
