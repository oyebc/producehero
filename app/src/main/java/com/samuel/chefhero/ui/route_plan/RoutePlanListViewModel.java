package com.samuel.chefhero.ui.route_plan;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.samuel.chefhero.data.model.Destination;
import com.samuel.chefhero.data.model.Route;
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
        executor = Executors.newFixedThreadPool(5);
    }

    public MutableLiveData<List<Destination>> getDestinationListLiveData() {
        if(destinationListLiveData == null){
            destinationListLiveData = new MutableLiveData<>();
        }
        return destinationListLiveData;
    }

    public void fetchRoutesList(){
        executor.execute(new FetchRoutesListTask());
    }

    private class FetchRoutesListTask implements Runnable {

        @Override
        public void run() {
            try {
                String userText = Utils.loadJSONFromAssetsWithFileName(getApplication(), Utils.DATA_FILE_NAME);
                Gson gson = new Gson();
                User user = gson.fromJson(userText, User.class);

                getDestinationListLiveData().postValue(user.getRoutes().get(0).getDestinations());
            } catch (IOException e) {
                Log.e(RouteListViewModel.class.getName(), e.getMessage());
            }
        }
    }
}
