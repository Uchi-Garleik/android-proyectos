package com.example.myapplication.listhistoricocompras;

import com.example.myapplication.beans.HistoricoCompra;

import java.util.ArrayList;

public interface ContractHistoricoCompras {
    public interface Presenter{
        void listHistoricoCompras(HistoricoCompra historicoCompra);
    }

    public interface Model{
        void listHistoricoComprasApi(HistoricoCompra historicoCompra,
                                     ContractHistoricoCompras.Model.OnListHistoricoComprasListener onListHistoricoComprasListener);

        interface OnListHistoricoComprasListener {
            void onFinished(ArrayList<HistoricoCompra> historicoCompraArrayList);
            void onFailure(String err);
        }
    }
    public interface View{
        public void successListHistoricoCompras(ArrayList<HistoricoCompra> historicoCompraArrayList);
        void failureListHistoricoCompras(String err);
    }
}
