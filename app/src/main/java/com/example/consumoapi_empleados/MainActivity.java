package com.example.consumoapi_empleados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.consumoapi_empleados.adapter.ListAdapterEmployees;
import com.example.consumoapi_empleados.model.Employees;
import com.example.consumoapi_empleados.utils.Apis;
import com.example.consumoapi_empleados.utils.Client;
import com.example.consumoapi_empleados.utils.EmployeeServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.Settings.System.getInt;
import static android.provider.Settings.System.putInt;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "API EMPLOYEE";
    private EmployeeServices employeeServices;
    private List<Employees> listEmployees = new ArrayList<>();
    private RecyclerView recyclerView;
    Context context = this;
    private loading loading = new loading();
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvEmployees);
       getSupportFragmentManager().beginTransaction().add(R.id.fragment_content_main,loading).commit();
        getlistEmployees();
    }

    public void getlistEmployees(){
        employeeServices = Apis.getEmployeeService();
        Call<List<Employees>> call = employeeServices.listEmployees();
        call.enqueue(new Callback<List<Employees>>() {
            @Override
            public void onResponse(Call<List<Employees>> call, Response<List<Employees>> response) {
                if(response.isSuccessful()){

                    listEmployees = response.body();
                    ListAdapterEmployees listAdapterEmployees = new ListAdapterEmployees(context, listEmployees);
                    recyclerView.setAdapter(listAdapterEmployees);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                }else{
                    Log.d(TAG, "La respuesta no fue exitosa");
                }
            }

            @Override
            public void onFailure(Call<List<Employees>> call, Throwable t) {

                Log.e("Error",t.getCause().getMessage());
            }
        });
    }
}