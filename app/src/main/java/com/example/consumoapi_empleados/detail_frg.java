package com.example.consumoapi_empleados;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.consumoapi_empleados.model.Employees;

import retrofit2.Response;
import retrofit2.http.Body;

public class detail_frg extends Fragment {

    private Employees employee;
    private TextView fullname;
    private TextView position;
    private TextView birthdate;
    private TextView experience;
    private TextView dateinit;
    private TextView country;
    private TextView city;
    private ImageView photo;
    public detail_frg(Employees employee){
        this.employee = employee;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail_frg, container, false);
        fullname = view.findViewById(R.id.fullname);
        position = view.findViewById(R.id.position);
        birthdate = view.findViewById(R.id.birthdate);
        experience = view.findViewById(R.id.yearexperience);
        dateinit = view.findViewById(R.id.dateinit);
        country = view.findViewById(R.id.country);
        city = view.findViewById(R.id.city);
        photo = view.findViewById(R.id.ivphoto);

        fullname.setText(employee.getNombre() + " \n " + employee.getApellidos());
        position.setText(employee.getCargo());
        birthdate.setText(employee.getFecha_nacimiento());
        experience.setText(String.valueOf(employee.getAni_experiencia()));
        dateinit.setText(employee.getFecha_inicio());
        country.setText(employee.getPais_residencia());
        city.setText(employee.getCiudad_residencia());

        Glide.with(this)
                .load(employee.getFoto())
                .centerCrop()
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(photo);
        // Inflate the layout for this fragment
        return view;
    }

}