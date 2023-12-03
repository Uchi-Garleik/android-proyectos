package com.example.myapplication.listhistoricocompras.presenter;

import android.content.Context;

import com.example.myapplication.beans.HistoricoCompra;
import com.example.myapplication.listhistoricocompras.ContractHistoricoCompras;
import com.example.myapplication.listhistoricocompras.model.ListHistoricoComprasModel;

import java.util.ArrayList;

public class ListHistoricoComprasPresenter implements ContractHistoricoCompras.Presenter, ContractHistoricoCompras.Model.OnListHistoricoComprasListener {

    private ContractHistoricoCompras.View view;
    private ContractHistoricoCompras.Model model;

    public ListHistoricoComprasPresenter(ContractHistoricoCompras.View view){
        this.view = view;
        model = new ListHistoricoComprasModel(this, (Context) this.view);
    }

    @Override
    public void listHistoricoCompras(HistoricoCompra historicoCompra) {
        model.listHistoricoComprasApi(historicoCompra, this);
    }

    @Override
    public void onFinished(ArrayList<HistoricoCompra> historicoCompraArrayList) {
        view.successListHistoricoCompras(historicoCompraArrayList);
    }

    @Override
    public void onFailure(String err) {

    }
}
