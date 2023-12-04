package com.example.myapplication.buyproduct.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.beans.Producto;
import com.example.myapplication.buyproduct.ContractBuyProduct;
import com.example.myapplication.buyproduct.presenter.BuyProductPresenter;
import com.example.myapplication.listcategoriedproducts.view.ListCategoriedProductsActivity;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BuyProductActivity extends AppCompatActivity implements ContractBuyProduct.View {

    private TextView title;
    private TextView descripcion;
    private TextView precio;
    private TextView talla;
    private TextView marca;
    private TextView estado;
    private ShapeableImageView productImage;
    private int idProducto;
    private int idUsuario;
    private SharedPreferences sharedPreferences;
    private Button comprarButton;

    private Button backBtn;


    private BuyProductPresenter presenter = new BuyProductPresenter(this);
    private static BuyProductActivity buyProductActivity = null;

    public static BuyProductActivity getInstance(){
        if (buyProductActivity == null){
            buyProductActivity = new BuyProductActivity();
        }
        return buyProductActivity;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productscreen);
        buyProductActivity = this;
        initComponents();
    }

    private void initComponents() {
        backBtn = findViewById(R.id.btnBack);
        backBtn.setOnClickListener(v->{
            Intent intent = new Intent(this, ListCategoriedProductsActivity.class);
            startActivity(intent);
        });

        sharedPreferences = getSharedPreferences("com.MyApp.USER_CFG",MODE_PRIVATE);
        idUsuario = sharedPreferences.getInt("id",0);

        idProducto = getIntent().getIntExtra("id",0);

        title = findViewById(R.id.productScreenTitle);
        title.setText(getIntent().getStringExtra("nombre"));

        descripcion = findViewById(R.id.productScreenDesc);
        descripcion.setText(getIntent().getStringExtra("descripcion"));

        precio = findViewById(R.id.productScreenPrecio);
        precio.setText(String.valueOf(getIntent().getDoubleExtra("precio",0)));

        talla = findViewById(R.id.productScreenTalla);
        talla.setText(getIntent().getStringExtra("talla"));

        marca = findViewById(R.id.productScreenMarca);
        marca.setText(getIntent().getStringExtra("marca"));

        estado = findViewById(R.id.productScreenEstado);
        estado.setText(getIntent().getStringExtra("estado"));

        productImage = findViewById(R.id.productScreenImage);
        Picasso.get().load("http://192.168.1.196:8080"+getIntent().getStringExtra("imagePath")).into(productImage);


        comprarButton = findViewById(R.id.productScreenComprar);
        comprarButton.setOnClickListener(v->{
            Producto producto = new Producto();
            producto.setId(idProducto);
            producto.setIdUser(idUsuario);
            presenter.buyProduct(producto);
            Toast.makeText(this, "Producto Comprado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ListCategoriedProductsActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void successBuyProduct(ArrayList<Producto> productsList) {

    }

    @Override
    public void failureBuyProduct(String err) {

    }
}
