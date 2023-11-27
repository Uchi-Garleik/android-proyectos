package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewInterface;
import com.example.myapplication.beans.Category;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {
    public final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<Category> categoriesArrayList;

    public CategoriesAdapter(Context context, ArrayList<Category> categoriesArrayList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.categoriesArrayList = categoriesArrayList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_listproduct, parent, false);

        return new CategoriesViewHolder(v, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        Category category = categoriesArrayList.get(position);
        holder.tvHeading.setText(category.getHeading());
        holder.titleImage.setImageResource(category.getTitleImage());
    }

    @Override
    public int getItemCount() {
        return categoriesArrayList.size();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder{

//        Button button;

        ShapeableImageView titleImage;
        TextView tvHeading;

        public CategoriesViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
//            button = itemView.findViewById(R.id.categoryButton);
//            titleImage = itemView.findViewById(R.id.title_image);
//            tvHeading = itemView.findViewById(R.id.tvHeading);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( recyclerViewInterface != null ){
                        int pos = getAdapterPosition();

                        if ( pos != RecyclerView.NO_POSITION ){
                            recyclerViewInterface.OnItemClick(pos);
                        }

                    }
                }
            });

        }
    }

}
