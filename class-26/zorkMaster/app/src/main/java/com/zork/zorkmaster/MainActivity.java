package com.zork.zorkmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //1. get an UI element by id
      Button submimtBttn = MainActivity.this.findViewById(R.id.MainActivitySubmitBttn);

      //2. add an event listener
      submimtBttn.setOnClickListener(view -> {
        //3. Callback fn()
        //4. do stuff in the callback
        System.out.println("ZORK WAS HERE");
        // Why should we log?
          // track what's happening in your application
          // Helps debug! Verify success
        Log.v("", "Very Verbose");
        Log.d("", "Debug");
        Log.i("", "Information");
        Log.w("", "Warning");
        Log.e("", "Error");
        Log.wtf("", "What a terrible failure");
        TextView greeting = MainActivity.this.findViewById(R.id.MainActivityTextViewGreeting);
        greeting.setText("I AM THE ZORK");

      });

      setupBttns();
    }

    public void setupBttns(){
      Button goToOrderFormBttn = MainActivity.this.findViewById(R.id.MainActivityOrderFormBttn);
      // setting up routing logic with intents. Intents are the highway between activities
      goToOrderFormBttn.setOnClickListener(view -> {
        // set up the intent (Current context.this, class to go to Class.class)
        Intent goToOrderFormActivity = new Intent(this, OrderForm.class);
        // launch the intent
        startActivity(goToOrderFormActivity);
      });
    }

}
