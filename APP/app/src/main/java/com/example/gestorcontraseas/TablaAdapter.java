package com.example.gestorcontraseas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class TablaAdapter extends RecyclerView.Adapter<TablaAdapter.ViewHolderDatos> {

    ArrayList<Datos> ListaDatos;
    private Context contexto;
    public TablaAdapter(ArrayList<Datos> ListaDatos, Context context){
        this.ListaDatos = ListaDatos;
        this.contexto = context;
    }


    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
       holder.ApartadoCorreo.setText(ListaDatos.get(position).getCorreo());
       holder.ApartadoSitio.setText(ListaDatos.get(position).getSitio());
       holder.ApartadoContra.setText(ListaDatos.get(position).getContra());
       holder.MostrarDatos.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Datos dato = ListaDatos.get(holder.getAbsoluteAdapterPosition());

               Intent intent = new Intent(contexto, ModificarDatos.class);
               intent.putExtra("clave",  dato);
               intent.putExtra("posicion", holder.getAbsoluteAdapterPosition());
               contexto.startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return ListaDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView ApartadoSitio, ApartadoCorreo, ApartadoContra;
        Button MostrarDatos;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            ApartadoSitio = itemView.findViewById(R.id.IdSitio);
            ApartadoCorreo = itemView.findViewById(R.id.IdCorreo);
            ApartadoContra = itemView.findViewById(R.id.idContra);
            MostrarDatos = itemView.findViewById(R.id.BotonAbrirDatos);
        }



    }
}
