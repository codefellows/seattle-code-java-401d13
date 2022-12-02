package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.zork.zorkmaster.MainActivity;
import com.zork.zorkmaster.R;

public class OrderForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);
        consumeProductExtra();
    }

    public void consumeProductExtra(){
      Intent callingIntent = getIntent();
      String productName = null;
      if(callingIntent != null){
        productName = callingIntent.getStringExtra(MainActivity.ZORK_PRODUCT_EXTRA_TAG);
      }
      TextView orderFormProductView = findViewById(R.id.OrderFormTVProduct);
      if(productName != null) {
        orderFormProductView.setText(productName);
      }
      else {
        orderFormProductView.setText("No product");
      }
    }
}
