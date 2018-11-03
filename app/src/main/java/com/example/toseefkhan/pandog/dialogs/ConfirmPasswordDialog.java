package com.example.toseefkhan.pandog.dialogs;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toseefkhan.pandog.R;

public class ConfirmPasswordDialog extends android.support.v4.app.DialogFragment{

    private static final String TAG = "ConfirmPasswordDialog";

    public interface OnConfirmPasswordListener{
        public void onConfirmPassword(String password);
    }
    OnConfirmPasswordListener mOnConfirmPasswordListener;

    //vars
    TextView mPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_confirm_password,container,false);
        Log.d(TAG, "onCreateView: started.");
        mPassword=(TextView) view.findViewById(R.id.confirm_password);

        TextView confirmDialog =(TextView) view.findViewById(R.id.dialogConfirm);
        confirmDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: captures password and confirming.");

                String password=mPassword.getText().toString();
                if (!password.equals("")){
                    mOnConfirmPasswordListener.onConfirmPassword(password);
                    getDialog().dismiss();
                }else {
                    Toast.makeText(getActivity(),"You must enter a password.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView cancelDialog =(TextView) view.findViewById(R.id.dialogCancel);
        cancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing the dialog");
                getDialog().dismiss();
            }
        });


        Log.d(TAG, "onCreateView: started.");
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mOnConfirmPasswordListener=(OnConfirmPasswordListener)getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: classcastexception "+ e.getMessage() );
        }
    }
}