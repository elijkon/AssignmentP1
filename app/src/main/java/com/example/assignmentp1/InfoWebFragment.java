package com.example.assignmentp1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class InfoWebFragment extends Fragment {

    private WebView webView;
    private String BASE_URL = "https://seekingalpha.com";

    public InfoWebFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_web, container, false);

        //just finding the view
        webView = view.findViewById(R.id.web_view);
        webView.loadUrl(BASE_URL);

        return view;
    }
}