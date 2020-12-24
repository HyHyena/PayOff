package ru.dreamteam.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseStatusEntity {

    private String status;
    private ResponsePayoutEntity responsePayoutEntity;
    private ErrorEntity errorEntity;

}
