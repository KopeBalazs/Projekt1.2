package com.adminapp2.models;

import java.io.Serializable;

public class Role implements Serializable {

    private String id;

    public enum Type {
        ADMIN, BASIC
    }

    private Type type;

    public Role(String id, Type type) {
        this.id = id;
        this.type = type;
    }

    public Role() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", type=" + type +
                '}';
    }
}
