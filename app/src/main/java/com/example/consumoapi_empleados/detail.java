package com.example.consumoapi_empleados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.consumoapi_empleados.model.Employees;
import com.example.consumoapi_empleados.utils.Apis;
import com.example.consumoapi_empleados.utils.EmployeeServices;
import com.google.android.material.transformation.TransformationChildCard;

import java.util.List;
import java.util.concurrent.FutureTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class detail extends AppCompatActivity {
    private Fragment loading;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        loading = new loading();
        Bundle extras = getIntent().getExtras();
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