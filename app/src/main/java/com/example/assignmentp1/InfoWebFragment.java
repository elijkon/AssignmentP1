package com.example.assignmentp1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class InfoWebFragment extends Fragment {

    private WebView webView;
    private TickerViewModel viewModel;

    public InfoWebFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_web, container, false);
        webView = view.findViewById(R.id.web_view);
        //keeps you in your app
        webView.setWebViewClient(new WebViewClient());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // the activity's shared ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(TickerViewModel.class);

        viewModel.selectedUrl.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String url) {
                webView.loadUrl(url);
            }
        });
    }
}