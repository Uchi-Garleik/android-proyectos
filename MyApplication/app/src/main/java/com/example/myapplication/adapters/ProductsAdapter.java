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
import com.example.myapplication.beans.Producto;
import com.example.myapplication.beans.Usuario;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {
    public final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<Producto> productoArrayList;

    public ProductsAdapter(Context context, ArrayList<Producto> productoArrayList, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.productoArrayList = productoArrayList;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    public void setFilteredList(ArrayList<Producto> filteredList){
        this.productoArrayList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductsAdapter.ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_listproduct, parent, false);
        return new ProductsAdapter.ProductsViewHolder(v, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ProductsViewHolder holder, int position) {
        Producto producto = productoArrayList.get(position);
        Log.e("onBindViewHolder: ", "Producto:" + producto );
        holder.productPrice.setText(String.valueOf(producto.getPrecio())+"€");
        holder.productHeading.setText(producto.getNombre());
        holder.productMarca.setText(producto.getMarca());
        String uniqueID = UUID.randomUUID().toString();
        Picasso.get().load("http://192.168.1.196:8080"+producto.getImagePath()+"?" + uniqueID).into(holder.productImage);
//        Log.e("onBindViewHolder: ", "http://192.168.104.75:8080" + producto.getImagePath()+"?" + uniqueID);
//        Picasso.get().load("http://192.168.104.75:8080"+producto.getImagePath()+"?" + uniqueID).into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return productoArrayList.size();
    }

    public static class ProductsViewHolder extends RecyclerView.ViewHolder{
        ShapeableImageView productImage;
        TextView productHeading;
        TextView productPrice;
        TextView productMarca;
        public ProductsViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel.Builder()
                    .setAllCorners(CornerFamily.ROUNDED, 10) // Set the radius for all corners to 10dp
                    .build();
            productImage = itemView.findViewById(R.id.productImage);
            productImage.setShapeAppearanceModel(shapeAppearanceModel);
            productHeading = itemView.findViewById(R.id.productTitle);
            productPrice = itemView.findViewById(R.id.productPrice);
            productMarca = itemView.findViewById(R.id.productMarca);
            Log.e("ProductsViewHolder: ", "PRODUCTHEADING: " + productHeading );
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
