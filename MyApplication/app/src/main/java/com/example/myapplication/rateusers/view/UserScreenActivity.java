package com.example.myapplication.rateusers.view;

import android.content.Context;
import android.content.Intent;
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
import com.example.myapplication.listproducts.view.HomeActivity;
import com.example.myapplication.listrateusers.view.ListUsersActivity;
import com.example.myapplication.loginuser.presenter.LoginUserPresenter;
import com.example.myapplication.loginuser.view.LoginUserActivity;
import com.example.myapplication.rateusers.ContractRateUser;
import com.example.myapplication.rateusers.presenter.RateUserPresenter;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class UserScreenActivity extends AppCompatActivity implements ContractRateUser.View {

    Button btnBack;

    int idUserScreenValue;
    String userNameScreenValue;

    TextView userTitle;
    Button saveRatingBtn;
    RatingBar userRatingStar;
    private ShapeableImageView userImageView;

    private String image;

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
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v->{
            Intent intent = new Intent(this, ListUsersActivity.class);
            startActivity(intent);
        });

        SharedPreferences sharedPreferences = getSharedPreferences("com.MyApp.USER_CFG", MODE_PRIVATE);
        idUserScreenValue = getIntent().getIntExtra("id",0);
        userNameScreenValue = getIntent().getStringExtra("username");
        userImageView = findViewById(R.id.userIconImage);
        userTitle = findViewById(R.id.userNameTitle);
        userRatingStar = findViewById(R.id.userRatingStar);
        saveRatingBtn = findViewById(R.id.saveRatingButton);
        String uniqueID = UUID.randomUUID().toString();
        Picasso.get().load("http://192.168.1.196:8080" + getIntent().getStringExtra("image") + "?" + uniqueID ).into(userImageView);
        userTitle.setText(getIntent().getStringExtra("username"));

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
