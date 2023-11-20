package com.example.myapplication.listUsers.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;
import com.example.myapplication.beans.Producto;
import com.example.myapplication.beans.Usuario;
import com.example.myapplication.listUsers.ContractListUsers;
import com.example.myapplication.listUsers.presenter.ListUsersPresenter;
import com.example.myapplication.listproducts.view.ListMyProductsActivity;
import com.example.myapplication.loginuser.view.LoginUserActivity;

import java.util.ArrayList;

public class ListUsersActivity extends AppCompatActivity implements ContractListUsers.View {


    private Button backBtn;


    private ListUsersPresenter presenter = new ListUsersPresenter(this);
    private static ListUsersActivity listUsersActivity = null;

    private static ListUsersActivity getInstance(){
        if (listUsersActivity == null){
            listUsersActivity = new ListUsersActivity();
        }
        return listUsersActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listusers);
        listUsersActivity = this;
        initComponents();
    }

    private void initComponents() {
        Usuario usuario = new Usuario();
        backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginUserActivity.class);
            startActivity(intent);
        });

        presenter.listUsers(usuario);

    }

    public int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    @Override
    public void successListUsers(ArrayList<Usuario> usersList) {
        LinearLayout columnLeft = findViewById(R.id.columnLeft);
        LinearLayout columnRight = findViewById(R.id.columnRight);
        LinearLayout parentElement = null;
        int counter = 0;

        for (Usuario usuario : usersList) {
            Log.e("user_prod_id", "id:" + usuario.getId());
            if (counter == 0){
                parentElement = columnLeft;
                Log.i("Counter", "hhello");
                counter = 1;
            }else{
                parentElement = columnRight;
                counter = 0;
                Log.i("Counter", "hi");
            }

            LinearLayout linearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(dpToPx(160), dpToPx(160)));
            layoutParams.setMargins(0,dpToPx(5),0,0);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setId(View.generateViewId());
            linearLayout.setGravity(Gravity.BOTTOM);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
            TextView textView = new TextView(this);
            textView.setId(View.generateViewId());
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(40)));
            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setText(usuario.getUsername());
            textView.setTextColor(ContextCompat.getColor(this, R.color.white));

            linearLayout.addView(textView);
            parentElement.addView(linearLayout);
        }

    }

    @Override
    public void failureListUsers(String err) {

    }
}
