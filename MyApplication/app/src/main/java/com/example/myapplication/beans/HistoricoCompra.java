package com.example.myapplication.beans;

public class HistoricoCompra {
    private String nombreProducto;
    private String nombreUsuario;
    private String fechaCompra;
    private double precioCompra;

    public HistoricoCompra(String nombreProducto, String nombreUsuario, String fechaCompra, int precioCompra) {
        this.nombreProducto = nombreProducto;
        this.nombreUsuario = nombreUsuario;
        this.fechaCompra = fechaCompra;
        this.precioCompra = precioCompra;
    }

    public HistoricoCompra() {

    }

    @Override
    public String toString() {
        return "HistoricoCompra{" +
                "nombreProducto='" + nombreProducto + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", fechaCompra='" + fechaCompra + '\'' +
                ", precioCompra=" + precioCompra +
                '}';
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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
