package com.example.myapplication.rateusers.presenter;

import android.content.Context;

import com.example.myapplication.beans.Usuario;
import com.example.myapplication.loginuser.ContractLoginUser;
import com.example.myapplication.loginuser.model.LoginUserModel;
import com.example.myapplication.rateusers.ContractRateUser;
import com.example.myapplication.rateusers.model.RateUserModel;

public class RateUserPresenter implements ContractRateUser.Presenter, ContractRateUser.Model.OnRateUserListener {
    private ContractRateUser.View view;
    private ContractRateUser.Model model;

    public RateUserPresenter(ContractRateUser.View view){
        this.view = view;
        model = new RateUserModel(this, (Context) this.view);
    }

    @Override
    public void rateUser(Usuario usuario) {
        model.rateUserApi(usuario, this);
    }

    @Override
    public void onFinished(Usuario usuario) {
        view.successRateUser(usuario);

    }

    @Override
    public void onFailure(String err) {

    }
}
