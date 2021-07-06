package com.example.consumoapi_empleados.utils;

import com.example.consumoapi_empleados.model.Employees;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface EmployeeServices {
    @GET("list")
    Call<List<Employees>>listEmployees();

    @GET("list/{id}")
    Call<Employees>getEmployee(@Path("id") int id);
}
