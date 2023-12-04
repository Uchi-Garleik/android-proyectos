package com.example.myapplication.listhistoricocompras.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewInterface;
import com.example.myapplication.adapters.HistoricoComprasAdapter;
import com.example.myapplication.adapters.ProductsAdapter;
import com.example.myapplication.addproducts.view.AddProductActivity;
import com.example.myapplication.beans.HistoricoCompra;
import com.example.myapplication.listcategoriedproducts.view.ListCategoriedProductsActivity;
import com.example.myapplication.listhistoricocompras.ContractHistoricoCompras;
import com.example.myapplication.listhistoricocompras.presenter.ListHistoricoComprasPresenter;
import com.example.myapplication.listproducts.view.HomeActivity;
import com.example.myapplication.listrateusers.view.ListUsersActivity;

import java.util.ArrayList;

public class ListHistoricoComprasActivity extends AppCompatActivity implements ContractHistoricoCompras.View, RecyclerViewInterface {

    private HistoricoComprasAdapter historicoComprasAdapter;
    private RecyclerView recyclerView;
    private ArrayList<HistoricoCompra> historicoCompraArrayListRecycler;

    private ListHistoricoComprasPresenter presenter = new ListHistoricoComprasPresenter(this);
    private static ListHistoricoComprasActivity listHistoricoComprasActivity = null;
    Button homeBtn;
    Button usuariosBtn;
    Button productsBtn;
    Button addProductMenuBtn;
    Button historicoComprasBtn;
    public static ListHistoricoComprasActivity getInstance(){
        if (listHistoricoComprasActivity == null){
            listHistoricoComprasActivity = new ListHistoricoComprasActivity();
        }
        return listHistoricoComprasActivity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listHistoricoComprasActivity = this;
        setContentView(R.layout.activity_historicocompras);
        initComponents();
    }

    private void initComponents() {

        historicoComprasBtn = findViewById(R.id.basketMenuBtn);
        homeBtn = findViewById(R.id.homeMenuBtn);
        usuariosBtn = findViewById(R.id.usersMenuBtn);
        productsBtn = findViewById(R.id.categoryMenuBtn);
        addProductMenuBtn = findViewById(R.id.addProductMenuBtn);
        homeBtn = findViewById(R.id.homeMenuBtn);
        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });
        historicoComprasBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListHistoricoComprasActivity.class);
            startActivity(intent);
        });

        productsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListCategoriedProductsActivity.class);
            startActivity(intent);
        });

        usuariosBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListUsersActivity.class);
            startActivity(intent);
        });

        addProductMenuBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddProductActivity.class);
            startActivity(intent);
        });


        HistoricoCompra historicoCompra = new HistoricoCompra();

        presenter.listHistoricoCompras(historicoCompra);
    }

    @Override
    public void successListHistoricoCompras(ArrayList<HistoricoCompra> historicoCompraArrayList) {
        dataInitialize(historicoCompraArrayList);
        recyclerView = findViewById(R.id.historicoComprasColumn);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        historicoComprasAdapter = new HistoricoComprasAdapter(this, historicoCompraArrayListRecycler, this);
        recyclerView.setAdapter(historicoComprasAdapter);
        historicoComprasAdapter.notifyDataSetChanged();
    }

    private void dataInitialize(ArrayList<HistoricoCompra> historicoCompraArrayList) {
        historicoCompraArrayListRecycler = new ArrayList<>();
        historicoCompraArrayListRecycler.addAll(historicoCompraArrayList);
    }

    @Override
    public void failureListHistoricoCompras(String err) {

    }

    @Override
    public void OnItemClick(int position) {

    }
}
