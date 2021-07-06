package com.example.consumoapi_empleados.utils;

public class Apis {

    public static final String URL_001="https://employee-api-spring.herokuapp.com/employee/";

    public static EmployeeServices getEmployeeService(){

        return Client.getClient(URL_001).create(EmployeeServices.class);
    }
}
