package com.example.consumoapi_empleados.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.consumoapi_empleados.R;
import com.example.consumoapi_empleados.model.Employees;

import java.util.List;

public class ListAdapterEmployees extends RecyclerView.Adapter<ListAdapterEmployees.ViewHolder> {

    List<Employees> listEmployees;
    Context context;

    public ListAdapterEmployees(Context context,List<Employees> listEmployees) {
        this.listEmployees = listEmployees;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employees,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapterEmployees.ViewHolder holder, int position) {
        Employees employees = listEmployees.get(position);
        holder.nombre.setText(employees.getNombre());
        holder.apellidos.setText(employees.getNombre());
        holder.cargo.setText(employees.getNombre());

        Glide.with(context)
                .load(employees.getFoto())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.foto);
    }

    @Override
    public int getItemCount() { return listEmployees.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView foto;
        private final TextView nombre;
        private final TextView apellidos;
        private final TextView cargo;

        public ViewHolder(View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.foto);
            cargo = itemView.findViewById(R.id.cargo);
            nombre = itemView.findViewById(R.id.nombre);
            apellidos = itemView.findViewById(R.id.apellidos);
        }
    }
}
