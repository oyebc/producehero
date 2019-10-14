package com.samuel.chefhero.ui.route_list;


import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.samuel.chefhero.data.model.Route;
import com.samuel.chefhero.data.model.User;
import com.samuel.chefhero.util.Utils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RouteListViewModel extends AndroidViewModel {

    private final Executor executor;
    private MutableLiveData<List<Route>> routesListLiveData;

    public RouteListViewModel(@NonNull Application mApplication){
        super(mApplication);
        executor = Executors.newFixedThreadPool(5);
    }

    public MutableLiveData<List<Route>> getRoutesListLiveData() {
        if(routesListLiveData == null){
            routesListLiveData = new MutableLiveData<>();
        }
        return routesListLiveData;
    }

    public void fetchRoutesList(){
        executor.execute(new FetchRoutesListTask());
    }

    private class FetchRoutesListTask implements Runnable {

        @Override
        public void run() {
            try {

                User user = Utils.getUserFromAssets(getApplication());

                getRoutesListLiveData().postValue(user.getRoutes());
            } catch (IOException e) {
                Log.e(RouteListViewModel.class.getName(), e.getMessage());
            }
        }
    }
}
