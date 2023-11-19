package com.example.myapplication.loginuser;


import com.example.myapplication.beans.Usuario;

public interface ContractLoginUser {

    public interface Presenter{
        void loginUser(Usuario usuario);
    }

    public interface Model{
        void loginUserApi(Usuario usuario,
                            OnLoginUserListener onLoginUserListener);

        interface OnLoginUserListener{
            void onFinished(Usuario usuario);
            void onFailure(String err);
        }

    }
    public interface View{
        public void successLoginUser(Usuario usuario);
        void failureMovies(String err);
    }

}
