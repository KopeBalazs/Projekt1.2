package com.adminapp2.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Question implements Serializable {

    /** Question id*/
    private String id;

    /** Time stamp. Created xxxx*/
    private String timeStamp;

    /** Duration*/
    private long duration;

    /** {@link Group} object*/
    private Group group;

    /** Kerdes */
    private String value;

    /** Aktiv vagy nem */
    private boolean state;

    public Question(String id, String timeStamp, long duration, Group group, String value, boolean state) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.duration = duration;
        this.group = group;
        this.value = value;
        this.state = state;
    }

    public boolean equals(Object obj) {
        if (obj instanceof  Question) {
            Question tmp = (Question) obj;

            if (tmp.getValue().equals(this.value)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
