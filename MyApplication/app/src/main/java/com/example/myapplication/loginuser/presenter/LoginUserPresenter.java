package com.example.myapplication.loginuser.presenter;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.addproducts.ContractAddProducts;
import com.example.myapplication.addproducts.model.AddProductModel;
import com.example.myapplication.beans.Producto;
import com.example.myapplication.beans.Usuario;
import com.example.myapplication.loginuser.ContractLoginUser;
import com.example.myapplication.loginuser.model.LoginUserModel;

public class LoginUserPresenter extends AppCompatActivity implements ContractLoginUser.Presenter, ContractLoginUser.Model.OnLoginUserListener {

    private ContractLoginUser.View view;
    private ContractLoginUser.Model model;

    public LoginUserPresenter(ContractLoginUser.View view){
        this.view = view;
        model = new LoginUserModel(this);
    }

    @Override
    public void loginUser(Usuario usuario) {
        model.loginUserApi(usuario, this);
    }

    @Override
    public void onFinished(Usuario usuario) {
        view.successLoginUser(usuario);

    }

    @Override
    public void onFailure(String err) {

    }
}
