package com.example.myapplication.addproducts.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;

import com.example.myapplication.addproducts.ContractAddProducts;
import com.example.myapplication.addproducts.data.DataProducts;
import com.example.myapplication.addproducts.presenter.AddProductPresenter;
import com.example.myapplication.beans.Producto;
import com.example.myapplication.utils.ApiService;
import com.example.myapplication.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductModel implements ContractAddProducts.Model {

    private static final String IP_BASE = "192.168.1.196:8080";
//private static final String IP_BASE = "192.168.104.75:8080";
    private AddProductPresenter presenter;
    public AddProductModel(AddProductPresenter presenter){this.presenter = presenter;}

    @Override
    public void addProductsApi(Producto producto, OnAddProductListener onAddProductListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").create(ApiService.class);


        RequestBody imageRequestBody = RequestBody.create(MediaType.parse("image/png"), Base64.encodeToString(producto.getImage(), Base64.DEFAULT));
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", "myImage", imageRequestBody);
        //Call<DataProducts> call = apiService.getDataAddProduct("PRODUCT.ADD", producto.getNombre(), producto.getDescripcion(), producto.getCategoria(), producto.getMarca(), producto.getTalla(), producto.getEstado(), producto.getPrecio(), producto.getMoneda(), producto.getIdUser(), Base64.encodeToString(producto.getImage(), Base64.DEFAULT));
        Call<DataProducts> call = apiService.getDataAddProduct("PRODUCT.ADD", producto.getNombre(), producto.getDescripcion(), producto.getCategoria(), producto.getMarca(), producto.getTalla(), producto.getEstado(), producto.getPrecio(), producto.getMoneda(), producto.getIdUser(), imagePart);

        call.enqueue(new Callback<DataProducts>() {
            @Override
            public void onResponse(Call<DataProducts> call, Response<DataProducts> response) {
                if (response.isSuccessful()){
                    DataProducts dataProducts = response.body();
                    ArrayList<Producto> productList = dataProducts.getProductList();
                    onAddProductListener.onFinished(productList.get(0));
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
            public void onFailure(Call<DataProducts> call, Throwable t) {
                Log.e("Response Error", "Error body:50:" + t.getMessage());
            }
        });

    }
}
