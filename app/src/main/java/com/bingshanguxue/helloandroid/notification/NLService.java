package com.bingshanguxue.helloandroid.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

/**
 * <p>Note: User require to enable notification permission from “Settings > Security > Notification access”.</p>
 * Created by bingshanguxue on 13/07/2017.
 */

public class NLService extends NotificationListenerService {

    private String TAG = this.getClass().getSimpleName();

    private NLServiceReceiver nlservicereciver;

    public static final String ACTION_NOTIFICATION = "com.bingshanguxue.helloandroid.NOTIFICATION";

    private static final String PACKAGE_NAME_GOOGLE_MESSAGES = "com.google.android.apps.messaging";
    private static final String PACKAGE_NAME_MOBILE_QQ = "com.tencent.mobileqq";


    @Override
    public void onCreate() {
        super.onCreate();
        nlservicereciver = new NLServiceReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_NOTIFICATION);
        registerReceiver(nlservicereciver,filter);

        Log.i(TAG,"onCreate, messagePacketName:" + resolveMessagePackagename());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(nlservicereciver);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        Log.i(TAG,"**********  onNotificationPosted");
        Log.i(TAG,"ID :" + sbn.getId() + "\t" + sbn.getNotification().tickerText + "\t" + sbn.getPackageName());
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        Log.i(TAG,"********** onNOtificationRemoved");
        Log.i(TAG,"ID :" + sbn.getId() + "\t" + sbn.getNotification().tickerText +"\t" + sbn.getPackageName());
    }

    private String resolveMessagePackagename() {
        Uri uri = Uri.parse("smsto:");
        Intent infoIntent = new Intent(Intent.ACTION_SENDTO, uri);
        ResolveInfo res = getPackageManager().resolveActivity(infoIntent, PackageManager.MATCH_DEFAULT_ONLY);
        return res != null ? res.activityInfo.packageName : null;
    }

    class NLServiceReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getStringExtra("command").equals("clearall")){
                NLService.this.cancelAllNotifications();
            }
            else if(intent.getStringExtra("command").equals("list")){
                Intent i1 = new  Intent("com.kpbird.nlsexample.NOTIFICATION</em>LISTENER<em>EXAMPLE");
                i1.putExtra("notification</em>event","=====================");
                sendBroadcast(i1);
                int i=1;
                for (StatusBarNotification sbn : NLService.this.getActiveNotifications()) {
                    Intent i2 = new  Intent("com.kpbird.nlsexample.NOTIFICATION<em>LISTENER</em>EXAMPLE");
                    i2.putExtra("notification<em>event",i +" " + sbn.getPackageName() + "n");
                    sendBroadcast(i2);
                    i++;
                }
                Intent i3 = new  Intent("com.kpbird.nlsexample.NOTIFICATION</em>LISTENER<em>EXAMPLE");
                i3.putExtra("notification</em>event","===== Notification List ====");
                sendBroadcast(i3);
            }
        }
    }
}
