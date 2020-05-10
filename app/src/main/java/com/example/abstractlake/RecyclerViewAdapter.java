package com.example.abstractlake;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{


    private List<String> mData;
    private LayoutInflater mInflater;
    private RecyclerViewAdapter recyclerAdapter = this;

    //Data is passed into the constructor
    RecyclerViewAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }


    //Inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }


    //Binds the data to the TextView in each row
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String sensorDataText = mData.get(position).toString();

        holder.myTextView.setText(sensorDataText);

    }


    //Total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    //Stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Get each TextView for displaying the data in it
            myTextView = itemView.findViewById(R.id.textSensorsAvailable);
        }

    }


}
