package com.example.automatedsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UnitsAdapter extends RecyclerView.Adapter<UnitsAdapter.UnitsListViewHolder> {


    private final Context context;
    private final List<UnitsList> unitList;

    public UnitsAdapter(Context context, List<UnitsList> unitList) {
        this.context = context;
        this.unitList = unitList;
    }

    @NonNull
    @Override
    public UnitsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.units, null);
        return new UnitsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UnitsListViewHolder holder, int position) {
        UnitsList unitsList = unitList.get(position);

        holder.unitcode.setText(unitsList.getUnitcode());
        holder.unitname.setText(unitsList.getUnitname());
        holder.lecturer.setText(unitsList.getLecturer());
    }

    @Override
    public int getItemCount() {
        return unitList.size();
    }

    class UnitsListViewHolder extends RecyclerView.ViewHolder {

        final TextView unitcode;
        final TextView unitname;
        final TextView lecturer;

        UnitsListViewHolder(View itemView) {
            super(itemView);

            unitcode = itemView.findViewById(R.id.unitcode);
            unitname = itemView.findViewById(R.id.unitname);
            lecturer = itemView.findViewById(R.id.lecturer);
        }
    }
}