package ru.dreamteam.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorEntity {
    private String code;
    private String message;
}
