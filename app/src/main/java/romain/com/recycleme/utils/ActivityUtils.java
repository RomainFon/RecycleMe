package romain.com.recycleme.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.IOException;
import java.io.InputStream;

import romain.com.recycleme.R;

public class ActivityUtils {

    public static void launchActivity(AppCompatActivity activity, Class cls){
        launchActivity(activity, cls, true);
    }

    public static void launchActivity(AppCompatActivity activity, Intent intent){
        launchActivity(activity, intent, true);
    }

    public static void launchActivity(AppCompatActivity activity, Class cls, Boolean finish){
        Intent intent = new Intent(activity, cls);
        launchActivity(activity, intent, finish);
    }

    public static void launchActivity(AppCompatActivity activity, Intent intent, Boolean finish){
        activity.startActivity(intent);
        //activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        if(finish){
            activity.finish();
        }
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void addFragmentToActivity(AppCompatActivity activity, @NonNull Fragment fragment, int frameId, String tag) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.add(frameId, fragment, tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void addFragmentToActivity(Fragment parentFragment, @NonNull Fragment fragment, int frameId, String tag) {
        FragmentTransaction transaction = parentFragment.getChildFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.add(frameId, fragment, tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static String loadJSON(Context context, String fileName){
        String json = null;
        try {
            InputStream is = context.getResources().openRawResource(
                    context.getResources().getIdentifier(fileName,
                            "raw", context.getPackageName()));
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
