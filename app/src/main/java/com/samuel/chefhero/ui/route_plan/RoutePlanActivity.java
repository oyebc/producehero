package com.samuel.chefhero.ui.route_plan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.samuel.chefhero.R;
import com.samuel.chefhero.data.model.Destination;
import com.samuel.chefhero.data.model.Route;
import com.samuel.chefhero.databinding.RoutePlanActivityBinding;

import java.util.List;

public class RoutePlanActivity extends AppCompatActivity {

    private RoutePlanActivityBinding binding;
    private RoutePlanListViewModel routePlanListViewModel;
    private RoutePlanListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_route_plan);
        adapter = new RoutePlanListAdapter();
        routePlanListViewModel = ViewModelProviders.of(this).get(RoutePlanListViewModel.class);

        routePlanListViewModel.getDestinationListLiveData().observe(this, new Observer<List<Destination>>() {
            @Override
            public void onChanged(List<Destination> routes) {
            }
        });

        binding.listFeed.setLayoutManager(new LinearLayoutManager(this));
        binding.listFeed.setAdapter(adapter);
        routePlanListViewModel.fetchRoutesList();

//        binding.listFeed.setOnClickListener(new Click);
    }
}
