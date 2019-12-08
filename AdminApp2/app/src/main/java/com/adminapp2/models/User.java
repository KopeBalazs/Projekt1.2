package com.adminapp2.models;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String id;

    private Group group;

    private String name;

    private Role role;

    public User(String id, Group group, String name, Role role) {
        this.id = id;
        this.group = group;
        this.name = name;
        this.role = role;
    }

    public User() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
                ", group=" + group +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
