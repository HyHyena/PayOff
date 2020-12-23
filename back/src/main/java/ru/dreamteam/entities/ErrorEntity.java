package ru.dreamteam.entities;

import lombok.Data;

@Data
public class ErrorEntity {
    private String code;
    private String message;
}
