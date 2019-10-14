package com.samuel.chefhero.ui.order_items;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.samuel.chefhero.data.model.OrderItem;
import com.samuel.chefhero.data.model.User;
import com.samuel.chefhero.util.Utils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderListViewModel extends AndroidViewModel {

    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private MutableLiveData<List<OrderItem>> orderItemLiveData;

    public OrderListViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<OrderItem>> getOrderItemLiveData() {
        if(orderItemLiveData == null){
            orderItemLiveData = new MutableLiveData<>();
        }
        return orderItemLiveData;
    }

    public void fetchOrderItemsListTask(){
        executorService.execute(new FetchOrderListTask());
    }

    private class FetchOrderListTask implements Runnable {
        @Override
        public void run() {
            try {
                User user = Utils.getUserFromAssets(getApplication());
                List<OrderItem> items = user.getRoutes().get(0).getDestinations().get(0).getOrders();

                getOrderItemLiveData().postValue(items);
            } catch (IOException e) {
                Log.e(OrderListViewModel.class.getName(), e.getMessage());
            }
        }
    }
}
