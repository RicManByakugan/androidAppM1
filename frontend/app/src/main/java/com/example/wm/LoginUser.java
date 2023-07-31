package com.example.wm;

public class LoginUser {
    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    private String logname;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public LoginUser(String logname,String password) {
        this.logname = logname;
        this.password = password;
    }
}
