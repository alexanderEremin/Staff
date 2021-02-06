package com.eremin.staff.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eremin.staff.R;
import com.eremin.staff.databinding.CardUserBinding;
import com.eremin.staff.interfaces.IClickRecycler;
import com.eremin.staff.mapdata.UserData;

import java.util.List;

public class AdapterListUsers extends RecyclerView.Adapter<AdapterListUsers.Holder> {
    private final List<UserData> data;
    private final IClickRecycler resultClick;
    public AdapterListUsers(List<UserData> data, IClickRecycler resultClick){
        this.data = data;
        this.resultClick = resultClick;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardUserBinding cardView = CardUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Holder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        UserData user = data.get(position);
        holder.binding.firstName.setText(user.getFirstName());
        holder.binding.lastName.setText(user.getLastName());
        holder.binding.fatherName.setText(user.getFatherName());
        holder.binding.position.setText(user.getPosition());
        holder.itemView.setOnClickListener(v -> resultClick.clickRecycler(data.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class Holder extends RecyclerView.ViewHolder{
        private final CardUserBinding binding;
        public Holder(@NonNull CardUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
