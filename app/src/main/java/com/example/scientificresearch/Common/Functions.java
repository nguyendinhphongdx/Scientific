package com.example.scientificresearch.Common;

import android.content.Context;
import android.widget.Toast;

public class Functions {
    public static void ShowToast(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
