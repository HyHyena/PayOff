package ru.dreamteam.entities;

import lombok.Data;

@Data
public class Customer implements Json{
    private String userId;
    private String ip;
}
