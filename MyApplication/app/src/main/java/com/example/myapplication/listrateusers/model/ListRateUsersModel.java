package com.example.myapplication.listrateusers.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.beans.Usuario;
import com.example.myapplication.listUsers.ContractListUsers;
import com.example.myapplication.listUsers.data.DataListUsers;
import com.example.myapplication.listUsers.presenter.ListUsersPresenter;
import com.example.myapplication.listrateusers.ContractListRateUsers;
import com.example.myapplication.listrateusers.data.DataListRateUsers;
import com.example.myapplication.listrateusers.presenter.ListRateUsersPresenter;
import com.example.myapplication.utils.ApiService;
import com.example.myapplication.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRateUsersModel implements ContractListRateUsers.Model {

    Context context;
    SharedPreferences sharedPreferences;

//         private static final String IP_BASE = "192.168.104.75:8080";
    private static final String IP_BASE = "192.168.1.196:8080";
    private ListRateUsersPresenter presenter;

//    public ListProductsModel(Context context){
//        this.context = context;
//    }

    public ListRateUsersModel(ListRateUsersPresenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void listRateUsersApi(Usuario usuario, ContractListRateUsers.Model.OnListRateUsersListener onListRateUsersListener) {
        sharedPreferences = context.getSharedPreferences("com.MyApp.USER_CFG",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").create(ApiService.class);
        Call<DataListRateUsers> call = null;

        if (sharedPreferences.getString("FavouriteUsers","false").equals("false") && sharedPreferences.getString("HighestSells","false").equals("false")){
            Log.e("listRateUsersApi: ", "err: 1" );
            call = apiService.getDataListRateUsers("RATING.FINDALL", sharedPreferences.getInt("id",0), "FINDALL");
        }

        if(sharedPreferences.getString("HighestSells","false").equals("true")){
            Log.e("listRateUsersApi: ", "err: 2" );
            call = apiService.getDataListRateUsers("RATING.FINDALL",sharedPreferences.getInt("id",0), "HighestSells");
        }

        if ( sharedPreferences.getString("FavouriteUsers","false").equals("true") ){
            call = apiService.getDataListRateUsers("RATING.FINDALL", sharedPreferences.getInt("id",0), "FavouriteUsers");
        }

        call.enqueue(new Callback<DataListRateUsers>() {
            @Override
            public void onResponse(Call<DataListRateUsers> call, Response<DataListRateUsers> response) {
                if (response.isSuccessful()){
                    DataListRateUsers dataListRateUsers = response.body();
                    ArrayList<Usuario> usersList = dataListRateUsers.getUsersList();
                    onListRateUsersListener.onFinished(usersList);
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
            public void onFailure(Call<DataListRateUsers> call, Throwable t) {
                Log.e("Response Error", "Error body:50:" + t.getMessage());
            }
        });

    }
}
