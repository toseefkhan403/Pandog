package com.example.toseefkhan.pandog.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.example.toseefkhan.pandog.Share.ShareActivity;
import com.example.toseefkhan.pandog.Home.HomeActivity;
import com.example.toseefkhan.pandog.Map.MapActivity;
import com.example.toseefkhan.pandog.Profile.Profile1Activity;
import com.example.toseefkhan.pandog.R;
import com.example.toseefkhan.pandog.SearchandNotification.SearchandNotificationActivity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {
    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavigationView: setting up bottomnavview");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context,BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.ic_house:                                 //ACTIVITY_NUM=0
                        Intent intent1=new Intent(context, HomeActivity.class);
                        context.startActivity(intent1);
                        break;

                    case R.id.ic_map:                                   //1
                        Intent intent2=new Intent(context, MapActivity.class);
                        context.startActivity(intent2);
                        break;

                    case R.id.ic_circle:                                      //2
                        Intent intent3=new Intent(context, ShareActivity.class);
                        context.startActivity(intent3);
                        break;

                    case R.id.ic_searchandnotification:                                       //3
                        Intent intent4=new Intent(context, SearchandNotificationActivity.class);
                        context.startActivity(intent4);
                        break;

                    case R.id.ic_android:                         //4
                        Intent intent5=new Intent(context, Profile1Activity.class);
                        context.startActivity(intent5);
                        break;
                }

                return false;
            }
        });
    }
}