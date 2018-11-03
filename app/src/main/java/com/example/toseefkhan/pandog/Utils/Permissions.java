package com.example.toseefkhan.pandog.Utils;


import android.Manifest;

public class Permissions {

    public static final String[] PERMISSIONS= {
           Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
           };

    public static final String[] CAMERA_PERMISSIONS= {
            Manifest.permission.CAMERA,
    };


    public static final String[] READ_EXTERNAL_STORAGE= {
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    public static final String[] WRITE_EXTERNAL_STORAGE= {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

}