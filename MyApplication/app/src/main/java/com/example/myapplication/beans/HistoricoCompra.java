package com.example.myapplication.beans;

public class HistoricoCompra {
    private String nombreProducto;
    private String id;
    private String fechaCompra;
    private double precioCompra;
    private String imagePath;

    public HistoricoCompra(String nombreProducto, String id, String fechaCompra, int precioCompra) {
        this.nombreProducto = nombreProducto;
        this.id = id;
        this.fechaCompra = fechaCompra;
        this.precioCompra = precioCompra;
    }

    public HistoricoCompra() {

    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "HistoricoCompra{" +
                "nombreProducto='" + nombreProducto + '\'' +
                ", id='" + id + '\'' +
                ", fechaCompra='" + fechaCompra + '\'' +
                ", precioCompra=" + precioCompra +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }
}
