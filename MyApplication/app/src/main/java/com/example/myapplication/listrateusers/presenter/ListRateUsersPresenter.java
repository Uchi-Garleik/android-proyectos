package com.example.myapplication.listrateusers.presenter;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.example.myapplication.beans.Usuario;
import com.example.myapplication.listUsers.ContractListUsers;
import com.example.myapplication.listUsers.model.ListUsersModel;
import com.example.myapplication.listrateusers.ContractListRateUsers;
import com.example.myapplication.listrateusers.model.ListRateUsersModel;

import java.util.ArrayList;

public class ListRateUsersPresenter implements ContractListRateUsers.Presenter, ContractListRateUsers.Model.OnListRateUsersListener {
    private ContractListRateUsers.View view;
    private ContractListRateUsers.Model model;
    private Fragment fragment;

    public ListRateUsersPresenter(ContractListRateUsers.View view){
        this.view = view;
        model = new ListRateUsersModel(this, (Context) view);
    }

    @Override
    public void listRateUsers(Usuario usuario) {
        model.listRateUsersApi(usuario, this);
    }

    @Override
    public void onFinished(ArrayList<Usuario> usersList) {
        view.successListRateUsers(usersList);
    }

    @Override
    public void onFailure(String err) {

    }
}
