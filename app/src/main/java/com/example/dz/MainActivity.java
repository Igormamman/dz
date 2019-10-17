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
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            if(savedInstanceState == null) {
                transaction.add(R.id.mainFrag, mainFragment);
                transaction.commit();
            }
    }

    @Override
    public void Change(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        first_frag Main=(first_frag)fragmentManager.findFragmentById(R.id.mainFrag);
        transaction.addToBackStack(null);
        if (Main!=null)
        transaction.remove(Main);
        transaction.replace(R.id.secondFrag, second_frag.newInstance(position));
        transaction.commit();
    }
}