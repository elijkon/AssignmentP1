package com.example.assignmentp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {


    FragmentManager fg;
    TickerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the view model
        viewModel = new ViewModelProvider(this).get(TickerViewModel.class);

        if (savedInstanceState == null) {
            fg = getSupportFragmentManager();
            FragmentTransaction trans = fg.beginTransaction();
            //frag for tickers
            TickerListFragment tickerListFragment = new TickerListFragment();
            trans.add(R.id.list_fragment_container, tickerListFragment, "TickerListFrag");

            //frag for the website
            InfoWebFragment infoWebFragment = new InfoWebFragment();
            trans.add(R.id.info_web_fragment_container, infoWebFragment, "InfoWebFrag");

            trans.commit();
        }
        // Check for SMS permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECEIVE_SMS}, 21);
        }
    }


    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (intent != null) {
            if (intent.hasExtra("INVALID_FORMAT")) {
                //toast indicating no valid watchlist entry was found
                Toast.makeText(this, "No valid watchlist entry was found", Toast.LENGTH_LONG).show();
            } else if (intent.hasExtra("INVALID_TICKER")) {
                //toast indicating that the ticker was invalid
                Toast.makeText(this, "The ticker was invalid", Toast.LENGTH_LONG).show();
            } else if (intent.hasExtra("TICKER")) {
                String ticker = intent.getStringExtra("TICKER");
                // new Ticker to the list
                viewModel.addTicker(ticker);
                // website immediately in the WebView
                viewModel.setUrlFromTicker(ticker);
            }
        }
    }




}