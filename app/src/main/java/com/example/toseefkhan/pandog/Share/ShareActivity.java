package com.example.toseefkhan.pandog.Share;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.toseefkhan.pandog.R;
import com.example.toseefkhan.pandog.Utils.BottomNavigationViewHelper;
import com.example.toseefkhan.pandog.Utils.Permissions;
import com.example.toseefkhan.pandog.Utils.SectionsPagerAdapter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class ShareActivity extends AppCompatActivity {

    //constants
    private static final int ACTIVITY_NUM=2;
    private static final int VERIFY_PERMISSIONS_REQUEST=2;

    private static final String TAG = "ShareActivity";
    private Context mContext=ShareActivity.this;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started");
        setContentView(R.layout.activity_share);
        //setupBottomNavigationViewEx();

        if (checkPermissionsArray(Permissions.PERMISSIONS)){
            setupViewPager();
        }else{
            verifyPermissions(Permissions.PERMISSIONS);
        }

    }

    public int getTask(){
        Log.d(TAG, "getTask: TASK: "+ getIntent().getFlags());
        return getIntent().getFlags();
    }

    /**
     * returns the current tab number
     * 0- GalleryFragment
     * 1- PhotoFragment
     * @return
     */
    public int getCurrentTabNumber(){
        return mViewPager.getCurrentItem();
    }

    /**
     * setup ViewPager for managing the tabs
     */
    private void setupViewPager(){
          SectionsPagerAdapter adapter=new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new GalleryFragment());
        adapter.addFragment(new PhotoFragment());

        mViewPager=(ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(adapter);

        TabLayout tabLayout=(TabLayout) findViewById(R.id.tabsBottom);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setText(getString(R.string.gallery));
        tabLayout.getTabAt(1).setText(getString(R.string.photo));
    }

    /**
     * verify all the permissions passed to the array
     * @param permissions
     */
    public void verifyPermissions(String[] permissions){
        Log.d(TAG, "verifyPermissions: verifying permissions.");
        ActivityCompat.requestPermissions(ShareActivity.this,permissions,VERIFY_PERMISSIONS_REQUEST);
    }

    /**
     * checks an array of permissions
     * @param permissions
     * @return
     */
    public boolean checkPermissionsArray(String [] permissions){
        Log.d(TAG, "checkPermissionsArray: checking permissions array.");

        for (int i=0;i<permissions.length;i++){
            String check=permissions[i];
            if (!checkPermissions(check)){
                return false;
            }
        }
        return true;
    }

    /**
     * check a single permission if it has been verified
     * @param permission
     * @return
     */
    public boolean checkPermissions(String permission){
        Log.d(TAG, "checkPermissions: checking permission: "+ permission);

        int permissionRequest=ActivityCompat.checkSelfPermission(ShareActivity.this,permission);

        if (permissionRequest!= PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "checkPermissions: permission was not granted for: "+ permission);
            return false;
        }
        else {
            Log.d(TAG, "checkPermissions: permission was granted for: "+ permission);
            return true;
        }
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