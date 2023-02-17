package com.numberONE.maryfarm;

import static android.content.ContentValues.TAG;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
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
    public String userId = "123456";
//    위젯의 크기 및 옵션이 변결될 때마다 호출되는 함수
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

//        CharSequence widgetText = String(Character.toChars(0x1F331));
        // Construct the RemoteViews object
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_mary);
//        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
//        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

//    위젯이 업데이트 될 때마다 호출되는 함수
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

        // RemoteViewsService 실행 등록시키는 함수
        Intent serviceIntent = new Intent(context, MyRemoteViewsService.class);
        RemoteViews widget = new RemoteViews(context.getPackageName(), R.layout.widget_mary);
        widget.setRemoteAdapter(R.id.widget_pick_date, serviceIntent);
        //클릭이벤트

        //보내기
        appWidgetManager.updateAppWidget(appWidgetIds, widget);
        super.onUpdate(context, appWidgetManager, appWidgetIds);

    }
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