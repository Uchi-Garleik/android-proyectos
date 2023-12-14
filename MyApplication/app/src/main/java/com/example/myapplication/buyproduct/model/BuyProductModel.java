package com.example.myapplication.buyproduct.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.myapplication.beans.Producto;
import com.example.myapplication.buyproduct.ContractBuyProduct;
import com.example.myapplication.buyproduct.data.DataBuyProduct;
import com.example.myapplication.buyproduct.presenter.BuyProductPresenter;
import com.example.myapplication.listcategoriedproducts.ContractListCategoriedProducts;
import com.example.myapplication.listcategoriedproducts.data.DataListCategoriedProducts;
import com.example.myapplication.listcategoriedproducts.presenter.ListCategoriedProductsPresenter;
import com.example.myapplication.utils.ApiService;
import com.example.myapplication.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyProductModel implements ContractBuyProduct.Model {
    private SharedPreferences sharedPreferencesUserCFG;
    private Context context;

    private static final String IP_BASE = "192.168.1.196:8080";
//            private static final String IP_BASE = "192.168.104.75:8080";
    private BuyProductPresenter presenter;

//    public ListProductsModel(Context context){
//        this.context = context;
//    }

    public BuyProductModel(BuyProductPresenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void buyProductApi(Producto producto, ContractBuyProduct.Model.OnBuyProductListener onBuyProductListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").create(ApiService.class);
        sharedPreferencesUserCFG = context.getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        Call<DataBuyProduct> call;

        call = apiService.getDataBuyProduct("VENTAS.ADD", producto.getId(), producto.getIdUser());


        call.enqueue(new Callback<DataBuyProduct>() {
            @Override
            public void onResponse(Call<DataBuyProduct> call, Response<DataBuyProduct> response) {
                if (response.isSuccessful()){
                    DataBuyProduct dataBuyProduct = response.body();
                    ArrayList<Producto> productsList = dataBuyProduct.getProductsList();
                    onBuyProductListener.onFinished(productsList);
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
            public void onFailure(Call<DataBuyProduct> call, Throwable t) {
                Log.e("Response Error", "Error body:50:" + t.getMessage());
            }
        });

    }
}
