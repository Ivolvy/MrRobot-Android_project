package com.example.mgenty.mrrobot_android_project.user;


public class User {
    private String id;
    private String name;
    private String eMail;
    private String password;

    public User(){}

    public User(String name, String eMail, String password){
        this.name = name;
        this.eMail = eMail;
        this.password = password;
    }

    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getEMail(){
        return eMail;
    }
    public String getPassword(){
        return password;
    }



}
