package com.example.assignmentp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements OnTickerSelectedListener {

    FragmentManager fg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }

    @Override
    public void onTickerSelected(String ticker) {

    }
}