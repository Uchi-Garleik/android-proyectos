package com.example.myapplication.listUsers.presenter;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.beans.Usuario;
import com.example.myapplication.listUsers.ContractListUsers;
import com.example.myapplication.listUsers.model.ListUsersModel;
import com.example.myapplication.listproducts.ContractListMyProducts;
import com.example.myapplication.listproducts.model.ListMyProductsModel;

import java.util.ArrayList;

public class ListUsersPresenter extends AppCompatActivity implements ContractListUsers.Presenter, ContractListUsers.Model.OnListUsersListener {

    private ContractListUsers.View view;
    private ContractListUsers.Model model;

    public ListUsersPresenter(ContractListUsers.View view){
        this.view = view;
        model = new ListUsersModel(this);
    }

    @Override
    public void listUsers(Usuario usuario) {
        model.listUsersApi(usuario, this);
    }

    @Override
    public void onFinished(ArrayList<Usuario> usersList) {
        view.successListUsers(usersList);
    }

    @Override
    public void onFailure(String err) {

    }
}
