package com.samuel.chefhero.ui.route_plan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.samuel.chefhero.R;
import com.samuel.chefhero.data.model.Destination;
import com.samuel.chefhero.data.model.Route;
import com.samuel.chefhero.databinding.RoutePlanListBinding;
import com.samuel.chefhero.ui.order_items.OrderListActivity;

import java.util.ArrayList;
import java.util.List;

class RoutePlanListAdapter extends RecyclerView.Adapter{

    private List<Destination> destinationList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RoutePlanListBinding listBinding = RoutePlanListBinding.inflate(layoutInflater, parent, false);
        return new RoutePlanListViewHolder(listBinding);
}


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RoutePlanListViewHolder) holder).bindView(destinationList.get(position));
    }

    public void refreshList(List<Destination> destinations){
        destinationList.clear();
        destinationList.addAll(destinations);
        notifyDataSetChanged();
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

        @RequiresApi(api = Build.VERSION_CODES.M)
        public void bindView(final Destination destination){
            routePlanListBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(routePlanListBinding.getRoot().getContext(),OrderListActivity.class);
                    routePlanListBinding.getRoot().getContext().startActivity(intent);
                }
            });
            routePlanListBinding.destinationName.setText(destination.getDestinationName());
            routePlanListBinding.address.setText(destination.getAddress());
            Button destinationButton = routePlanListBinding.destinationButton;
            destinationButton.setVisibility(View.VISIBLE);
            final Context context = destinationButton.getContext();
            if(destination.isSigned()){
                destinationButton.setBackgroundColor(Color.GRAY);
                destinationButton.setText(context.getString(R.string.signed));
                destinationButton.setClickable(false);
            } else{
                destinationButton.setBackgroundColor(context.getColor(R.color.map_green));
                destinationButton.setText(context.getString(R.string.map));
                destinationButton.setClickable(true);
                destinationButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri gmapsIntentUri = Uri.parse("google.navigation:q="+Uri.encode(destination.getAddress())+ "&avoid=tf");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmapsIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        context.startActivity(mapIntent);
                    }
                });
            }
        }
    }


}
