package com.example.myapplication.listUsers;

import com.example.myapplication.beans.Usuario;

import java.util.ArrayList;

public interface ContractListUsers {

    public interface Presenter{
        void listUsers(Usuario usuario);
    }

    public interface Model{
        void listUsersApi(Usuario usuario,
                               ContractListUsers.Model.OnListUsersListener onListUsersListener);

        interface OnListUsersListener {
            void onFinished(ArrayList<Usuario> usersList);
            void onFailure(String err);
        }

    }
    public interface View{
        public void successListUsers(ArrayList<Usuario> usersList);
        void failureListUsers(String err);
    }

}
