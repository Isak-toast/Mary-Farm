package com.numberONE.maryfarm;

import static android.content.ContentValues.TAG;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.numberONE.maryfarm.Retrofit.Calendar.ItemModel;
import com.numberONE.maryfarm.Retrofit.Calendar.MemoModel;
import com.numberONE.maryfarm.Retrofit.Calendar.RetrofitFactory;
import com.numberONE.maryfarm.Retrofit.Calendar.RetrofitService;
import com.numberONE.maryfarm.ui.home.Calendar.CalendarPickPlantAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of App Widget functionality.
 */
public class WidgetMary extends AppWidgetProvider {
    public String userId = "2626273197";
    //    위젯의 크기 및 옵션이 변결될 때마다 호출되는 함수
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        //위젯 클릭시 앱을 띄우고 MainActivity 로 이동
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_mary);
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
//        intent.setComponent(new ComponentName(context, MainActivity.class));
//        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
//        views.setOnClickPendingIntent(R.id.appwidget_text, pi);
    }

    //    위젯이 업데이트 될 때마다 호출되는 함수
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);

            // RemoteViewsService 실행 등록시키는 함수
            Intent serviceIntent = new Intent(context, MyRemoteViewsService.class);
            RemoteViews widget = new RemoteViews(context.getPackageName(), R.layout.widget_mary);
            widget.setRemoteAdapter(R.id.widget_pick_date, serviceIntent);
            //클릭이벤트
            //Listview 클릭 이벤트를 위한 코드. -> 원리는 pendingIntent 부여 -> 하나씩 부여하기에는 부담이 되어서 각 항목의 클릭이 아닌 위젯 자체에 대한 클릭
//            Intent toastIntent = new Intent(context, WidgetMary.class);
//            toastIntent.setAction(WidgetMary.TOAST_ACTION);
//            toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
//            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));
//            PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent,
//                    PendingIntent.FLAG_UPDATE_CURRENT);
//            widget.setPendingIntentTemplate(R.id.widget_pick_date, toastPendingIntent);
            //보내기
            appWidgetManager.updateAppWidget(appWidgetIds, widget);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
    //    @Override
//    public void onReceive(Context context, Intent intent) {
//
//        AppWidgetManager mgr = AppWidgetManager.getInstance(context);
//        if (intent.getAction().equals(TOAST_ACTION)) {
//
//            Intent i = new Intent(context, MainActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(i);
//        }
//
//        super.onReceive(context, intent);
//    }
//    앱 위젯이 최초로 설치되는 순간 호출되는 함수
    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }
    //    위젯이 제거되는 순간 호출되는 함수
    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}