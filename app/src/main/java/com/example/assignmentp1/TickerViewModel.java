package com.example.assignmentp1;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.Arrays;

public class TickerViewModel extends ViewModel {

    public final MutableLiveData<ArrayList<String>> tickerList;
    public final MutableLiveData<String> selectedUrl;

    public TickerViewModel() {
        tickerList = new MutableLiveData<>();
        ArrayList<String> defaultTickers = new ArrayList<>();
        defaultTickers.add("NEE");
        defaultTickers.add("AAPL");
        defaultTickers.add("DIS");
        tickerList.setValue(defaultTickers);

        selectedUrl = new MutableLiveData<>();
        // shows the seekingalpha.com website on startup
        selectedUrl.setValue("https://seekingalpha.com");
    }

    public void addTicker(String newTicker) {
        ArrayList<String> currentList = tickerList.getValue();
        //make sure its not empty
        if (currentList == null) {
            currentList = new ArrayList<>();
        }

        //  no more than 6 entries
        if (currentList.size() < 6) {
            currentList.add(newTicker);
        } else {
            // replaces the sixth entry
            currentList.set(5, newTicker);
        }
        tickerList.setValue(currentList);
    }

    public void setUrlFromTicker(String ticker) {

        String url = "https://seekingalpha.com/symbol/" + ticker;
        selectedUrl.setValue(url);
    }
}