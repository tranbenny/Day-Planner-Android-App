package com.example.bennytran.yelpagendabuilder.apiCalls;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class MessageService extends Service {

    public static final String LOG_TAG = "MESSAGE SERVICE";

    public MessageService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sendMessage();
        return super.onStartCommand(intent, flags, startId);
    }

    private void sendMessage() {
        Intent intent = new Intent("finished-loading");
        Log.i(LOG_TAG, "SENDING SERVICE MESSAGE");
        boolean sent = LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        Log.i(LOG_TAG, "message sent: " + sent);
    }
}
