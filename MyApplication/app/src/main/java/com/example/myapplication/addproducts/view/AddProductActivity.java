package com.example.myapplication.addproducts.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.addproducts.ContractAddProducts;
import com.example.myapplication.addproducts.presenter.AddProductPresenter;
import com.example.myapplication.beans.Producto;
import com.example.myapplication.listproducts.view.HomeActivity;
import com.example.myapplication.loginuser.view.LoginUserActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddProductActivity extends AppCompatActivity implements ContractAddProducts.View {

    private ActivityResultLauncher<PickVisualMediaRequest> pickMediaLauncher;

    TextView pageTitle;

    String[] categories = {"Zapatillas", "Pantalones", "Chaqueta", "Abrigos"};
    String[] currencies = {"EUR", "USD", "GBP", "RSD", "RUB"};
    String[] estados = {"Nuevo", "Usado"};

    Uri imgUri;
    ImageView addImageBtn;
    Bitmap selectedImage;

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


    private AddProductPresenter presenter = new AddProductPresenter(this);

    private static AddProductActivity addProductActivity = null;
    private static AddProductActivity getInstance(){
        return addProductActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listitem);
        addImageBtn = findViewById(R.id.addImageBtn);

        pickMediaLauncher = registerForActivityResult(new PickVisualMedia(), uri -> {
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                imgUri = uri;
                Log.d("PhotoPicker", "Selected URI: " + imgUri);
                addImageBtn.setImageURI(imgUri);

            } else {
                Log.d("PhotoPicker", "No media selected");
            }
        });


        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the photo picker activity here
                // Literally just ignore the error this gives.
                // # It just works #
                pickMediaLauncher.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(PickVisualMedia.ImageAndVideo.INSTANCE)
                        .build());
            }
        });


        AutoCompleteTextView categoriesAutoCompleteTextView = findViewById(R.id.categoryAutoCompleteTxtView);
        String[] categoriesArrayString = getResources().getStringArray(R.array.categories);
        ArrayAdapter<String> categoriesArrayAdapter = new ArrayAdapter(this,R.layout.dropdown_item, categoriesArrayString);
        categoriesAutoCompleteTextView.setAdapter(categoriesArrayAdapter);

        AutoCompleteTextView estadoProductoAutoCompleteTextView = findViewById(R.id.estadoProductoAutoCompleteTxtView);
        String[] estadosArrayString = getResources().getStringArray(R.array.estados);
        ArrayAdapter<String> estadoProductoArrayAdapter = new ArrayAdapter(this,R.layout.dropdown_item, estadosArrayString);
        estadoProductoAutoCompleteTextView.setAdapter(estadoProductoArrayAdapter);

        AutoCompleteTextView monedaAutoCompleteTextView = findViewById(R.id.monedaAutoCompleteTxtView);
        String[] monedaArrayString = getResources().getStringArray(R.array.monedas);
        ArrayAdapter<String> monedasArrayAdapter = new ArrayAdapter(this,R.layout.dropdown_item, monedaArrayString);
        monedaAutoCompleteTextView.setAdapter(monedasArrayAdapter);





        addProductActivity = this;
        initComponents();
    }


    private void initComponents() {

        sharedPreferencesUserCFG = getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);

        pageTitle = findViewById(R.id.pageTitle);
        pageTitle.setText(pageTitle.getText().toString() + ", " + sharedPreferencesUserCFG.getString("username","DEFAULT_USERNAME") );

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
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });

        addProductBtn.setOnClickListener(view -> {
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

            try {
                InputStream imageStream = getContentResolver().openInputStream(imgUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                selectedImage.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] imageBytes = byteArrayOutputStream.toByteArray();
                producto.setImage(imageBytes);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }


            presenter.addProduct(producto);
        });


    }

    @Override
    public void successAddProduct(Producto producto) {

    }

    @Override
    public void failureMovies(String err) {

    }
}
