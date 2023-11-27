package com.example.myapplication.rateusers.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.myapplication.beans.Usuario;
import com.example.myapplication.loginuser.ContractLoginUser;
import com.example.myapplication.loginuser.data.DataUsers;
import com.example.myapplication.loginuser.presenter.LoginUserPresenter;
import com.example.myapplication.rateusers.ContractRateUser;
import com.example.myapplication.rateusers.data.DataRateUser;
import com.example.myapplication.rateusers.presenter.RateUserPresenter;
import com.example.myapplication.utils.ApiService;
import com.example.myapplication.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RateUserModel implements ContractRateUser.Model {

    private Context context;
//    private static final String IP_BASE = "192.168.1.196:8080";
        private static final String IP_BASE = "192.168.104.75:8080";
    private RateUserPresenter presenter;
    SharedPreferences sharedPreferences;

    public RateUserModel(RateUserPresenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
    }


    @Override
    public void rateUserApi(Usuario usuario, ContractRateUser.Model.OnRateUserListener onRateUserListener) {
        sharedPreferences = context.getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").create(ApiService.class);

        Call<DataRateUser> call = apiService.getDataRateUser("USER.RATE", usuario.getId(), sharedPreferences.getInt("id",0), usuario.getRating());
        call.enqueue(new Callback<DataRateUser>() {
            @Override
            public void onResponse(Call<DataRateUser> call, Response<DataRateUser> response) {
                if (response.isSuccessful()){
                    DataRateUser dataRateUser = response.body();
                    ArrayList<Usuario> usersList = dataRateUser.getUsersList();
                    try {
                        onRateUserListener.onFinished(usersList.get(0));
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
            public void onFailure(Call<DataRateUser> call, Throwable t) {
                Log.e("Response Error", "Error body:50:" + t.getMessage());
            }
        });

    }
}
