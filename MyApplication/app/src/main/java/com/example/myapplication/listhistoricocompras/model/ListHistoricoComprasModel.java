package com.example.myapplication.listhistoricocompras.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.myapplication.beans.HistoricoCompra;
import com.example.myapplication.listhistoricocompras.ContractHistoricoCompras;
import com.example.myapplication.listhistoricocompras.data.DataListHistoricoCompras;
import com.example.myapplication.listhistoricocompras.presenter.ListHistoricoComprasPresenter;
import com.example.myapplication.utils.ApiService;
import com.example.myapplication.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListHistoricoComprasModel implements ContractHistoricoCompras.Model {
    private SharedPreferences sharedPreferencesUserCFG;
    private Context context;

    private static final String IP_BASE = "192.168.1.196:8080";
//            private static final String IP_BASE = "192.168.104.75:8080";
    private ListHistoricoComprasPresenter presenter;

//    public ListProductsModel(Context context){
//        this.context = context;
//    }

    public ListHistoricoComprasModel(ListHistoricoComprasPresenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void listHistoricoComprasApi(HistoricoCompra historicoCompra, ContractHistoricoCompras.Model.OnListHistoricoComprasListener onListHistoricoComprasListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").create(ApiService.class);
        sharedPreferencesUserCFG = context.getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        Call<DataListHistoricoCompras> call;
        call = apiService.getDataListHistoricoCompras("VENTAS.FINDALL", sharedPreferencesUserCFG.getInt("id",0));
        call.enqueue(new Callback<DataListHistoricoCompras>() {
            @Override
            public void onResponse(Call<DataListHistoricoCompras> call, Response<DataListHistoricoCompras> response) {
                if (response.isSuccessful()){
                    DataListHistoricoCompras dataListHistoricoCompras = response.body();
                    ArrayList<HistoricoCompra> historicoComprasProductsList = dataListHistoricoCompras.getHistoricoComprasList();
                    Log.e("historicoCompras!!!!!!!!: ", historicoComprasProductsList.toString() );
                    onListHistoricoComprasListener.onFinished(historicoComprasProductsList);
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
            public void onFailure(Call<DataListHistoricoCompras> call, Throwable t) {
                Log.e("Response Error", "Error body:50:" + t.getMessage());
            }
        });
    }
}
