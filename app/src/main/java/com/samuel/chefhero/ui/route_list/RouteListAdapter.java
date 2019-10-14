package com.samuel.chefhero.ui.route_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samuel.chefhero.data.model.Route;
import com.samuel.chefhero.databinding.RouteListBinding;

import java.util.ArrayList;
import java.util.List;

class RouteListAdapter extends RecyclerView.Adapter {

    private List<Route> routesList = new ArrayList<>();
    private View.OnClickListener routeClicked;

    public RouteListAdapter(View.OnClickListener routeClicked) {
        this.routeClicked = routeClicked;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RouteListBinding listBinding = RouteListBinding.inflate(layoutInflater, parent, false);
        RouteListViewHolder viewHolder = new RouteListViewHolder(listBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RouteListViewHolder) holder).bindView(routesList.get(position));
    }

    public void refreshRoutesList(List<Route> routes){
        routesList.clear();
        routesList.addAll(routes);

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return routesList.size();
    }

    private class RouteListViewHolder extends RecyclerView.ViewHolder {

        private RouteListBinding routeListBinding;

        public RouteListViewHolder(@NonNull RouteListBinding routeListBinding) {
            super(routeListBinding.getRoot());
            this.routeListBinding = routeListBinding;
            this.routeListBinding.getRoot().setOnClickListener(routeClicked);
        }

        public void bindView(Route route){
            routeListBinding.routeName.setText(route.getRouteName());
        }
    }
}
