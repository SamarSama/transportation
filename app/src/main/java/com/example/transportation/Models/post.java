package com.example.transportation.Models;

public class post {
    private String name, date,Post,phone;
    public post()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPost() {
        return Post;
    }

    public void setPost(String post) {
        Post = post;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public post(String name, String phone, String date, String Post) {
        this.name = name;
        this.phone = phone;
        this.Post = Post;
        this.date=date;

    }
}
