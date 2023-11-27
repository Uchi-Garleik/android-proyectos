package com.example.myapplication.listrateusers.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.HomeActivity;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewInterface;
import com.example.myapplication.listrateusers.ContractListRateUsers;
import com.example.myapplication.listrateusers.presenter.ListRateUsersPresenter;
import com.example.myapplication.rateusers.view.UserScreenActivity;
import com.example.myapplication.adapters.UsersAdapter;
import com.example.myapplication.beans.Usuario;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class ListUsersActivity extends AppCompatActivity implements ContractListRateUsers.View, RecyclerViewInterface {

    private Button backBtn;

    SharedPreferences sharedPreferences;

    private ArrayList<Usuario> usuarioArrayList;
    private ListRateUsersPresenter presenter = new ListRateUsersPresenter(this);
    private static ListUsersActivity listUsersActivity = null;

    private Button topFavouriteUsersButton;

    private Button homeButton;

    private RatingBar ratingBar;
    private String[] usuariosHeading;
    private RecyclerView recyclerView;
    private int[] imageResourceID;
    private float rating;
    private ShapeableImageView shapeableImageView;
    private ArrayList<Usuario> usuarioArrayListRecycler;


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

    @Override
    protected void onResume() {
        super.onResume();
        initComponents();
    }

    private void initComponents() {
        Usuario usuario = new Usuario();
        sharedPreferences = getSharedPreferences("com.MyApp.USER_CFG",MODE_PRIVATE);

        SharedPreferences.Editor editor2 = sharedPreferences.edit();
        editor2.putString("FavouriteUsers", "false");
        editor2.apply();

        topFavouriteUsersButton = findViewById(R.id.topFavouriteUsers);
        topFavouriteUsersButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if ( sharedPreferences.getString("FavouriteUsers","false").equals("true") ){
                editor.putString("FavouriteUsers","false");
            }
            if ( sharedPreferences.getString("FavouriteUsers","false").equals("false") ){
                editor.putString("FavouriteUsers","true");
            }
            editor.apply();
            presenter.listRateUsers(usuario);
        });

        presenter.listRateUsers(usuario);

        homeButton = findViewById(R.id.UhomeMenuBtn);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });

    }

    public int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    public ArrayList<Usuario> getUsuarioArrayList(){
        Usuario usuario = new Usuario();
        presenter.listRateUsers(usuario);
        return usuarioArrayList;
    }

    @Override
    public void successListRateUsers(ArrayList<Usuario> usersList) {
        Log.e("successListRateUsers: ","ewq" );
        Log.e("successListRateUsers: ", "holita:: " + usersList);

        // From this line until specified is the code for the RecyclerView
        dataInitialize(usersList);
        recyclerView = findViewById(R.id.usersColumn);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UsersAdapter usersAdapter = new UsersAdapter(this, usuarioArrayListRecycler, this);
        recyclerView.setAdapter(usersAdapter);
        usersAdapter.notifyDataSetChanged();

    }

    private void dataInitialize(ArrayList<Usuario> usersList) {
        usuarioArrayListRecycler = new ArrayList<>();
        Log.e("dataInitialize: ", "MI ARRAY:" + usersList.get(0).getRating() );
        imageResourceID = new int[]{
                R.drawable.neko
        };

        for (Usuario usuario : usersList) {
            Usuario usuarioAux = usuario;
//            usuarioAux.setRating(usuario.getRating());
//            usuarioAux.setId(usuario.getId());
            usuarioArrayListRecycler.add(usuarioAux);
        }

    }
    // End of the code for the RecyclerView
    @Override
    public void failureListRateUsers(String err) {

    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(this, UserScreenActivity.class);
        intent.putExtra("id",usuarioArrayListRecycler.get(position).getId());
        intent.putExtra("username",usuarioArrayListRecycler.get(position).getUsername());
        intent.putExtra("image",usuarioArrayListRecycler.get(position).getImage());
        Toast.makeText(listUsersActivity, "ID Usuario: " + usuarioArrayListRecycler.get(position).getId() + ", Username: " + usuarioArrayListRecycler.get(position).getUsername(), Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }
}