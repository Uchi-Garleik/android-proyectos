package com.example.myapplication.beans;

import java.util.ArrayList;
import java.util.Arrays;

public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String marca;
    private String talla;
    private String estado;
    private ArrayList<String> categoryArrayList;

    private void addCategoryToArrayList(String category){
        if (categoryArrayList!=null){
            categoryArrayList.add(category);
        }
    }

    public ArrayList<String> getCategoryArrayList() {
        return categoryArrayList;
    }

    public void setCategoryArrayList(ArrayList<String> categoryArrayList) {
        this.categoryArrayList = categoryArrayList;
    }

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
        categoryArrayList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", marca='" + marca + '\'' +
                ", talla='" + talla + '\'' +
                ", estado='" + estado + '\'' +
                ", categoryArrayList=" + categoryArrayList +
                ", precio=" + precio +
                ", moneda='" + moneda + '\'' +
                ", image=" + Arrays.toString(image) +
                ", imagePath='" + imagePath + '\'' +
                ", idUser=" + idUser +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
