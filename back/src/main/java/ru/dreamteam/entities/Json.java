package ru.dreamteam.entities;

import com.google.gson.Gson;

public interface Json {
    default String objectToString(){
        return new Gson().toJson(this);
    }
}
