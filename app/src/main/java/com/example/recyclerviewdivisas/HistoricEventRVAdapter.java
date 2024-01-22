package com.example.recyclerviewdivisas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoricEventRVAdapter extends RecyclerView.Adapter<HistoricEventRVAdapter.MyViewHolder> {
    Context context;
    public static String result;
    ArrayList<divisasEventModel> divisasEventModel;

    private int selectedPosition = RecyclerView.NO_POSITION;

    public HistoricEventRVAdapter(Context context, ArrayList<divisasEventModel> divisasEventModel) {
        this.context = context;
        this.divisasEventModel = divisasEventModel;
    }

    public static String getResult() {
        return result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cv_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvName.setText(divisasEventModel.get(position).getDivisaName());
        holder.tvPrecio.setText(divisasEventModel.get(position).getDivisaPrecio());
        holder.tvIc.setImageDrawable(divisasEventModel.get(position).getDivisaIC());

        // Configurar el color de fondo según si está seleccionado o no
        holder.itemView.setBackgroundColor(position == selectedPosition ? Color.parseColor("#60D943") : Color.TRANSPARENT);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                handleClick(position);
                if(MainActivity.VIPSwitch.isChecked()){
                    result=
                            Double.parseDouble(holder.tvPrecio.getText().toString()) *
                                    Double.parseDouble(MainActivity.cantidad.getText().toString()) * 1.02 + "";

                }else {
                    result=
                            Double.parseDouble(holder.tvPrecio.getText().toString()) *
                                    Double.parseDouble(MainActivity.cantidad.getText().toString()) + "";
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return divisasEventModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrecio;
        ImageView tvIc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvEventName);
            tvPrecio = itemView.findViewById(R.id.tvEventPrecio);
            tvIc = itemView.findViewById(R.id.tvEventImage);
        }
    }

    private void handleClick(int position) {
        int previouslySelectedItem = selectedPosition;
        selectedPosition = position;

        // Notificar cambios solo si hay un cambio de selección
        if (previouslySelectedItem != RecyclerView.NO_POSITION && previouslySelectedItem != selectedPosition) {
            notifyItemChanged(previouslySelectedItem);
        }

        // Notificar cambios en la nueva selección
        notifyItemChanged(selectedPosition);
    }

}
