package com.example.myapplication.listcategories;

import com.example.myapplication.beans.Category;
import com.example.myapplication.beans.Producto;
import com.example.myapplication.listproducts.ContractListMyProducts;

import java.util.ArrayList;

public interface ContractListCategories {
    public interface Presenter{
        void listCategories(Category category);
    }

    public interface Model{
        void listCategoriesApi(Category category,
                               ContractListCategories.Model.OnListCategoriesListener onListCategoriesListener);

        interface OnListCategoriesListener {
            void onFinished(ArrayList<Category> categoryArrayList);
            void onFailure(String err);
        }

    }
    public interface View{
        public void successListCategories(ArrayList<Category> categoryArrayList);
        void failureListCategories(String err);
    }
}
