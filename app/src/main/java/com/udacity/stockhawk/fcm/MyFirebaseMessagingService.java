package com.udacity.stockhawk.fcm;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.udacity.stockhawk.ui.MainActivity;
import com.udacity.stockhawk.util.NotificationUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by MarwaTalaat on 3/11/2017.
 */


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
            try {

                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                sendPushNotification(json);

            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
    }


    private void sendPushNotification(JSONObject json) {
        Log.i("Notification JSON " ,json.toString());
        try {
            //getting the json data
           JSONObject data = json.getJSONObject("data");

            //parsing json data
            String title = data.getString("title");
            String message = data.getString("message");


            //creating MyNotificationManager object
            NotificationUtil mNotificationManager = new NotificationUtil(getApplicationContext());

            //creating an intent for the notification
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);


                //displaying small notification
                mNotificationManager.showSmallNotification(title,message,intent);

        } catch (JSONException e) {
           Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
       }
    }


}