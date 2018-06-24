package com.example.yongbeom.tutoavr;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {
    Button btnBasicNoti;
    Button btnExtLayoutNoti;
    Button btnUpdateNoti;
    Button btnSpecNoti;
    Button btnHeadNoti;
    Button btnLodingNoti;
    HashMap<String, Integer> notifyIdMap = new HashMap();
    private int threadNumber = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        HashMap map = new HashMap();
        if (getIntent().getExtras() != null) {
            map = (HashMap) getIntent().getExtras().get("paramMap");
        }

        Log.d("paramMap", map.toString());
        Toast.makeText(this, (String) map.get("username"), Toast.LENGTH_SHORT).show();
        settingView();


    }

    protected void settingView() {
        btnBasicNoti = findViewById(R.id.btn_basic_noti);
        View.OnClickListener btnBasicNotiListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bagsicNoti();
            }
        };
        btnBasicNoti.setOnClickListener(btnBasicNotiListener);

        btnExtLayoutNoti = findViewById(R.id.btn_ext_layout_noti);
        View.OnClickListener btnExtLayoutNotiListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extLayOutNoti();
            }
        };
        btnExtLayoutNoti.setOnClickListener(btnExtLayoutNotiListener);


        btnUpdateNoti = findViewById(R.id.btn_update_noti);
        View.OnClickListener btnUpdateNotiListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNoti();
            }
        };
        btnUpdateNoti.setOnClickListener(btnUpdateNotiListener);


        btnSpecNoti = findViewById(R.id.btn_spec_noti);
        View.OnClickListener btnSpecNotiListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                specNoti();
            }
        };
        btnSpecNoti.setOnClickListener(btnSpecNotiListener);

        btnHeadNoti = findViewById(R.id.btn_head_noti);
        View.OnClickListener btnHeadNotiListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                headNoti();
            }
        };
        btnHeadNoti.setOnClickListener(btnHeadNotiListener);

        btnLodingNoti = findViewById(R.id.btn_loding_noti);
        View.OnClickListener btnLodingNotiListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lodingBarNoti();
            }
        };
        btnLodingNoti.setOnClickListener(btnLodingNotiListener);


    }

    // 기본알림
    protected void bagsicNoti() {

        NotificationCompat.Builder mBuilder = notiBuilder("기본알림", "기본알림 내용");


        noti(mBuilder, 1);
    }

    // 해드알림
    protected void headNoti() {
        NotificationCompat.Builder builder = notiBuilder("해드 알림", "해드 내용");


        builder.setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_VIBRATE);


        noti(builder, 3);// 해당 id 의 알림을 덮어 씌운다.

    }

    // 확장알림
    protected void extLayOutNoti() {
        NotificationCompat.Builder mBuilder = notiBuilder("확장알림", "확장알림 내용");

        mBuilder.setStyle(getInBoxContent());

        noti(mBuilder, 1);
    }

    // 업데이트 알림
    protected void updateNoti() {
        NotificationCompat.Builder mNotifyBuilder = notiBuilder("업데이트 알림", "업데이트 내용");

        int numMessages = 0;
        mNotifyBuilder
                .setNumber(++numMessages);

        noti(mNotifyBuilder, 0);// 해당 id 의 알림을 덮어 씌운다.
        noti(mNotifyBuilder, 1);// 해당 id 의 알림을 덮어 씌운다.

    }

    // 특수 알림
    protected void specNoti() {
        NotificationCompat.Builder builder = notiBuilder("특수 알림", "특수 내용");


        Intent resultIntent = new Intent(this, Main2Activity.class);
        builder.setContentIntent(
                getSpeckPendingIntent(resultIntent, MainActivity.class)
        )
                .setAutoCancel(true);

        noti(builder, 3);// 해당 id 의 알림을 덮어 씌운다.
    }

    // 로딩바 알림
    protected void lodingBarNoti() {
        NotificationCompat.Builder mBuilder = notiBuilder("로딩 알림", "로딩 내용");
        threadNumber++;
        //notiThread(mBuilder);
        notiNonThread(mBuilder, threadNumber);

    }

    private void notiNonThread(final NotificationCompat.Builder mBuilder, int idNum) {
        final NotificationManager mNotifyManager =
        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int id =getNotiId(idNum);
        // 포문은 순차 진행 구문으로 으로 함수 중복 후출시 종료 될때 까지 대기 상태가 된다
        for (int incr = 0; incr <= 10000; incr+=5) {
            mBuilder.setProgress(10000, incr, false);
            mNotifyManager.notify(id,mBuilder.build());
            if(incr==10000){
                mNotifyManager.cancel(id);
            }

        }

    }


    // 쓰래드를 이용해야 하는 알림의 공통함수
    private void notiThread(final NotificationCompat.Builder mBuilder) {
        final NotificationManager mNotifyManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 쓰래드로 해야 중복 알림없이 1개로 발생
        // When the loop is finished, updates the notification



       Thread t1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {

                        int id =getNotiId(Thread.currentThread().getId());

                        for (int incr = 0; incr <= 100; incr+=5) {
                            mBuilder.setProgress(100, incr, false);
                            mNotifyManager.notify(id,mBuilder.build());
                            if(incr==100){
                                mNotifyManager.cancel(id);
                            }
                            try {
                                // Sleep for 5 seconds
                                Thread.sleep(1*1000);
                                Log.d("Therad","Thread name: "+Thread.currentThread().getId());
                            } catch (InterruptedException e) {
                                Log.d("Error", "sleep failure");
                            }
                        }

                    }
                }
        );
    }


    protected NotificationCompat.Builder notiBuilder(String title,String text){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher) // 알림창에 표기 아이콘
                        .setContentTitle(title)
                        .setContentText(text)
                        .setAutoCancel(true);
        return mBuilder;
    }

    private void noti(NotificationCompat.Builder notifyBuilder, int notifyID) {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(
                notifyID,
                notifyBuilder.build());
    }

    // 펜딩 인텐트 어플리케이션이 중지 중이여도 실행 하기 위함
    private PendingIntent getkPendingIntent(Intent resultIntent, Class cls) {
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(cls);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        return resultPendingIntent;
    }

    private PendingIntent getSpeckPendingIntent(Intent resultIntent, Class cls) {
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        return notifyPendingIntent;
    }


    // 확장알림내에 표현해야 되는 박스데이터
    protected NotificationCompat.InboxStyle getInBoxContent(){
        NotificationCompat.InboxStyle inBoxStyle =
                new NotificationCompat.InboxStyle();


        String[] events = new String[6];
        inBoxStyle.setBigContentTitle("Event tracker details:");



        for (int i=0; i < events.length; i++) {
            events[i]="숫자"+i;
            inBoxStyle.addLine(events[i]);
        }



        return inBoxStyle;
    }

    // 실행중인 쓰래드의 아이디와
    protected int getNotiId(Long notifyIdLong){
        String notifyId = Long.toString(notifyIdLong);

        if(notifyIdMap.get(notifyId)==null){
            threadNumber++;
            notifyIdMap.put(notifyId,threadNumber);
        }
        return notifyIdMap.get(notifyId);
    }

    protected int getNotiId(int notifyInt){
        String notifyId = Integer.toString(notifyInt);

        if(notifyIdMap.get(notifyId)==null){
            threadNumber++;
            notifyIdMap.put(notifyId,threadNumber);
        }
        return notifyIdMap.get(notifyId);
    }

}
