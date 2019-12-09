package com.example.userapp2.Models;


import java.io.Serializable;

/**
 *
 */
public class Group implements Serializable {

    private String id;

    private String adminId;

    private String name;

    public Group(String id, String adminId, String name) {
        this.id = id;
        this.adminId = adminId;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (obj instanceof  Group) {
            Group tmp = (Group) obj;

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
        return "Group{" +
                "id='" + id + '\'' +
                ", adminId='" + adminId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
