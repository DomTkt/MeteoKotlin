package com.example.a727222.weatherapp.models;

import com.google.gson.JsonElement;

public final class ResponseData extends ResponseStatus {
    private JsonElement data;

    public JsonElement getData() {
        return data;
    }
}