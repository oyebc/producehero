package com.samuel.chefhero.ui.order_items;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;

import com.samuel.chefhero.R;
import com.samuel.chefhero.data.model.OrderItem;
import com.samuel.chefhero.databinding.OrderListActivityBinding;

import java.util.List;

public class OrderListActivity extends AppCompatActivity {

    private OrderListActivityBinding binding;
    private OrderListViewModel viewModel;
    private OrderListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_list);
        viewModel = ViewModelProviders.of(this).get(OrderListViewModel.class);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel.getOrderItemLiveData().observe(this, new Observer<List<OrderItem>>() {
            @Override
            public void onChanged(List<OrderItem> orderItems) {
                adapter = new OrderListAdapter(orderItems);
            }
        });
        binding.content.listFeed.setLayoutManager(new LinearLayoutManager(this));
        binding.content.listFeed.setAdapter(adapter);

        viewModel.fetchOrderItemsListTask();
    }

}
