package com.example.toseefkhan.pandog.SearchandNotification;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.toseefkhan.pandog.R;
import com.example.toseefkhan.pandog.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class SearchandNotificationActivity extends AppCompatActivity {
    private static final int ACTIVITY_NUM=3;
    private static final String TAG = "SnNotificationActivity";
    private Context mContext=SearchandNotificationActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started");
        setContentView(R.layout.activity_home);
        setupBottomNavigationViewEx();
    }

    //for setting up bottom navigation view ex
    private void setupBottomNavigationViewEx(){
        Log.d(TAG, "setupBottomNavigationViewEx: started");
        BottomNavigationViewEx bottomNavigationViewEx=(BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext,bottomNavigationViewEx);
        Menu menu=bottomNavigationViewEx.getMenu();
        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}