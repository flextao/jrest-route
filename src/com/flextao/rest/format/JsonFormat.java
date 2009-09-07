package com.flextao.rest.format;

import com.flextao.rest.Format;
import com.google.gson.Gson;

public class JsonFormat implements Format {

    private final Gson gson;

    public JsonFormat() {
        this(new Gson());
    }

    public JsonFormat(Gson gson) {
        this.gson = gson;
    }

    public <T> T deserialize(String json, Class<T> type) {
        return gson.fromJson(json, type);
    }

    public String serialize(Object obj) {
        return gson.toJson(obj);
    }

}
