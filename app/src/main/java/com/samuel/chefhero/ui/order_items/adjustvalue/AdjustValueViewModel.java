package com.samuel.chefhero.ui.order_items.adjustvalue;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.samuel.chefhero.data.model.OrderItem;
import com.samuel.chefhero.data.model.User;
import com.samuel.chefhero.util.Utils;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AdjustValueViewModel extends AndroidViewModel {

    private final Executor executor;
    private MutableLiveData<OrderItem> currentOrderLiveData;

    public AdjustValueViewModel(@NonNull Application mApplication){
        super(mApplication);
        executor = Executors.newCachedThreadPool();
    }

    public MutableLiveData<OrderItem> getCurrentOrderLiveData() {
        if(currentOrderLiveData == null){
            currentOrderLiveData = new MutableLiveData<>();
        }
        return currentOrderLiveData;
    }

    public void fetchCurrentOrderItem(){
        executor.execute(new FetchCurrentOrderItemTask());
    }

    private class FetchCurrentOrderItemTask implements Runnable {
        @Override
        public void run() {

            try {
                User user = Utils.getUserFromAssets(getApplication());
                getCurrentOrderLiveData()
                        .postValue
                                (user.getRoutes()
                                        .get(0)
                                        .getDestinations()
                                        .get(0)
                                        .getOrders()
                                        .get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
