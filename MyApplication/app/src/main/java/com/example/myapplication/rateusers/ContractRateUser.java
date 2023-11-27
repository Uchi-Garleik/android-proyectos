package com.example.myapplication.rateusers;

import com.example.myapplication.beans.Usuario;

public interface ContractRateUser {

    public interface Presenter{
        void rateUser(Usuario usuario);
    }

    public interface Model{
        void rateUserApi(Usuario usuario,
                          ContractRateUser.Model.OnRateUserListener onRateUserListener);

        interface OnRateUserListener{
            void onFinished(Usuario usuario);
            void onFailure(String err);
        }

    }
    public interface View{
        public void successRateUser(Usuario usuario);
        void failureRateUser(String err);
    }

}
