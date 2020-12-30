package ru.dreamteam.models;

import lombok.Data;

@Data
public class Customer implements Json{
    private String userId;
    private String ip;
}
