package com.samuel.chefhero.ui.route_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.samuel.chefhero.databinding.RouteListActivityBinding;

import android.os.Bundle;

import com.samuel.chefhero.R;
import com.samuel.chefhero.data.model.Route;

import java.util.List;

public class RouteListActivity extends AppCompatActivity {

    private RouteListActivityBinding binding;
    private RouteListViewModel routeListViewModel;
    private RouteListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_route_list);
        adapter = new RouteListAdapter();
        routeListViewModel = ViewModelProviders.of(this).get(RouteListViewModel.class);

        routeListViewModel.getRoutesListLiveData().observe(this, new Observer<List<Route>>() {
            @Override
            public void onChanged(List<Route> routes) {
                adapter.refreshRoutesList(routes);
            }
        });

        binding.listFeed.setLayoutManager(new LinearLayoutManager(this));
        binding.listFeed.setAdapter(adapter);
        routeListViewModel.fetchRoutesList();
    }
}
