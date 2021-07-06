package com.example.consumoapi_empleados;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.consumoapi_empleados.model.Employees;
import com.example.consumoapi_empleados.utils.Apis;
import com.example.consumoapi_empleados.utils.EmployeeServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class detail extends AppCompatActivity {
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        if(extras.getString("option").equals("register")){
            Fragment register = new register_employee();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_content,register).commit();

        }else{
            Fragment loading = new loading();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_content,loading).commit();
            EmployeeServices employeeServices = Apis.getEmployeeService();
            Call<Employees> call = employeeServices.getEmployee(extras.getInt("id"));
            call.enqueue(new Callback<Employees>() {
                @Override
                public void onResponse(Call<Employees> call, Response<Employees> response) {
                    if(response.isSuccessful()){
                        Fragment detailform = new detail_frg(response.body());
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_content,detailform).commit();
                    }
                }

                @Override
                public void onFailure(Call<Employees> call, Throwable t) {
                    Log.e("Error",t.getCause().getMessage());

                }
            });
        }


    }
}