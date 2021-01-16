package mx.gob.cdmx.centroh.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import mx.gob.cdmx.centroh.R;

public class SimpleNotification {

    public void simpleNotification(Class aClass,Context context,  String channeliD, String nameChannel, String Title, String message, String details, int id, boolean progress)
    {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context, channeliD);
        Intent ii = new Intent(context, aClass);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, ii, 0);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText(message);
        bigText.setBigContentTitle(Title);
        bigText.setSummaryText(details);

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.drawable.ic_action_name);
        mBuilder.setContentTitle(Title);
        mBuilder.setContentText(message);
        if (progress){
            mBuilder.setProgress(0,0,true);
        }
        mBuilder.setPriority(android.app.Notification.PRIORITY_MAX);
        mBuilder.setSmallIcon(R.drawable.ic_action_name);
        mBuilder.setStyle(bigText);
        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channeliD,nameChannel,
                    NotificationManager.IMPORTANCE_DEFAULT);
            mNotificationManager.createNotificationChannel(channel);
        }

        mNotificationManager.notify(id, mBuilder.build());

    }

    public void cancelNotification(Context context, String channeliD, String nameChannel, int id){

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channeliD,nameChannel,
                    NotificationManager.IMPORTANCE_DEFAULT);
            mNotificationManager.createNotificationChannel(channel);
        }

        mNotificationManager.cancel(id);

    }
}
