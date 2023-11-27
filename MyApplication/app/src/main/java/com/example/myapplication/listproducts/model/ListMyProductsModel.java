package com.example.myapplication.listproducts.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.beans.Producto;
import com.example.myapplication.listproducts.ContractListMyProducts;
import com.example.myapplication.listproducts.data.DataListMyProducts;
import com.example.myapplication.listproducts.presenter.ListMyMyProductsPresenter;
import com.example.myapplication.utils.ApiService;
import com.example.myapplication.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMyProductsModel extends AppCompatActivity implements ContractListMyProducts.Model {
    private SharedPreferences sharedPreferencesUserCFG;
    private Context context;

    private static final String IP_BASE = "192.168.1.196:8080";
//    private static final String IP_BASE = "192.168.104.75:8080";
    private ListMyMyProductsPresenter presenter;

//    public ListProductsModel(Context context){
//        this.context = context;
//    }

    public ListMyProductsModel(ListMyMyProductsPresenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void listMyProductsApi(Producto producto, OnListMyProductsListener onListMyProductsListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").create(ApiService.class);
        sharedPreferencesUserCFG = context.getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        Call<DataListMyProducts> call;

        if (sharedPreferencesUserCFG.getInt("id",0) == 0){
             call = apiService.getDataListProducts("PRODUCT.FILTER");
        }else{
            call = apiService.getDataListProducts("PRODUCT.FILTER", sharedPreferencesUserCFG.getInt("id",0));
        }

        call.enqueue(new Callback<DataListMyProducts>() {
            @Override
            public void onResponse(Call<DataListMyProducts> call, Response<DataListMyProducts> response) {
                if (response.isSuccessful()){
                    DataListMyProducts dataListMyProducts = response.body();
                    ArrayList<Producto> productsList = dataListMyProducts.getProductsList();
                    onListMyProductsListener.onFinished(productsList);
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
            public void onFailure(Call<DataListMyProducts> call, Throwable t) {
                Log.e("Response Error", "Error body:50:" + t.getMessage());
            }
        });

    }
}
