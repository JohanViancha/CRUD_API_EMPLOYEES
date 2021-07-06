package com.example.consumoapi_empleados;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.consumoapi_empleados.adapter.ListAdapterEmployees;
import com.example.consumoapi_empleados.model.Employees;
import com.example.consumoapi_empleados.utils.Apis;
import com.example.consumoapi_empleados.utils.EmployeeServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "API EMPLOYEE";
    private EmployeeServices employeeServices;
    private List<Employees> listEmployees = new ArrayList<>();
    private RecyclerView recyclerView;
    Context context = this;
    private loading loading = new loading();
    private TextView notdata;
    private Button registerEmployee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvEmployees);
        notdata = findViewById(R.id.notdata);
        registerEmployee = findViewById(R.id.btnRegister);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_content_main,loading).commit();
        getlistEmployees();


        registerEmployee.setOnClickListener(v -> {
            Intent intent = new Intent(context,detail.class);
            intent.putExtra("option","register");
            startActivity(intent);
        });


    }

    public void getlistEmployees(){
        employeeServices = Apis.getEmployeeService();
        Call<List<Employees>> call = employeeServices.listEmployees();
        call.enqueue(new Callback<List<Employees>>() {
            @Override
            public void onResponse(Call<List<Employees>> call, Response<List<Employees>> response) {
                if(response.isSuccessful()){
                    listEmployees = response.body();
                    getSupportFragmentManager().beginTransaction().
                            remove(getSupportFragmentManager().findFragmentById(R.id.fragment_content_main)).commit();
                    if(listEmployees.size()>0){
                        ListAdapterEmployees listAdapterEmployees = new ListAdapterEmployees(context, listEmployees);
                        recyclerView.setAdapter(listAdapterEmployees);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));


                    }else{
                        notdata.setVisibility(View.VISIBLE);
                    }

                }else{
                    Log.d(TAG, "La respuesta no fue exitosa");
                }
            }

            @Override
            public void onFailure(Call<List<Employees>> call, Throwable t) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().remove(loading);
                Log.e("Error",t.getCause().getMessage());
            }
        });
    }
}