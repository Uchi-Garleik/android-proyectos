package com.example.myapplication.listhistoricocompras.data;

import com.example.myapplication.beans.HistoricoCompra;

import java.util.ArrayList;

public class DataListHistoricoCompras {
    private String message;
    private ArrayList<HistoricoCompra> historicoComprasList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<HistoricoCompra> getHistoricoComprasList() {
        return historicoComprasList;
    }

    public void setHistoricoComprasList(ArrayList<HistoricoCompra> historicoCompraArrayList) {
        this.historicoComprasList = historicoCompraArrayList;
    }
}
