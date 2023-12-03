package com.example.myapplication.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewInterface;
import com.example.myapplication.beans.HistoricoCompra;
import com.example.myapplication.beans.Producto;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.UUID;

public class HistoricoComprasAdapter extends RecyclerView.Adapter<HistoricoComprasAdapter.HistoricoComprasViewHolder> {
    public final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<HistoricoCompra> historicoCompraArrayList;
    String TAG = "HISTORICO ADAPTER";

    public HistoricoComprasAdapter(Context context, ArrayList<HistoricoCompra> historicoCompraArrayList, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.historicoCompraArrayList = historicoCompraArrayList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public HistoricoComprasAdapter.HistoricoComprasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder: I AM HERE" );
        View v = LayoutInflater.from(context).inflate(R.layout.item_historicocompra, parent, false);
        return new HistoricoComprasAdapter.HistoricoComprasViewHolder(v, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoricoComprasAdapter.HistoricoComprasViewHolder holder, int position) {
        Log.e(TAG, "onCreateViewHolder: I AM HERE2" );
        HistoricoCompra historicoCompra = historicoCompraArrayList.get(position);
        holder.historicoTitle.setText(historicoCompra.getNombreProducto());
        holder.historicoPrecio.setText("hola");
    }

    @Override
    public int getItemCount() {
        return historicoCompraArrayList.size();
    }

    public static class HistoricoComprasViewHolder extends RecyclerView.ViewHolder{
        ShapeableImageView historicoImage;
        TextView historicoTitle;
        TextView historicoPrecio;
        TextView historicoFecha;
        public HistoricoComprasViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            Log.e("TAG", "HistoricoComprasViewHolder: " );
            historicoImage = itemView.findViewById(R.id.historicoImage);
            historicoTitle = itemView.findViewById(R.id.historicoTitulo);
            historicoPrecio = itemView.findViewById(R.id.historicoPrecio);
            historicoFecha = itemView.findViewById(R.id.historicoFecha);
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
