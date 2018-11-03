package com.example.toseefkhan.pandog.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.toseefkhan.pandog.R;
import com.example.toseefkhan.pandog.Utils.BottomNavigationViewHelper;
import com.example.toseefkhan.pandog.Utils.FirebaseMethods;
import com.example.toseefkhan.pandog.Utils.SectionStatePagerAdapter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class AccountSettingsActivity extends AppCompatActivity {

    private static final int ACTIVITY_NUM=4;
    private static final String TAG = "AccountSettingsActivity";
    private Context mContext;
    public SectionStatePagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started");
        setContentView(R.layout.activity_account_settings);
        mContext=AccountSettingsActivity.this;
        mViewPager =(ViewPager) findViewById(R.id.container);
        mRelativeLayout=(RelativeLayout) findViewById(R.id.relLayout1);
        setupSettingsList();
        setupBottomNavigationViewEx();
        setupFragments();
        getIncomingIntent();

        //setup the backarrow for navigating back to profile activity
        ImageView backArrow=(ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back to profile activity");
                finish();
            }
        });
    }

    private void setupSettingsList(){
        Log.d(TAG, "setupSettingsList: initialising Account Settings List");
        ListView listView =(ListView) findViewById(R.id.listViewActivitySettings);

        ArrayList<String> options=new ArrayList<>();
        options.add(getString(R.string.edit_profile_fragment));
        options.add(getString(R.string.sign_out));

        ArrayAdapter arrayAdapter=new ArrayAdapter(mContext,android.R.layout.simple_list_item_1,options);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: navigating to fragment#"+position);
                setViewPager(position);
            }
        });
    }

    public void setViewPager(int fragmentNumber){
        mRelativeLayout.setVisibility(View.GONE);
        Log.d(TAG, "setViewPager: navigating to fragment number"+ fragmentNumber);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(fragmentNumber);
    }

    private void getIncomingIntent(){
        Intent intent=getIntent();

        //if there is an imageUrl attached as an extra , then it was chosen from the gallery/photo fragment
        if (intent.hasExtra(getString(R.string.selected_image))){
            Log.d(TAG, "getIncomingIntent: new incoming imageUrl");
            if (intent.getStringExtra(getString(R.string.return_to_fragment))
                    .equals(getString(R.string.edit_profile_fragment))){

                //set the new profile picture
                FirebaseMethods firebaseMethods=new FirebaseMethods(AccountSettingsActivity.this);
                firebaseMethods.uploadNewPhoto(getString(R.string.profile_photo),null,0,
                        intent.getStringExtra(getString(R.string.selected_image)));
            }
        }


        if (intent.hasExtra(getString(R.string.calling_activity))){
            Log.d(TAG, "getIncomingIntent: received incoming intent from " + getString(R.string.profile_activity));
            setViewPager(pagerAdapter.getFragmentNumber(getString(R.string.edit_profile_fragment)));
        }
    }

    private void setupFragments(){
        pagerAdapter =new SectionStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new EditProfileFragment(),getString(R.string.edit_profile_fragment));      //fragment number 0
        pagerAdapter.addFragment(new SignOutFragment(),getString(R.string.sign_out));               //fragment number 1
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