package com.example.myapplication.beans;

import java.util.ArrayList;

public class Producto {
    private String nombre;
    private String descripcion;
    private String categoria;
    private String marca;
    private String talla;
    private String estado;
    private double precio;
    private String moneda;
    private byte[] image;
    private String imagePath;
    /* TODO:
    *   private ArrayList<byte[]> arrayList;/
    *   Test if you can store multiple images and work with them using an arrraylist of arrays of bytes
    */
    private int idUser;

    public Producto() {

    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", marca='" + marca + '\'' +
                ", talla='" + talla + '\'' +
                ", estado='" + estado + '\'' +
                ", precio=" + precio +
                ", moneda='" + moneda + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", idUser=" + idUser +
                '}';
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
}
