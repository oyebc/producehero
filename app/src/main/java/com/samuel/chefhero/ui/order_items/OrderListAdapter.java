package com.samuel.chefhero.ui.order_items;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samuel.chefhero.data.model.OrderItem;
import com.samuel.chefhero.databinding.OrderItemViewBinding;
import com.samuel.chefhero.ui.order_items.adjustvalue.AdjustValueActivity;

import java.util.ArrayList;
import java.util.List;

class OrderListAdapter extends RecyclerView.Adapter {

    private List<OrderItem> orderItems = new ArrayList<>();


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        OrderItemViewBinding orderItemViewBinding = OrderItemViewBinding.inflate(layoutInflater, parent, false);
        return new OrderItemViewHolder(orderItemViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((OrderItemViewHolder)holder).bindView(orderItems.get(position));
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public void refreshList(List<OrderItem> orderItems) {
        this.orderItems.clear();
        this.orderItems.addAll(orderItems);
        notifyDataSetChanged();
    }

    private class OrderItemViewHolder extends RecyclerView.ViewHolder {

        private final OrderItemViewBinding binding;

        public OrderItemViewHolder(@NonNull OrderItemViewBinding orderItemViewBinding) {
            super(orderItemViewBinding.rootView);
            this.binding = orderItemViewBinding;
        }

        public void bindView(final OrderItem orderItem){
            binding.orderName.setText(orderItem.getItemName());
            binding.weight.setText(orderItem.getWeight() +"kg");
            binding.numberOfItems.setText("x"+orderItem.getQuantity());
            binding.adjustButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = binding.adjustButton.getContext();
                    Intent intent = new Intent(context, AdjustValueActivity.class);
                    context.startActivity(intent);
                }
            });

        }
    }
}
