package com.samuel.chefhero.ui.route_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.samuel.chefhero.databinding.RouteListActivityBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.samuel.chefhero.R;
import com.samuel.chefhero.data.model.Route;
import com.samuel.chefhero.ui.route_plan.RoutePlanActivity;

import java.util.List;

public class RouteListActivity extends AppCompatActivity {

    private RouteListActivityBinding binding;
    private RouteListViewModel routeListViewModel;
    private RouteListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_route_list);
        adapter = new RouteListAdapter(routeClicked);
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

    private final View.OnClickListener routeClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RouteListActivity.this, RoutePlanActivity.class);
            startActivity(intent);
        }
    };
}
