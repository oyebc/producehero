package com.samuel.chefhero.ui.order_items.adjustvalue;

import android.os.Bundle;
import android.widget.NumberPicker;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.samuel.chefhero.R;
import com.samuel.chefhero.data.model.OrderItem;
import com.samuel.chefhero.databinding.AdjustValueActivityBinding;

public class AdjustValueActivity extends AppCompatActivity {

    private AdjustValueActivityBinding binding;
    private AdjustValueViewModel valueViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_adjust_value);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        valueViewModel = ViewModelProviders.of(this).get(AdjustValueViewModel.class);
        valueViewModel
                .getCurrentOrderLiveData()
                .observe(this, new Observer<OrderItem>() {
                    @Override
                    public void onChanged(OrderItem orderItem) {
                        binding.content.orderName.setText
                                (orderItem.getItemName() +" (" +orderItem.getWeight() +" Kg)");
                        NumberPicker quantityPicker = binding.content.quantityPicker;
                        quantityPicker.setMaxValue(100);
                        quantityPicker.setMinValue(5);
                        quantityPicker.setValue(orderItem.getQuantity());
                    }
                });
        valueViewModel.fetchCurrentOrderItem();
    }

}
