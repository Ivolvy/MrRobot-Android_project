package com.example.mgenty.mrrobot_android_project.crypto;


public class Secret {
    private String password;

    public Secret() {
    }

    public Secret(String password) {
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
