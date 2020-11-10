package com.example.old_school_store_app.models.entities;

public class User
{
    private int id;
    private String login;
    private String password;
    private String name;
    private int bDate;
    private String phone;
    private String email;

    public User(int id, String login, String password, String name, int bDate, String phone, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.bDate = bDate;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getbDate() {
        return bDate;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
