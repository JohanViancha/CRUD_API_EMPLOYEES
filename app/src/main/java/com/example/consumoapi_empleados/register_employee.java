package com.example.consumoapi_empleados;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.consumoapi_empleados.model.Employees;
import com.example.consumoapi_empleados.utils.Alert;
import com.example.consumoapi_empleados.utils.Apis;
import com.example.consumoapi_empleados.utils.EmployeeServices;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class register_employee extends Fragment {
    private TextView regName;
    private TextView regEndName;
    private TextView regPosition;
    private TextView regDateBirth;
    private TextView regExperience;
    private TextView regDateInit;
    private TextView regCountry;
    private TextView regCity;
    private TextView regUrlImage;
    private ImageView regImage;
    private TextInputLayout textLayoutUrl;
    private ScrollView scrollView;
    private Button btnRegisterEmployee;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_employee, container, false);
        Alert alert = new Alert(context);
        regName = view.findViewById(R.id.regName);
        regEndName = view.findViewById(R.id.regEndName);
        regPosition = view.findViewById(R.id.regPosition);
        regDateBirth = view.findViewById(R.id.regDateBirth);
        regExperience = view.findViewById(R.id.regExperience);
        regDateInit = view.findViewById(R.id.regDateInit);
        regCountry = view.findViewById(R.id.regCountry);
        regCity = view.findViewById(R.id.regCity);
        regUrlImage = view.findViewById(R.id.regUrlImage);
        regImage = view.findViewById(R.id.regImage);
        textLayoutUrl = view.findViewById(R.id.textLayoutUrl);
        scrollView = view.findViewById(R.id.scrollRegister);
        btnRegisterEmployee = view.findViewById(R.id.btnRegisterEmployee);

        textLayoutUrl.setStartIconOnClickListener(v -> {
            if(!regUrlImage.getText().equals("")){
                Glide.with(this)
                        .load(regUrlImage.getText().toString())
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .into(regImage);
            }
        });

        textLayoutUrl.setEndIconOnClickListener(v -> {
            regImage.setImageResource(0);
            regUrlImage.setText("");

        });

        btnRegisterEmployee.setOnClickListener(v -> {
            Employees employee = new Employees(regName.getText().toString(),
                    regEndName.getText().toString(), regPosition.getText().toString(),
                    regUrlImage.getText().toString(),regDateBirth.getText().toString(),
                    regDateInit.getText().toString(),
                    Integer.parseInt(regExperience.getText().toString()),
                    regCountry.getText().toString(), regCity.getText().toString());

            EmployeeServices employeeServices = Apis.getEmployeeService();
            Call<Boolean> callSave = employeeServices.save(employee);
            callSave.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if(response != null){
                        alert.getAlert("¡El empleado ha sido registrado!");
                    }
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                   Log.e(null,t.getCause().toString());
                   alert.getAlert("El tiempo ha excedido, intente de nuevo");
                }
            });

        });

        return view;
    }
}