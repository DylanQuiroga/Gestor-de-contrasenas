package com.example.gestorcontraseas;

import java.io.Serializable;

public class Datos implements Serializable {
    private String ID, tipoCuenta ,sitio ,correo ,contra ,rut ,celular ,telefonoFijo ,nombres ,apellidos ,correoSecundario ,direccion ,otro1 ,otro2 ,otro3, codigo;

    public Datos(String ID, String tipoCuenta , String sitio , String correo , String contra , String rut , String celular , String telefonoFijo , String nombres , String apellidos , String correoSecundario , String direccion , String otro1 , String otro2 , String otro3, String codigo){
        this.ID = ID;
        this.tipoCuenta = tipoCuenta;
        this.sitio = sitio;
        this.correo = correo;
        this.contra = contra;
        this.rut = rut;
        this.celular = celular;
        this.telefonoFijo = telefonoFijo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoSecundario = correoSecundario;
        this.direccion = direccion;
        this.otro1 = otro1;
        this.otro2 = otro2;
        this.otro3 = otro3;
        this.codigo = codigo;
    }

    //getters
    public String getID(){ return ID; }
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public String getSitio() {
        return sitio;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContra() {
        return contra;
    }

    public String getRut() {
        return rut;
    }

    public String getCelular() {
        return celular;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreoSecundario() {
        return correoSecundario;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getOtro1() {
        return otro1;
    }

    public String getOtro2() {
        return otro2;
    }

    public String getOtro3() {
        return otro3;
    }

    public String getCodigo(){
            return codigo;
    }
    //setters
    public void setID(String ID){ this.ID = ID; }
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCorreoSecundario(String correoSecundario) {
        this.correoSecundario = correoSecundario;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setOtro1(String otro1) {
        this.otro1 = otro1;
    }

    public void setOtro2(String otro2) {
        this.otro2 = otro2;
    }

    public void setOtro3(String otro3) {
        this.otro3 = otro3;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
