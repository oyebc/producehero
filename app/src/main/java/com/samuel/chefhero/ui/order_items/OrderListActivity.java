package com.samuel.chefhero.ui.order_items;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.samuel.chefhero.R;
import com.samuel.chefhero.data.model.OrderItem;
import com.samuel.chefhero.databinding.OrderListActivityBinding;
import com.samuel.chefhero.ui.order_items.sign.SignOrderActivity;

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
        adapter = new OrderListAdapter();
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel.getOrderItemLiveData().observe(this, new Observer<List<OrderItem>>() {
            @Override
            public void onChanged(List<OrderItem> orderItems) {
                adapter.refreshList(orderItems);
            }
        });
        binding.content.listFeed.setLayoutManager(new LinearLayoutManager(this));
        binding.content.listFeed.setAdapter(adapter);

        viewModel.fetchOrderItemsListTask();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        MenuItem menuItem = menu.getItem(0);
        menuItem.setTitle(this.getString(R.string.sign));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_item:
                Intent intent = new Intent(this, SignOrderActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
