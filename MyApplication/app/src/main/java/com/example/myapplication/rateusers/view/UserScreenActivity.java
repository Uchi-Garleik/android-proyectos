package com.example.myapplication.rateusers.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.beans.Usuario;
import com.example.myapplication.listcategoriedproducts.view.ListCategoriedProductsActivity;
import com.example.myapplication.loginuser.presenter.LoginUserPresenter;
import com.example.myapplication.loginuser.view.LoginUserActivity;
import com.example.myapplication.rateusers.ContractRateUser;
import com.example.myapplication.rateusers.presenter.RateUserPresenter;

public class UserScreenActivity extends AppCompatActivity implements ContractRateUser.View {

    int idUserScreenValue;
    String userNameScreenValue;

    TextView userTitle;
    Button saveRatingBtn;
    RatingBar userRatingStar;

    private RateUserPresenter presenter = new RateUserPresenter(this);
    private static UserScreenActivity userScreenActivity = null;
    public static UserScreenActivity getInstance() {
        if (userScreenActivity == null){
            userScreenActivity = new UserScreenActivity();
        }
        return userScreenActivity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userscreen);
        userScreenActivity = this;
        initComponents();
    }

    private void initComponents() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.MyApp.USER_CFG", MODE_PRIVATE);
        idUserScreenValue = getIntent().getIntExtra("id",0);
        userNameScreenValue = getIntent().getStringExtra("username");
        userTitle = findViewById(R.id.userNameTitle);
        userRatingStar = findViewById(R.id.userRatingStar);
        saveRatingBtn = findViewById(R.id.saveRatingButton);
        saveRatingBtn.setOnClickListener(v -> {
            String zxy = "[ID USER:" + idUserScreenValue + "], [RATING:" + userRatingStar.getRating()+"], [MY USER ID:" + sharedPreferences.getInt("id",0) +"]";
            Toast.makeText(this, zxy, Toast.LENGTH_SHORT).show();
            Usuario usuario = new Usuario();
            usuario.setId(idUserScreenValue);
            usuario.setRating(userRatingStar.getRating());
            presenter.rateUser(usuario);
        });
    }

    @Override
    public void successRateUser(Usuario usuario) {

    }

    @Override
    public void failureRateUser(String err) {

    }
}
