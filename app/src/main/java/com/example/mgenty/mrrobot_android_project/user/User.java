package com.example.mgenty.mrrobot_android_project.user;


public class User {
    private String id;
    private String name;
    private String email;
    private String password;

    public User(){}

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getEMail(){
        return email;
    }
    public String getPassword(){
        return password;
    }



}
