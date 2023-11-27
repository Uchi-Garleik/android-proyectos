package com.example.myapplication.listUsers.model;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.beans.Producto;
import com.example.myapplication.beans.Usuario;
import com.example.myapplication.listUsers.ContractListUsers;
import com.example.myapplication.listUsers.data.DataListUsers;
import com.example.myapplication.listUsers.presenter.ListUsersPresenter;
import com.example.myapplication.listproducts.data.DataListMyProducts;
import com.example.myapplication.listproducts.presenter.ListMyMyProductsPresenter;
import com.example.myapplication.utils.ApiService;
import com.example.myapplication.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUsersModel implements ContractListUsers.Model {

     private static final String IP_BASE = "192.168.104.75:8080";
//    private static final String IP_BASE = "192.168.1.196:8080";
    private ListUsersPresenter presenter;

//    public ListProductsModel(Context context){
//        this.context = context;
//    }

    public ListUsersModel(ListUsersPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void listUsersApi(Usuario usuario, OnListUsersListener onListUsersListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").create(ApiService.class);

        Call<DataListUsers> call;
        call = apiService.getDataListUsers("USER.FILTER", "");
        call.enqueue(new Callback<DataListUsers>() {
            @Override
            public void onResponse(Call<DataListUsers> call, Response<DataListUsers> response) {
                if (response.isSuccessful()){
                    DataListUsers dataListUsers = response.body();
                    ArrayList<Usuario> usersList = dataListUsers.getUsersList();
                    onListUsersListener.onFinished(usersList);
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
            public void onFailure(Call<DataListUsers> call, Throwable t) {
                Log.e("Response Error", "Error body:50:" + t.getMessage());
            }
        });

    }
}
