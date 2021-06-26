package com.example.consumoapi_empleados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.consumoapi_empleados.adapter.ListAdapterEmployees;
import com.example.consumoapi_empleados.model.Employees;
import com.example.consumoapi_empleados.utils.EmployeeServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    final String TAG = "API EMPLOYEE";
    EmployeeServices employeeServices;
    List<Employees> listEmployees = new ArrayList<>();
    private RecyclerView recyclerView;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvEmployees);
        getlistEmployees();
    }

    public void getlistEmployees(){
        Call<List<Employees>> call = employeeServices.getEmployees();

        call.enqueue(new Callback<List<Employees>>() {
            @Override
            public void onResponse(Call<List<Employees>> call, Response<List<Employees>> response) {
                if(response.isSuccessful()){
                    listEmployees = response.body();
                    ListAdapterEmployees listAdapterEmployees = new ListAdapterEmployees(context, listEmployees);
                    recyclerView.setAdapter(listAdapterEmployees);
                    recyclerView.setHasFixedSize(true);
                    GridLayoutManager layoutManager = new GridLayoutManager(context,3);
                    recyclerView.setLayoutManager(layoutManager);
                }else{
                    Log.d(TAG, "La respuesta no fue exitosa");
                }
            }

            @Override
            public void onFailure(Call<List<Employees>> call, Throwable t) {

                Log.e("Error",t.getMessage().toString());
            }
        });
    }
}