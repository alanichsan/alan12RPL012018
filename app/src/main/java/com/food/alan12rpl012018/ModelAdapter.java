package com.food.alan12rpl012018;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ModelListViewHolder> {


    private ArrayList<ModelList> dataList;

    public ModelAdapter (ArrayList<ModelList> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ModelListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ModelListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ModelListViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtJumlah.setText(dataList.get(position).getJumlah());
        holder.txtJenis.setText(dataList.get(position).getJenis());
        holder.txtCode.setText(dataList.get(position).getCode());

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ModelListViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtJumlah, txtJenis, txtCode;

        public ModelListViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.txt_nama);
            txtJumlah = (TextView) itemView.findViewById(R.id.txt_jumlah);
            txtJenis = (TextView) itemView.findViewById(R.id.txt_jenis);
            txtCode = (TextView) itemView.findViewById(R.id.txt_code);
        }
    }
}