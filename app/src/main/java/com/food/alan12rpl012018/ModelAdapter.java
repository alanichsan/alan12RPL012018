package com.food.alan12rpl012018;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ModelListViewHolder> {


//    private ArrayList<ModelList> dataList;
//
//    public ModelAdapter (ArrayList<ModelList> dataList) {
//        this.dataList = dataList;
//    }
private Context mContext;
    private List<ModelList> mData;

    public ModelAdapter(Context mContext, List<ModelList> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ModelListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.list_item, parent, false);
        ModelAdapter.ModelListViewHolder holder = new ModelAdapter.ModelListViewHolder(v);
        mContext = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(ModelListViewHolder holder, int position) {
        holder.txtHargasewa.setText(mData.get(position).getHargasewa());
        holder.txtMerk.setText(mData.get(position).getMerk());
        holder.txtJenis.setText(mData.get(position).getJenis());
        holder.txtCode.setText(mData.get(position).getCode());
        holder.txtWarna.setText(mData.get(position).getWarna());
        holder.profile.setImageResource(mData.get(position).getProfile());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ModelListViewHolder extends RecyclerView.ViewHolder{

        private Button btnedit, btnDelete, btncancel, btnaccept;
        private EditText txtHargasewa,txtMerk,txtJenis,txtCode,txtWarna;
        private ImageView profile;


        public ModelListViewHolder(View view) {
            super(view);
            profile = (ImageView) view.findViewById(R.id.img_profile);
            btnedit = view.findViewById(R.id.btn_edit);
            btnedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    View dlgView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_edit_master, null);
                    final Dialog dialog = new Dialog(v.getContext(), android.R.style.Theme_Material_Dialog);

                    dialog.setContentView(dlgView);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                    btncancel = dialog.findViewById(R.id.btn_delete);
                    btnaccept = dialog.findViewById(R.id.btn_edits);

                    txtHargasewa = dialog.findViewById(R.id.txthargasewa);
                    txtMerk = dialog.findViewById(R.id.txtmerk);
                    txtJenis = dialog.findViewById(R.id.txtjenis);
                    txtCode = dialog.findViewById(R.id.txtkode);
                    txtWarna = dialog.findViewById(R.id.txtwarna);

                    txtHargasewa.setText(mData.get(getAdapterPosition()).getHargasewa());
                    txtMerk.setText(mData.get(getAdapterPosition()).getMerk());
                    txtJenis.setText(mData.get(getAdapterPosition()).getJenis());
                    txtCode.setText(mData.get(getAdapterPosition()).getCode());
                    txtWarna.setText(mData.get(getAdapterPosition()).getWarna());

                    btncancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    btnaccept.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String hargasewa = txtHargasewa.getText().toString();
                            String merk = txtMerk.getText().toString();
                            String jenis = txtJenis.getText().toString();
                            String code = txtCode.getText().toString();
                            String warna = txtWarna.getText().toString();
                            String id = mData.get(getAdapterPosition()).getId();
                            if (mContext instanceof MainActivity) {
                                ((MainActivity)mContext).editData(id,hargasewa, merk, jenis, code, warna);
                            }
                        }
                    });
                }
            });

            btnDelete = view.findViewById(R.id.btn_delete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View dlgView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_delete_customer, null);
                    final Dialog dialog = new Dialog(v.getContext(), android.R.style.Theme_Material_Dialog);

                    dialog.setContentView(dlgView);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();

                    btnaccept = dialog.findViewById(R.id.btn_yes);
                    btncancel = dialog.findViewById(R.id.btn_no);

                    btnaccept.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String u_id = mData.get(getAdapterPosition()).getId();
                            if (mContext instanceof MainActivity) {
                                ((MainActivity)mContext).deleteData(u_id);
                            }
                        }
                    });

                    btncancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }
            });

        }
    }
}