package com.example.myapplication.beans;

import android.media.Image;

public class Usuario {
    private int id;
    private String username;
    private String password;
    private int image;
    private double rating;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Usuario() {
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", image=" + image +
                ", rating=" + rating +
                '}';
    }

    public Usuario(String username, int image) {
        this.username = username;
        this.image = image;
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
