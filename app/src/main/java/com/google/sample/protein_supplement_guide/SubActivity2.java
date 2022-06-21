package com.google.sample.protein_supplement_guide;


import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;

public class SubActivity2 extends AppCompatActivity {
    private TextView tv_sub;
    private AlarmManager alarmManager;
    private GregorianCalendar mCalender;

    private NotificationManager notificationManager;
    NotificationCompat.Builder builder;

    private TextView textView_Date;
    private TimePickerDialog.OnTimeSetListener callbackMethod;
    Button btn_alarm_set,reset_time;
    TextView alarm_content;
    EditText weight_val,height_val;
    int hour_val, minute_val,spoon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        mCalender = new GregorianCalendar();
        this.InitializeView();
        this.InitializeListener();
        OnClickHandler();
        reset_time = findViewById(R.id.button_time_set);
        btn_alarm_set = findViewById(R.id.btn_alarm_set);
        alarm_content = findViewById(R.id.textView_how);
        height_val = findViewById(R.id.editHeight);
        weight_val = findViewById(R.id.editWeight);
        reset_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickHandler();
            }
        });
        height_val.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                setAlarmContent();
                return false;
            }
        });
        weight_val.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                setAlarmContent();
                return false;
            }
        });
        btn_alarm_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm(hour_val,minute_val);
            }
        });
    }
    public void setAlarmContent() {
        if (weight_val.getText().toString().length()==0 || height_val.getText().toString().length()==0) {
            return;
        }
        spoon = (int) Math.round(Float.parseFloat(weight_val.getText().toString())*0.4);
        spoon+=(int) Math.round((Integer.parseInt(height_val.getText().toString())-172)*0.1);
        alarm_content.setText("사용자의 체중과 무게를 종합한 결과 "+Integer.toString(spoon)+"g의 유청 단백질을 3시간 단위로 3회 섭취하는 것이 추천됩니다.");
    }
    public void InitializeView()
    {
        textView_Date = (TextView)findViewById(R.id.textView_date);
    }

    public void InitializeListener()
    {
        callbackMethod = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                textView_Date.setText(hourOfDay + "시" + minute + "분");
                hour_val = hourOfDay;
                minute_val= minute;
            }
        };
    }
    public void OnClickHandler()
    {
        TimePickerDialog dialog = new TimePickerDialog(this, callbackMethod, 8, 10, true);

        dialog.show();
    }
    private void setAlarm(int hourOfDay, int minute) {
        //AlarmReceiver에 값 전달
        Intent receiverIntent = new Intent(this, AlarmReceiver.class);
        receiverIntent.putExtra("spoon",spoon);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, receiverIntent, 0);
//        pendingIntent.getIntentSender();
        long now = System.currentTimeMillis();

//        String from = "2021-10-22 10:11:00"; //임의로 날짜와 시간을 지정

        //날짜 포맷을 바꿔주는 소스코드
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime = new Date(now);
//        Date datetime = null;
//        try {
//            datetime = dateFormat.parse(from);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        minute+=1
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datetime);
        calendar.add(Calendar.SECOND,10);
//        calendar.set(Calendar.MINUTE, minute);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
//        return;
//        for(int i=0; i<3; i++)
//        {
//            calendar.set(Calendar.HOUR, hourOfDay);
//            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
//            hourOfDay = (hourOfDay+3)%24;
//            if (hourOfDay<=6)
//                hourOfDay = 7;
//        }


    }
}