package com.example.myapplication.loginuser.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.addproducts.view.AddProductActivity;
import com.example.myapplication.beans.Usuario;
import com.example.myapplication.listUsers.view.ListUsersActivity;
import com.example.myapplication.listproducts.view.ListMyProductsActivity;
import com.example.myapplication.loginuser.ContractLoginUser;
import com.example.myapplication.loginuser.presenter.LoginUserPresenter;

public class LoginUserActivity extends AppCompatActivity implements ContractLoginUser.View {

    SharedPreferences sharedPreferencesUserCFG;

    Button buttonLogout;
    Button buttonLogin;
    Button buttonListProducts;
    Button buttonMostSells;

    EditText username;
    EditText password;

    private LoginUserPresenter presenter = new LoginUserPresenter(this);
    private static LoginUserActivity loginUserActivity = null;
    private static LoginUserActivity getInstance(){
        return loginUserActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginUserActivity = this;
        initComponents();
    }

    private boolean isLoggedIn(){
        return sharedPreferencesUserCFG.getBoolean("isLoggedIn", false);
    }

    private void initComponents() {
        sharedPreferencesUserCFG = getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        buttonLogin = findViewById(R.id.loginBtn2);
        buttonLogout = findViewById(R.id.logoutBtn);
        buttonListProducts = findViewById(R.id.listadoProductosBtn);
        buttonMostSells = findViewById(R.id.mostSellsBtn);

        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);

        buttonMostSells.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListUsersActivity.class);
            startActivity(intent);
        });

        buttonListProducts.setOnClickListener(view -> {
            if (!isLoggedIn()){
                Toast.makeText(this, "Please Log In", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(this, ListMyProductsActivity.class);
                startActivity(intent);
            }
        });

        buttonLogout.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPreferencesUserCFG.edit();
            editor.remove("isLoggedIn");
            editor.remove("username");
            editor.remove("id");
            editor.apply();
        });

        buttonLogin.setOnClickListener(view -> {
            if ( !isLoggedIn() ){
                Usuario usuario = new Usuario();
                usuario.setUsername(username.getText().toString());
                usuario.setPassword(password.getText().toString());
                Toast.makeText(this, "wtf", Toast.LENGTH_SHORT).show();
                presenter.loginUser(usuario);
                Log.e("logged success", "logged finished");
            }else{
                String userData = "";
                userData += "{Username: " + sharedPreferencesUserCFG.getString("username", "cuke") + "},";
                userData += "Id: " + sharedPreferencesUserCFG.getInt("id",0) + "}";
                Toast.makeText(this, userData, Toast.LENGTH_SHORT).show();
                Log.e("success", "isLoggedIn returned true");
                Intent intent = new Intent(this, AddProductActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void successLoginUser(Usuario usuario) {
        Toast.makeText(this, usuario.toString(), Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = sharedPreferencesUserCFG.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.putString("username", usuario.getUsername());
        editor.putInt("id", usuario.getId());
        editor.apply();
        Log.e("success", "editor finished");
        Intent intent = new Intent(this, AddProductActivity.class);
        startActivity(intent);
    }

    @Override
    public void failureMovies(String err) {
        Log.e("wtf", "wtff");
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }
}
