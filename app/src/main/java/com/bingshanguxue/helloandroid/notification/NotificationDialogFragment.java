package com.bingshanguxue.helloandroid.notification;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bingshanguxue.helloandroid.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by bingshanguxue on 13/07/2017.
 */

public class NotificationDialogFragment extends DialogFragment implements View.OnClickListener{

    private View rootView;
    private TextView txtView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.notification, container, false);

        txtView = rootView.findViewById(R.id.textView);
        rootView.findViewById(R.id.btnNotificationAccess).setOnClickListener(this);
        rootView.findViewById(R.id.btnCreateNotify).setOnClickListener(this);
        rootView.findViewById(R.id.btnClearNotify).setOnClickListener(this);
        rootView.findViewById(R.id.btnListNotify).setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNotificationAccess:
                Intent intent=new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                startActivity(intent);
                break;
            case R.id.btnCreateNotify:
                NotificationManager nManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
                NotificationCompat.Builder ncomp = new NotificationCompat.Builder(getContext());
                ncomp.setContentTitle("My Notification");
                ncomp.setContentText("Notification Listener Service Example");
                ncomp.setTicker("Notification Listener Service Example");
                ncomp.setSmallIcon(R.mipmap.ic_launcher);
                ncomp.setAutoCancel(true);
                nManager.notify((int)System.currentTimeMillis(),ncomp.build());
                break;
            case R.id.btnClearNotify:
                Intent i = new Intent(NLService.ACTION_NOTIFICATION);
                i.putExtra("command","clearall");
                getContext().sendBroadcast(i);
                break;
            case R.id.btnListNotify:
//                Intent i = new Intent("com.kpbird.nlsexample.NOTIFICATION_LISTENER_SERVICE_EXAMPLE");
//                i.putExtra("command","list");
//            sendBroadcast(i);
                break;
        }
    }
}
