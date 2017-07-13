package com.bingshanguxue.helloandroid.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;


/**
 * 11-07 12:43:18.078 5071-10468/? W/BroadcastQueue: Permission Denial: receiving Intent { act=android.provider.Telephony.SMS_RECEIVED flg=0x8000010 (has extras) } to com.manfenjiayuan.mixicook_vip/com.mfh.framework.core.sms.SMSBroadcastReceiver requires android.permission.RECEIVE_SMS due to sender com.android.phone (uid 1001)
 * Created by shengkun on 15/6/11.
 */
public class SMSBroadcastReceiver extends BroadcastReceiver {
    private String TAG = this.getClass().getSimpleName();

    // Get the object of SmsManager
    final SmsManager mSmsManager = SmsManager.getDefault();

    Intent mServiceIntent;
//    WeakHandler mHandler;

    @Override
    public void onReceive(Context context, Intent intent) {
        //从Intent中接受信息
        Bundle bundle = intent.getExtras();
        if (bundle == null){
            return;
        }
//        Log.d("收到短信息" + StringUtils.decodeBundle(bundle));


        Object[] pdus = (Object[]) bundle.get("pdus");
        for (Object p : pdus) {
            SmsMessage message = SmsMessage.createFromPdu((byte[]) p);
            //获取短信内容
            final String content = message.getMessageBody();
            //获取发送时间
            final Date date = new Date(message.getTimestampMillis());
            final String sender = message.getOriginatingAddress();
            Log.d(TAG, "content=" + content);
            Log.d(TAG, "date=" + date);
            Log.d(TAG, "sender=" + sender);

//            if (!RegularUtils.isMobile(sender)) {
//                return;
//            }



        }
    }

}
