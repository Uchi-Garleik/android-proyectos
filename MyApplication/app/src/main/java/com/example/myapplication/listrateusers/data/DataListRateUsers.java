package com.example.myapplication.listrateusers.data;

import com.example.myapplication.beans.Usuario;

import java.util.ArrayList;

public class DataListRateUsers {
    private String message;
    private ArrayList<Usuario> usersList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Usuario> getUsersList() {
        return usersList;
    }

    public void setProductList(ArrayList<Usuario> usersList) {
        this.usersList = usersList;
    }

}
