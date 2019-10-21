package com.example.dz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements first_frag.ChangeFragment            {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout. activity_main);
        int SIZE = 100;
        first_frag mainFragment=first_frag.newInstance(SIZE);
        if(savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.Frag, mainFragment);
            transaction.commit();
        }
    }

    @Override
    public void Change(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.Frag, second_frag.newInstance(position)).addToBackStack(null);
        transaction.commit();
    }
}