package com.moutamid.myfitnesspaladmin.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fxn.stash.Stash;
import com.moutamid.myfitnesspaladmin.DetailScreenActivity;
import com.moutamid.myfitnesspaladmin.R;
import com.moutamid.myfitnesspaladmin.models.CompModel;
import com.moutamid.myfitnesspaladmin.utili.Constants;

import java.util.ArrayList;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.RankVH> {

    Context context;
    Activity activity;
    ArrayList<CompModel> list;

    public RankAdapter(Context context, Activity activity, ArrayList<CompModel> list) {
        this.context = context;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public RankVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RankVH(LayoutInflater.from(context).inflate(R.layout.rank_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RankVH holder, int position) {
        CompModel model = list.get(holder.getAdapterPosition());
        holder.name.setText("By " + model.getName());

        holder.itemView.setOnClickListener(v -> {
            Stash.put(Constants.MODEL, model);
            context.startActivity(new Intent(context, DetailScreenActivity.class));
            activity.finish();
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RankVH extends RecyclerView.ViewHolder{
        TextView name, rank;
        public RankVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            rank = itemView.findViewById(R.id.rankPosition);
        }
    }

}
