package com.example.assignmentp1;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class TickerListFragment extends Fragment {

    private TickerViewModel viewModel;
    private ListView tickerListView;

    //private OnTickerSelectedListener mListener; dont need cause of viewmodel

    public TickerListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticker_list, container, false);
        tickerListView = view.findViewById(R.id.ticker_list_view);
        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get the activity's shared ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(TickerViewModel.class);

        // Observe the list of tickers for changes
        viewModel.tickerList.observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> tickers) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(),
                        android.R.layout.simple_list_item_1, tickers);
                tickerListView.setAdapter(adapter);
            }
        });

        tickerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedTicker = (String) parent.getItemAtPosition(position);
                viewModel.setUrlFromTicker(selectedTicker);
            }
        });
    }
}