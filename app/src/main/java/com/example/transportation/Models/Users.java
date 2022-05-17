package com.example.transportation.Models;

public class Users {
    private String name, phone,password,Type,adress;
    public Users()
    {

    }

    public Users(String name, String phone, String password,String type,String adress) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.adress=adress;
        this.Type=type;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

}
