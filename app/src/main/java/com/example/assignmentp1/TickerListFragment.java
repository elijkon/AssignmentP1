package com.example.assignmentp1;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class TickerListFragment extends Fragment {

    private ArrayList<String> tickerList;
    private ArrayAdapter<String> adapter;
    private OnTickerSelectedListener mListener;

    public TickerListFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (OnTickerSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnTickerSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //list for my ticker symbols
        tickerList = new ArrayList<>();
        tickerList.add("NEE");
        tickerList.add("AAPL");
        tickerList.add("DIS");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticker_list, container, false);

        // the listview and its adapter to display the tickers
        ListView listView = view.findViewById(R.id.ticker_list_view);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, tickerList);
        listView.setAdapter(adapter);

        // the listener for when an item is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

        return view;
    }
}