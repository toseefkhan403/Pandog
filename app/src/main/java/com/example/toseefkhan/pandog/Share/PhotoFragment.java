package com.example.toseefkhan.pandog.Share;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;

import com.example.toseefkhan.pandog.R;
import com.example.toseefkhan.pandog.Utils.Permissions;

public class PhotoFragment extends Fragment {
    private static final String TAG = "PhotoFragment";

    //constants
    private static final int PHOTO_FRAGMENT_NUM=1;
    private static final int GALLERY_FRAGMENT_NUM=2;
    private static final int CAMERA_REQUEST_CODE=5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_photo,container,false);
        Log.d(TAG, "onCreateView: started");


        Button btnCamera=(Button) view.findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        if (((ShareActivity)getActivity()).getCurrentTabNumber()==PHOTO_FRAGMENT_NUM){
            if (((ShareActivity)getActivity()).checkPermissions(Permissions.CAMERA_PERMISSIONS[0])){
                Log.d(TAG, "onCreateView: starting camera");

                Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_REQUEST_CODE);
            }else {
                Intent intent=new Intent(getActivity(),ShareActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==CAMERA_REQUEST_CODE){
            Log.d(TAG, "onActivityResult: got a photo");
            Log.d(TAG, "onActivityResult: attempting to navigate to the final share screen");

            //navigate to the final share screen to publish the photo

        }
    }
}