package com.sunna.lgmviptask1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<CovidData> {
    private List<CovidData> cData;
    private Context mcontext;
    public CustomAdapter(@NonNull Context context, List<CovidData> cdata) {
        super(context, R.layout.testing,cdata);
        this.mcontext = context;
        this.cData = cdata;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View listviewitem = inflater.inflate(R.layout.testing, null, true);
        TextView districtName = listviewitem.findViewById(R.id.districtName);
        TextView confirmed = listviewitem.findViewById(R.id.confirmedNumber);
        TextView active = listviewitem.findViewById(R.id.activeNumber);
        TextView deceased = listviewitem.findViewById(R.id.deceasedNumber);
        TextView recovered = listviewitem.findViewById(R.id.recoveredNumber);

        districtName.setText(cData.get(position).getDistrictName());
        confirmed.setText(cData.get(position).getConfirmed());
        active.setText(cData.get(position).getActive());
        deceased.setText(cData.get(position).getDeceased());
        recovered.setText(cData.get(position).getRecovered());
        return listviewitem;

    }
}
