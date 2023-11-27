package com.example.myapplication.listrateusers;

import com.example.myapplication.beans.Usuario;
import com.example.myapplication.listUsers.ContractListUsers;

import java.util.ArrayList;

public interface ContractListRateUsers {
    public interface Presenter{
        void listRateUsers(Usuario usuario);
    }

    public interface Model{
        void listRateUsersApi(Usuario usuario,
                          ContractListRateUsers.Model.OnListRateUsersListener onListRateUsersListener);

        interface OnListRateUsersListener {
            void onFinished(ArrayList<Usuario> usersList);
            void onFailure(String err);
        }

    }
    public interface View{
        public void successListRateUsers(ArrayList<Usuario> usersList);
        void failureListRateUsers(String err);
    }
}
