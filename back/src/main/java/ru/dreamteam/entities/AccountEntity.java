package ru.dreamteam.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountEntity {

    private String id;
    private String currency;
    private Long balance;
    private Long balanceFrozen;

}
