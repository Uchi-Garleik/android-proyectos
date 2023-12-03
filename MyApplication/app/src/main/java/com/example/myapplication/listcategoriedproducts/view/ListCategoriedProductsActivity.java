package com.example.myapplication.listcategoriedproducts.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewInterface;
import com.example.myapplication.adapters.ProductsAdapter;
import com.example.myapplication.addproducts.view.AddProductActivity;
import com.example.myapplication.beans.Category;
import com.example.myapplication.beans.Producto;
import com.example.myapplication.buyproduct.view.BuyProductActivity;
import com.example.myapplication.listcategoriedproducts.ContractListCategoriedProducts;
import com.example.myapplication.listcategoriedproducts.presenter.ListCategoriedProductsPresenter;
import com.example.myapplication.listproducts.view.HomeActivity;
import com.example.myapplication.listrateusers.view.ListUsersActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListCategoriedProductsActivity extends AppCompatActivity implements ContractListCategoriedProducts.View, RecyclerViewInterface {


    private SharedPreferences sharedPreferences;

    private SearchView searchView;
    ProductsAdapter productsAdapter;
    Button menBtn;
    Button womenBtn;
    Button homeBtn;
    Button usuariosBtn;
    Button productsBtn;
    Button addProductMenuBtn;
    private ArrayList<String> categoryArray;
    private ArrayList<Producto> productoArrayList;
    private ListCategoriedProductsPresenter presenter = new ListCategoriedProductsPresenter(this);
    private static ListCategoriedProductsActivity listCategoriedProductsActivity = null;

    private RecyclerView recyclerView;
    private ArrayList<Producto> productoArrayListRecycler;

    private RecyclerView categoryRecyclerView;
    private ArrayList<Category> categoryArrayListRecycler;


    public static ListCategoriedProductsActivity getInstance() {
        if (listCategoriedProductsActivity == null){
            listCategoriedProductsActivity = new ListCategoriedProductsActivity();
        }
        return listCategoriedProductsActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorieditems);
        listCategoriedProductsActivity = this;
        sharedPreferences = getSharedPreferences("com.MyApp.PRODUCTS",MODE_PRIVATE);
        initComponents();
    }

    private void initComponents() {
        searchView = findViewById(R.id.searchProduct);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

        categoryArray = new ArrayList<>();
        Producto producto = new Producto();
        producto.setCategoria("");
        menBtn = findViewById(R.id.menBtn);
        womenBtn = findViewById(R.id.womenBtn);

        homeBtn = findViewById(R.id.homeMenuBtn);
        usuariosBtn = findViewById(R.id.usersMenuBtn);
        productsBtn = findViewById(R.id.categoryMenuBtn);
        addProductMenuBtn = findViewById(R.id.addProductMenuBtn);

        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
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



        menBtn.setOnClickListener(v -> {

            if (categoryArray.contains("men")){
                categoryArray.remove("men");
                menBtn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.accent_200)));
            }else{
                categoryArray.add("men");
                menBtn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.accent_100)));
            }
            Log.e("initComponents: ", "MY FILTERS FOR PRODUCTS:" + categoryArray );
            producto.setCategoryArrayList(categoryArray);
            presenter.listCategoriedProducts(producto);
        });

        womenBtn.setOnClickListener(v -> {
            if (categoryArray.contains("women")){
                categoryArray.remove("women");
                womenBtn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.accent_200)));
            }else{
                categoryArray.add("women");
                womenBtn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.accent_100)));
            }
            Log.e("initComponents: ", "MY FILTERS FOR PRODUCTS:" + categoryArray );
            producto.setCategoryArrayList(categoryArray);
            presenter.listCategoriedProducts(producto);
        });

        presenter.listCategoriedProducts(producto);
    }

    private void filterList(String newText) {
        ArrayList<Producto> filterList = new ArrayList<>();
        for (Producto producto : productoArrayListRecycler){
            if (producto.getNombre().toLowerCase().contains(newText.toLowerCase())){
                filterList.add(producto);
            }
        }

        if (filterList.isEmpty()){
            Toast.makeText(this, "No", Toast.LENGTH_SHORT).show();
        }else{
            productsAdapter.setFilteredList(filterList);
        }

    }

    @Override
    public void successListCategoriedProducts(ArrayList<Producto> productsList) {
        Log.e("successListCategoriedProducts: ", "MY ARRAY:" + productsList );
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        dataInitialize(productsList);
        recyclerView = findViewById(R.id.productsColumn);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        productsAdapter = new ProductsAdapter(this, productoArrayListRecycler, this);
        recyclerView.setAdapter(productsAdapter);
        productsAdapter.notifyDataSetChanged();
    }

    private void dataInitialize(ArrayList<Producto> productsList) {
        productoArrayListRecycler = new ArrayList<>();
        for (Producto producto : productsList) {
            Producto productoAux = producto;
            productoArrayListRecycler.add(productoAux);
        }
        Log.e("dataInitialize: ", "MY ARRAY:" + productoArrayListRecycler );
    }

    @Override
    public void failureListCategoriedProducts(String err) {

    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(this, BuyProductActivity.class);
        intent.putExtra("id",productoArrayListRecycler.get(position).getId());
        intent.putExtra("nombre", productoArrayListRecycler.get(position).getNombre());
        intent.putExtra("descripcion", productoArrayListRecycler.get(position).getDescripcion());
        intent.putExtra("imagePath", productoArrayListRecycler.get(position).getImagePath());
        intent.putExtra("precio", productoArrayListRecycler.get(position).getPrecio());
        intent.putExtra("moneda",productoArrayListRecycler.get(position).getMoneda());
        intent.putExtra("estado",productoArrayListRecycler.get(position).getEstado());
        intent.putExtra("marca",productoArrayListRecycler.get(position).getMarca());
        intent.putExtra("talla",productoArrayListRecycler.get(position).getTalla());
        startActivity(intent);
        Toast.makeText(this, productoArrayListRecycler.get(position).toString(), Toast.LENGTH_SHORT).show();
        Log.d("ProductInfo: ", "" + productoArrayListRecycler.get(position).toString());
    }
}

