package com.example.gestorcontraseas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TablaAdapter extends RecyclerView.Adapter<TablaAdapter.ViewHolderDatos> {

    ArrayList<Datos> ListaDatos;

    public TablaAdapter(ArrayList<Datos> ListaDatos){
        this.ListaDatos = ListaDatos;
    }


    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
       holder.ApartadoCorreo.setText(ListaDatos.get(position).getContra());
       holder.ApartadoSitio.setText(ListaDatos.get(position).getCorreo());
       holder.ApartadoContra.setText(ListaDatos.get(position).getRut());
    }

    @Override
    public int getItemCount() {
        return ListaDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView ApartadoSitio, ApartadoCorreo, ApartadoContra;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            ApartadoSitio = itemView.findViewById(R.id.IdSitio);
            ApartadoCorreo = itemView.findViewById(R.id.IdCorreo);
            ApartadoContra = itemView.findViewById(R.id.idContra);
        }

    }
}
