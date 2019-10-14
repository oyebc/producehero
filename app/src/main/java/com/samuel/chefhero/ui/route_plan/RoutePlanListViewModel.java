package com.samuel.chefhero.ui.route_plan;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.samuel.chefhero.data.model.Destination;
import com.samuel.chefhero.data.model.User;
import com.samuel.chefhero.ui.route_list.RouteListViewModel;
import com.samuel.chefhero.util.Utils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RoutePlanListViewModel extends AndroidViewModel  {

    private final Executor executor;
    private MutableLiveData<List<Destination>> destinationListLiveData;

    public RoutePlanListViewModel(@NonNull Application mApplication){
        super(mApplication);
        executor = Executors.newCachedThreadPool();
    }

    public MutableLiveData<List<Destination>> getDestinationListLiveData() {
        if(destinationListLiveData == null){
            destinationListLiveData = new MutableLiveData<>();
        }
        return destinationListLiveData;
    }

    public void fetchDestinationsInRoute(){
        executor.execute(new FetchDestinationsListTask());
    }

    private class FetchDestinationsListTask implements Runnable {

        @Override
        public void run() {
            try {

                User user = Utils.getUserFromAssets(getApplication());

                getDestinationListLiveData().postValue(user.getRoutes().get(0).getDestinations());
            } catch (IOException e) {
                Log.e(RoutePlanListViewModel.class.getName(), e.getMessage());
            }
        }
    }
}
