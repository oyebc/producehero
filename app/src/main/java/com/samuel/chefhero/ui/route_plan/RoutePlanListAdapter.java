package com.samuel.chefhero.ui.route_plan;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samuel.chefhero.data.model.Destination;
import com.samuel.chefhero.data.model.Route;
import com.samuel.chefhero.databinding.RoutePlanListBinding;

import java.util.ArrayList;
import java.util.List;

class RoutePlanListAdapter extends RecyclerView.Adapter{

    private List<Destination> destinationList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RoutePlanListBinding listBinding = RoutePlanListBinding.inflate(layoutInflater, parent, false);
        RoutePlanListViewHolder viewHolder = new RoutePlanListViewHolder(listBinding);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RoutePlanListViewHolder) holder).bindView(destinationList.get(position));
    }

    public void refreshList(List<Destination> destinations){
        destinationList.clear();
        destinationList.addAll(destinations);
    }
    @Override
    public int getItemCount() {
        return destinationList.size();
    }

    private class RoutePlanListViewHolder extends RecyclerView.ViewHolder {

        private RoutePlanListBinding routePlanListBinding;

        public RoutePlanListViewHolder(@NonNull RoutePlanListBinding routeListBinding) {
            super(routeListBinding.getRoot());
            this.routePlanListBinding = routeListBinding;
        }

        public void bindView(Destination destination){
            routePlanListBinding.routeName.setText(destination.getDestinationName());
        }
    }
}
