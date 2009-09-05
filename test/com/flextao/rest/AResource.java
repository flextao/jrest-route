
package com.flextao.rest;


public class AResource {

    private int id;
    private String name;

    public AResource() {
    }

    public AResource(int id) {
        this(id, null);
    }

    public AResource(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
