package com.example.myapplication.addproducts.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.addproducts.ContractAddProducts;
import com.example.myapplication.addproducts.presenter.AddProductPresenter;
import com.example.myapplication.beans.Producto;
import com.example.myapplication.loginuser.view.LoginUserActivity;

public class AddProductActivity extends AppCompatActivity implements ContractAddProducts.View {

    TextView pageTitle;

    String[] categories = {"Zapatillas", "Pantalones", "Chaqueta", "Abrigos"};
    String[] currencies = {"EUR", "USD", "GBP", "RSD", "RUB"};
    String[] estados = {"Nuevo", "Usado"};

    AutoCompleteTextView categoryAutoCompleteTxtView;
    AutoCompleteTextView estadoAutoCompleteTxtView;
    AutoCompleteTextView currenciesAutoCompleteTxtView;

    ArrayAdapter<String> adapterCategories;
    ArrayAdapter<String> adapterCurrencies;
    ArrayAdapter<String> adapterEstado;
    SharedPreferences sharedPreferencesUserCFG;

    int idUsuario;
    EditText nombre;
    EditText descripcion;
    AutoCompleteTextView categoria;
    EditText marca;
    EditText talla;
    AutoCompleteTextView estado;
    EditText precio;
    AutoCompleteTextView moneda;
    Button addProductBtn;
    Button backButton;
    Button addImageButton;


    private AddProductPresenter presenter = new AddProductPresenter(this);

    private static AddProductActivity addProductActivity = null;
    private static AddProductActivity getInstance(){
        return addProductActivity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listitem);
        addProductActivity = this;
        initComponents();
    }

    private void initComponents() {
        addImageButton = findViewById(R.id.addImageBtn);
        addImageButton.setOnClickListener(view -> {
            String[] projection = new String[] {
                    "media-database-columns-to-retrieve"
            };
            String selection = "sql-where-clause-with-placeholder-variables";
            String[] selectionArgs = new String[] {
                    "values-of-placeholder-variables"
            };
            String sortOrder = "sql-order-by-clause";

            Cursor cursor = getApplicationContext().getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection,
                    selection,
                    selectionArgs,
                    sortOrder
            );

            while (cursor.moveToNext()) {
                // Use an ID column from the projection to get
                // a URI representing the media item itself.
            }

        });

        sharedPreferencesUserCFG = getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);

        pageTitle = findViewById(R.id.pageTitle);
        pageTitle.setText(pageTitle.getText().toString() + ", " + sharedPreferencesUserCFG.getString("username","DEFAULT_USERNAME") );

        categoryAutoCompleteTxtView = findViewById(R.id.categoryAutoCompleteTxtView);
        adapterCategories = new ArrayAdapter<String>(this, R.layout.list_item, categories);
        categoryAutoCompleteTxtView.setAdapter(adapterCategories);

        estadoAutoCompleteTxtView = findViewById(R.id.estadoProductoAutoCompleteTxtView);
        adapterEstado = new ArrayAdapter<String>(this, R.layout.list_item, estados);
        estadoAutoCompleteTxtView.setAdapter(adapterEstado);

        currenciesAutoCompleteTxtView = findViewById(R.id.monedaAutoCompleteTxtView);
        adapterCurrencies = new ArrayAdapter<String>(this, R.layout.list_item, currencies);
        currenciesAutoCompleteTxtView.setAdapter(adapterCurrencies);


        idUsuario = sharedPreferencesUserCFG.getInt("id",0);
        nombre = findViewById(R.id.editTextNombreArticulo);
        descripcion = findViewById(R.id.editTextDescripcionArticulo);
        categoria = findViewById(R.id.categoryAutoCompleteTxtView);
        marca = findViewById(R.id.editTextMarca);
        talla = findViewById(R.id.editTextTalla);
        estado = findViewById(R.id.estadoProductoAutoCompleteTxtView);
        precio = findViewById(R.id.editTextPrecio);
        moneda = findViewById(R.id.monedaAutoCompleteTxtView);
        addProductBtn = findViewById(R.id.buttonAddProduct);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginUserActivity.class);
            startActivity(intent);
        });

        addProductBtn.setOnClickListener(view -> {
            Toast.makeText(this, "Wtf", Toast.LENGTH_SHORT).show();
            Producto producto = new Producto();
            producto.setIdUser(idUsuario);
            producto.setNombre(nombre.getText().toString());
            producto.setDescripcion(descripcion.getText().toString());
            producto.setCategoria(categoria.getText().toString());
            producto.setMarca(marca.getText().toString());
            producto.setTalla(talla.getText().toString());
            producto.setEstado(estado.getText().toString());
            producto.setPrecio(Double.parseDouble(precio.getText().toString()));
            producto.setMoneda(moneda.getText().toString());
            Toast.makeText(this, producto.toString(), Toast.LENGTH_SHORT).show();
            presenter.addProduct(producto);
            Log.e("Producto To String:",producto.toString());
        });


    }

    @Override
    public void successAddProduct(Producto producto) {

    }

    @Override
    public void failureMovies(String err) {

    }
}
