package com.example.myapplication.listUsers.view;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.beans.Usuario;
import com.example.myapplication.listUsers.ContractListUsers;
import com.example.myapplication.listUsers.presenter.ListUsersPresenter;
import com.example.myapplication.listproducts.view.ListMyProductsActivity;

import java.util.ArrayList;

public class ListUsersActivity extends AppCompatActivity implements ContractListUsers.View {


    private ListUsersPresenter presenter = new ListUsersPresenter(this);

    private static ListUsersActivity listUsersActivity = null;

    private static ListUsersActivity getInstance(){
        if (listUsersActivity == null){
            listUsersActivity = new ListUsersActivity();
        }
        return listUsersActivity;
    }
    @Override
    public void successListUsers(ArrayList<Usuario> usersList) {

    }

    @Override
    public void failureMovies(String err) {

    }
}
