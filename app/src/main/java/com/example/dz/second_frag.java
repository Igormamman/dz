package com.example.dz;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class second_frag extends Fragment {
    private static final String NUMBER = "CLICKED_NUMBER";
    private int num;

    static second_frag newInstance(int n) {
        second_frag frag = new second_frag();
        Bundle bundle = new Bundle();
        bundle.putInt(NUMBER, n);
        frag.setArguments(bundle);
        return frag;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        Bundle args = getArguments();
        if(savedInstanceState != null) {
            num = savedInstanceState.getInt(NUMBER);
        }
        if(args != null) {
            num = args.getInt(NUMBER);
        }
        TextView counter = view.findViewById(R.id.Clicked);
        counter.setText(String.valueOf(num));
        int color=(num%2==0? Color.BLUE:Color.RED);
        counter.setTextColor(color);
        return view;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NUMBER, num);
    }

}
