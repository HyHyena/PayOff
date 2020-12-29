package ru.dreamteam.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseStatusDTO {

    private String status;
    private ResponsePayoutDTO responsePayoutDTO;
    private Error error;

}
