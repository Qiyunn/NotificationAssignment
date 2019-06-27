package com.example.notificationassignment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button simpleNotifictonButton,buttonNotificationButton,imageNotificatonButton,drawTextNotificationButton,customNotificationButton;

    private String CHANNEL_ID="personal notification";
    private int NOTIFICATION_ID=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleNotifictonButton=findViewById(R.id.simple_notification_button);
        buttonNotificationButton=findViewById(R.id.button_notification_button);
        imageNotificatonButton=findViewById(R.id.image_notification_button);
        drawTextNotificationButton=findViewById(R.id.draw_text_notification_button);
        customNotificationButton=findViewById(R.id.custom_notification_button);

        simpleNotifictonButton.setOnClickListener(this);
        buttonNotificationButton.setOnClickListener(this);
        imageNotificatonButton.setOnClickListener(this);
        drawTextNotificationButton.setOnClickListener(this);
        customNotificationButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        createNotificationChannel();
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,CHANNEL_ID);
        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(this);

        switch (v.getId()){
            case R.id.simple_notification_button:
                builder.setSmallIcon(R.drawable.ic_message_black_24dp);
                builder.setContentTitle("Title");
                builder.setContentText("this is personal notification");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

                managerCompat.notify(NOTIFICATION_ID,builder.build());
                break;
            case R.id.button_notification_button:
                builder.setSmallIcon(R.drawable.ic_message_black_24dp);
                builder.setContentTitle("Title");
                builder.setContentText("this is personal notification");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

                Intent intent=new Intent(this,OpenActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

                builder.addAction(R.drawable.ic_message_black_24dp,"open",pendingIntent);

                managerCompat.notify(NOTIFICATION_ID,builder.build());

                break;
            case R.id.image_notification_button:
                builder.setSmallIcon(R.drawable.ic_message_black_24dp);
                builder.setContentTitle("Title");
                builder.setContentText("this is personal notification");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

                Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.img_snow);

                builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(bitmap));

                managerCompat.notify(NOTIFICATION_ID,builder.build());
                break;
            case R.id.draw_text_notification_button:

                Bitmap bitmap1=BitmapFactory.decodeResource(getResources(),R.drawable.img_snow);
                builder.setSmallIcon(R.drawable.ic_message_black_24dp);
                builder.setLargeIcon(bitmap1);
                builder.setContentTitle("Title");
                builder.setContentText("this is personal notification");

                builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.notification)));

                managerCompat.notify(NOTIFICATION_ID,builder.build());
                break;
            case R.id.custom_notification_button:
                RemoteViews remoteViews=new RemoteViews(getPackageName(),R.layout.custom_notification);
                builder.setSmallIcon(R.drawable.ic_message_black_24dp);

                builder.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
                builder.setCustomContentView(remoteViews);

                managerCompat.notify(NOTIFICATION_ID,builder.build());

        }
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name="personal channel";
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,name,importance);


            NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

        }
    }
}
