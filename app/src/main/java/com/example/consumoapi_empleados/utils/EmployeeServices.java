package com.example.consumoapi_empleados.utils;

import com.example.consumoapi_empleados.model.Employees;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeServices {
    @GET("listar/")
    Call<List<Employees>>getEmployees();
}
