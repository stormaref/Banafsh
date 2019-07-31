package org.arshef.banafsh.services;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.arshef.banafsh.R;
import org.arshef.banafsh.models.DataModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements ItemMoveCallback.ItemTouchHelperContract {

    public List<DataModel> data;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mUni;
        private TextView mPos;
        private ImageButton mBtn;
        View rowView;

        public MyViewHolder(View itemView) {
            super(itemView);

            rowView = itemView;
            mTitle = itemView.findViewById(R.id.titleLabel);
            mUni = itemView.findViewById(R.id.uniLabel);
            mPos = itemView.findViewById(R.id.posLabel);
            mBtn = itemView.findViewById(R.id.removeBtn);
        }
    }

    public RecyclerViewAdapter(List<DataModel> data) {
        this.data = (List<DataModel>) ((ArrayList<DataModel>) data).clone();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTitle.setText(data.get(position).Title);
        holder.mUni.setText(data.get(position).University);
        holder.mPos.setText(String.format(".%s", String.valueOf(data.get(position).Position)));
        holder.mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.get(position).Id != 0)
                    ModelDataAdapter.checkedState.set(data.get(position).Id - 1, false);
                ModelDataAdapter.Models.remove(position);
                ModelDataAdapter.getInstance().refresh();
                data.remove(position);
                notifyItemRemoved(position);
                Correction();
                refresh();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(data, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(data, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        Correction();
        refresh();
    }

    private void refresh() {
        for (int i = 0; i < data.size(); i++) {
            notifyItemChanged(i);
        }
    }

    private void Correction() {
        for (int i = 0; i < data.size(); i++) {
            data.get(i).Position = i + 1;
        }
    }

    @Override
    public void onRowSelected(MyViewHolder myViewHolder) {
        myViewHolder.rowView.setBackgroundColor(Color.GRAY);

    }

    @Override
    public void onRowClear(MyViewHolder myViewHolder) {
        myViewHolder.rowView.setBackgroundColor(Color.WHITE);

    }
}

