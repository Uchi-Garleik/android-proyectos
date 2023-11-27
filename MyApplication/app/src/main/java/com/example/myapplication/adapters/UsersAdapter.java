package com.example.myapplication.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewInterface;
import com.example.myapplication.beans.Usuario;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsuariosViewHolder> {
    public final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<Usuario> usuariosArrayList;

    public UsersAdapter(Context context, ArrayList<Usuario> usuariosArrayList, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.usuariosArrayList = usuariosArrayList;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public UsuariosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_listuser, parent, false);
        return new UsuariosViewHolder(v, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosViewHolder holder, int position) {
        Usuario usuario = usuariosArrayList.get(position);
        Log.e("onBindViewHolder: ", "Usuario:" + usuario );
        holder.userHeading.setText(usuario.getUsername());
        holder.userImage.setImageResource(usuario.getImage());
        holder.ratingBar.setRating((float) usuario.getRating());
    }

    @Override
    public int getItemCount() {
        return usuariosArrayList.size();
    }

    public static class UsuariosViewHolder extends RecyclerView.ViewHolder{
        ShapeableImageView userImage;
        TextView userHeading;
        RatingBar ratingBar;
        public UsuariosViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            userImage = itemView.findViewById(R.id.userImage);
            userHeading = itemView.findViewById(R.id.userTitle);
            ratingBar = itemView.findViewById(R.id.userRating);
            Log.e("UsuariosViewHolder: ", "USERHEADING:" + userHeading );
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
