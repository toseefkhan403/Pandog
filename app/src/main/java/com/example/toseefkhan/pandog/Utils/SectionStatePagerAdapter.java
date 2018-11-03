package com.example.toseefkhan.pandog.Utils;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


    public class SectionStatePagerAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> mFragmentList=new ArrayList<>();
        private final HashMap<Fragment,Integer> mFragments=new HashMap<>();
        private final HashMap<String,Integer> mFragmentNumbers=new HashMap<>();
        private final HashMap<Integer,String> mFragmentNames=new HashMap<>();


        public SectionStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        //method for adding fragments
        public void addFragment(Fragment fragment,String fragmentName){
            mFragmentList.add(fragment);
            mFragments.put(fragment,mFragmentList.size()-1);
            mFragmentNumbers.put(fragmentName,mFragmentList.size()-1);
            mFragmentNames.put(mFragmentList.size()-1,fragmentName);
        }

        /*
         *returns the fragment with the name @param
         * name @param being @fragmentName
         */
        public Integer getFragmentNumber(String fragmentName){
            if(mFragmentNumbers.containsKey(fragmentName)){
                return mFragmentNumbers.get(fragmentName);
            }else return null;
        }

        /*
         *returns the fragment with the object Fragment @param
         * @param being @fragment
         */
        public Integer getFragmentNumber(Fragment fragment){
            if(mFragmentNumbers.containsKey(fragment)){
                return mFragmentNumbers.get(fragment);
            }else return null;
        }

        /**
         * returns the fragment name with the fragment number as the @param
         * @param fragmentNumber
         * @return
         */
        public  String getFragmentName(Integer fragmentNumber){
            if(mFragmentNames.containsKey(fragmentNumber)){
                return mFragmentNames.get(fragmentNumber);
            }else return null;
        }
    }