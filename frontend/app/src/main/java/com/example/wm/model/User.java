package com.example.wm;

import java.time.LocalDateTime;

public class User {
    private String _id;
    private String name;
    private String firstname;
    private String logname;
    private String password;
    private LocalDateTime dateSubscribe;

    // Constructor
    public User(String _id, String name, String firstname, String logname, String password, LocalDateTime dateSubscribe) {
        this._id = _id;
        this.name = name;
        this.firstname = firstname;
        this.logname = logname;
        this.password = password;
        this.dateSubscribe = dateSubscribe;
    }

    // Getters and Setters
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDateSubscribe() {
        return dateSubscribe;
    }

    public void setDateSubscribe(LocalDateTime dateSubscribe) {
        this.dateSubscribe = dateSubscribe;
    }
}
