package com.udacity.stockhawk.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.ui.MainActivity;

/**
 * Created by MarwaTalaat on 3/16/2017.
 */

public class StockWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context ctxt, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(ctxt, appWidgetManager, appWidgetIds);

        for (int appWidgetId : appWidgetIds) {
            Intent svcIntent = new Intent(ctxt, StockWidgetService.class);
            svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
            svcIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

            RemoteViews widget = new RemoteViews(ctxt.getPackageName(), R.layout.stock_widget);
            widget.setRemoteAdapter(R.id.listview,svcIntent);

            Intent clickIntent = new Intent(ctxt, MainActivity.class);
            PendingIntent clickPI = PendingIntent.getActivity(ctxt, 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            widget.setPendingIntentTemplate(R.id.listview, clickPI);

            appWidgetManager.updateAppWidget(appWidgetId, widget);
        }
    }

    @Override
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        super.onReceive(context, intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
                new ComponentName(context, getClass()));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.listview);
    }

}