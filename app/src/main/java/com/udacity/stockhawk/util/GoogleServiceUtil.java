package com.udacity.stockhawk.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.udacity.stockhawk.R;
import com.udacity.stockhawk.ui.MainActivity;

/**
 * Created by MarwaTalaat on 3/20/2017.
 */

public class GoogleServiceUtil {
    public static boolean checkPlayServices(Context context) {
        GoogleApiAvailability gApi = GoogleApiAvailability.getInstance();
        int resultCode = gApi.isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (gApi.isUserResolvableError(resultCode)) {
                gApi.getErrorDialog(((MainActivity)context), resultCode, 0).show();
            } else {
                Toast.makeText(context, context.getResources().getString(R.string.toast_playservices_unrecoverable), Toast.LENGTH_LONG).show();
                ((MainActivity)context).finish();
            }
            return false;
        }
        return true;
    }
}