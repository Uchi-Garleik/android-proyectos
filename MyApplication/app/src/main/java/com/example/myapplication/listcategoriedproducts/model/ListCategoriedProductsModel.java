package com.example.myapplication.listcategoriedproducts.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.myapplication.beans.Producto;
import com.example.myapplication.listcategoriedproducts.ContractListCategoriedProducts;
import com.example.myapplication.listcategoriedproducts.data.DataListCategoriedProducts;
import com.example.myapplication.listcategoriedproducts.presenter.ListCategoriedProductsPresenter;
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

public class ListCategoriedProductsModel implements ContractListCategoriedProducts.Model {
    private SharedPreferences sharedPreferencesUserCFG;
    private Context context;

//    private static final String IP_BASE = "192.168.1.196:8080";
        private static final String IP_BASE = "192.168.104.75:8080";
    private ListCategoriedProductsPresenter presenter;

//    public ListProductsModel(Context context){
//        this.context = context;
//    }

    public ListCategoriedProductsModel(ListCategoriedProductsPresenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void listCategoriedProductsApi(Producto producto, ContractListCategoriedProducts.Model.OnListCategoriedProductsListener onListCategoriedProductsListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").create(ApiService.class);
        sharedPreferencesUserCFG = context.getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        Call<DataListCategoriedProducts> call;

        if (!(producto.getCategoryArrayList().isEmpty())){
            String categories = "";
            for (String categoria : producto.getCategoryArrayList() ) {
                categories += categoria+".";
            }
//            categories = categories.substring(0,categories.length()-1);
            call = apiService.getDataListCategoriedProducts("PRODUCT.FILTERNOTMINE", categories, sharedPreferencesUserCFG.getInt("id",0));
        }else {
//            String categories = "";
            call = apiService.getDataListCategoriedProducts("PRODUCT.FILTERNOTMINE", producto.getCategoria(), sharedPreferencesUserCFG.getInt("id",0));
        }


        call.enqueue(new Callback<DataListCategoriedProducts>() {
            @Override
            public void onResponse(Call<DataListCategoriedProducts> call, Response<DataListCategoriedProducts> response) {
                if (response.isSuccessful()){
                    DataListCategoriedProducts dataListCategoriedProducts = response.body();
                    ArrayList<Producto> productsList = dataListCategoriedProducts.getProductsList();
                    onListCategoriedProductsListener.onFinished(productsList);
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
            public void onFailure(Call<DataListCategoriedProducts> call, Throwable t) {
                Log.e("Response Error", "Error body:50:" + t.getMessage());
            }
        });

    }
}
