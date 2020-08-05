package com.aliendroid.covid17.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aliendroid.model.DataGlobal;
import com.aliendroid.covid17.R;

import java.util.List;

public class GlobalAdapter extends RecyclerView.Adapter {

    List<DataGlobal> webLists;
    public Context context;

    public GlobalAdapter(List<DataGlobal> webLists, Context context) {
        this.webLists = webLists;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView namaprov;
        public TextView positif;
        public TextView sembuh;
        public TextView meninggal;
        public TextView konfirmasi;

        public ViewHolder(View itemView) {
            super(itemView);
            namaprov = (TextView) itemView.findViewById(R.id.txtprov);
            positif = (TextView) itemView.findViewById(R.id.txtPositif);
            sembuh = (TextView) itemView.findViewById(R.id.txtsembuh32);
            meninggal = (TextView) itemView.findViewById(R.id.txtmeninggal2);
            konfirmasi = itemView.findViewById(R.id.txtconfirm);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listglobal, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder,  final int position) {

        if (holder instanceof ViewHolder) {
            final DataGlobal webList = webLists.get(position);

            ((ViewHolder)holder).namaprov.setText(webList.getCountry_Region());
            ((ViewHolder)holder).sembuh.setText(webList.getRecovered());
            ((ViewHolder)holder).meninggal.setText(webList.getDeaths());
            ((ViewHolder)holder).positif.setText(webList.getActive());
            ((ViewHolder)holder).konfirmasi.setText(", Confirmed : "+webList.getConfirmed());
        }
    }

    @Override
    public int getItemCount() {
        return webLists.size();
    }
}