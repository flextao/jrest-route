
package com.flextao.rest;

public interface Format {
    public String serialize(Object obj);

    public <T> T deserialize(String str, Class<T> type);
}
