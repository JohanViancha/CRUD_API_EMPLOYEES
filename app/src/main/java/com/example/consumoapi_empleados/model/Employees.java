package com.example.consumoapi_empleados.model;

public class Employees {

    private int id;
    private String nombre;
    private String apellidos;
    private String cargo;
    private String foto;
    private String fecha_nacimiento;
    private String fecha_inicio;
    private int ani_experiencia;
    private String pais_residencia;
    private String ciudad_residencia;

    public Employees(String nombre, String apellidos, String cargo, String foto,
                     String fecha_nacimiento, String fecha_inicio, int ani_experiencia,
                     String pais_residencia, String ciudad_residencia) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.foto = foto;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_inicio = fecha_inicio;
        this.ani_experiencia = ani_experiencia;
        this.pais_residencia = pais_residencia;
        this.ciudad_residencia = ciudad_residencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
      this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public int getAni_experiencia() {
        return ani_experiencia;
    }

    public void setAni_experiencia(int ani_experiencia) {
        this.ani_experiencia = ani_experiencia;
    }

    public String getPais_residencia() {
        return pais_residencia;
    }

    public void setPais_residencia(String pais_residencia) {
        this.pais_residencia = pais_residencia;
    }

    public String getCiudad_residencia() {
        return ciudad_residencia;
    }

    public void setCiudad_residencia(String ciudad_residencia) {
        this.ciudad_residencia = ciudad_residencia;
    }
}
