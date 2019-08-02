package com.example.timezone.models;


public class EntityZone {

    String time;
    String timezone;

    public EntityZone(String time, String timezone) {
        this.time = time;
        this.timezone = timezone;
    }

    public String getTime() {
        return time;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
