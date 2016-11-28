package com.bit2016.datepickerdialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_BEGIN_DATE = 0;
    private static final int REQUEST_CODE_END_DATE = 1;
    private static final int REQUEST_CODE_BEGIN_TIME = 2;
    private static final int REQUEST_CODE_END_TIME = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.textView_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DatePickerActivity.class);
                startActivityForResult(intent, REQUEST_CODE_BEGIN_DATE);
            }
        });

        findViewById(R.id.textView_end).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DatePickerActivity.class);
                startActivityForResult(intent, REQUEST_CODE_END_DATE);
            }
        });

//        findViewById(R.id.)
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        System.out.println(requestCode + ":" + resultCode);

        if (resultCode != Activity.RESULT_OK) {
            Log.d("MainActivity", "resultCode:" + resultCode);
            return;
        }
        if (requestCode == REQUEST_CODE_BEGIN_DATE) {
            int year = intent.getIntExtra("year", -1);
            int month = intent.getIntExtra("monthOfYear", -1);
            int date = intent.getIntExtra("dayOfMonth", -1);

            TextView textView = (TextView)findViewById(R.id.textView_start);
            textView.setText(year + "-" + month + "-" + date);
        } else if (requestCode == REQUEST_CODE_END_DATE) {
            int year = intent.getIntExtra("year", -1);
            int month = intent.getIntExtra("monthOfYear", -1);
            int date = intent.getIntExtra("dayOfMonth", -1);

            TextView textView = (TextView)findViewById(R.id.textView_end);
            textView.setText(year + "-" + month + "-" + date);
        }
    }

    public void dialogDatePicker(View view) {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.d("----------->", year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                ((EditText)findViewById(R.id.editText)).setText(date);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)); // 11월은 10

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.d("dialogDatePicker", "onCancel() called");
            }
        });
        dialog.show();
    };

    public void dialogTimePicker(View view) {
        Calendar calendar = Calendar.getInstance();

        Format format = new SimpleDateFormat("HH:mm");
        System.out.println(format.format(calendar.get(Calendar.HOUR_OF_DAY)));

        TimePickerDialog dialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":" + minute;
                ((EditText)findViewById(R.id.editText2)).setText(time);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.d("dialogTimePicker", "onCancel() called");
            }
        });
        dialog.show();
    };
}
