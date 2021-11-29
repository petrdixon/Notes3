package com.geek.notes3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    Bundle b = new Bundle();
    static final String KEY_NAME = "1";
    SecondFragment secondFragment = SecondFragment.newInstance(b);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        b = getIntent().getExtras().getBundle(KEY_NAME);

        boolean isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;

        if (!isLandscape) {
            secondFragment = SecondFragment.newInstance(b);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.noteFragment, secondFragment).commit();

        } else if (isLandscape) {
            finish();
        }

    }


}