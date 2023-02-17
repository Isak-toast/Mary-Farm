package com.numberONE.maryfarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.ImageView;
import android.widget.RemoteViewsService;

import com.numberONE.maryfarm.ui.home.Calendar.WidgetPickPlantAdapter;
public class MyRemoteViewsService extends RemoteViewsService {
    //필수 오버라이드 함수 : RemoteViewsFactory를 반환한다.
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetPickPlantAdapter(this.getApplicationContext());
    }
    /////////////////////////////////////////
//    public MyRemoteViewsService() {
//    }
//    @Override
//    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
}