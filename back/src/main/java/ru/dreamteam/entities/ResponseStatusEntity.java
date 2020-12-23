package ru.dreamteam.entities;

import lombok.Data;

@Data
public class ResponseStatusEntity {

    private String status;
    private ResponsePayoutEntity responsePayoutEntity;
    private ErrorEntity errorEntity;

}
