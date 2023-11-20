package com.example.myapplication.loginuser.model;

import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.addproducts.ContractAddProducts;
import com.example.myapplication.addproducts.data.DataProducts;
import com.example.myapplication.addproducts.presenter.AddProductPresenter;
import com.example.myapplication.beans.Producto;
import com.example.myapplication.beans.Usuario;
import com.example.myapplication.loginuser.ContractLoginUser;
import com.example.myapplication.loginuser.data.DataUsers;
import com.example.myapplication.loginuser.presenter.LoginUserPresenter;
import com.example.myapplication.loginuser.view.LoginUserActivity;
import com.example.myapplication.utils.ApiService;
import com.example.myapplication.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUserModel implements ContractLoginUser.Model {

    private static final String IP_BASE = "192.168.104.75:8080";
    private LoginUserPresenter presenter;
    public LoginUserModel(LoginUserPresenter presenter){this.presenter = presenter;}

    @Override
    public void loginUserApi(Usuario usuario, OnLoginUserListener onLoginUserListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").create(ApiService.class);
        Log.d("USUARIO LISTO PARA LOGEAR:", usuario.toString());

        Call<DataUsers> call = apiService.getDataLoginUser("USER.LOGIN", usuario.getUsername(), usuario.getPassword());
        call.enqueue(new Callback<DataUsers>() {
            @Override
            public void onResponse(Call<DataUsers> call, Response<DataUsers> response) {
                if (response.isSuccessful()){
                    DataUsers dataUsers = response.body();
                    ArrayList<Usuario> usersList = dataUsers.getUsersList();
                    try {
                        onLoginUserListener.onFinished(usersList.get(0));
                    }catch(IndexOutOfBoundsException e){
                        Log.e("No Found User","no user exists");
                    }

                }else{
                    Log.e("Response Error", "HTTP state:38:" + response.code());
                    try{
                        String errorBody = response.errorBody().string();
                        Log.e("Response error", "Error body:41: " + errorBody);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataUsers> call, Throwable t) {
                Log.e("Response Error", "Error body:50:" + t.getMessage());
            }
        });

    }
}

