package com.flextao.rest;

/**
 * A Format serialize resource object and deserialize resource object from
 * string, which is parsed from request input content.
 */
public interface Format {
    public String serialize(Object obj);

    public <T> T deserialize(String str, Class<T> type);

    public String mimeType();
}
