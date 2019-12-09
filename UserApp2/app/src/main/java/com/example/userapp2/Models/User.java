package com.example.userapp2.Models;

import java.io.Serializable;

public class User implements Serializable {

    private String id;

    private String name;

    public User(String id, /*Group group,*/ String name) {
        this.id = id;
        //this.group = group;
        this.name = name;
    }

    public User() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (obj instanceof  User) {
            User tmp = (User) obj;

            if (tmp.getName().equals(this.name)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
