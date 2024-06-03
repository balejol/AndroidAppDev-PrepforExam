package com.example.egzaminui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.Date;
import java.util.Locale;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ThirdActivity extends AppCompatActivity {

    private TextView currentMonthTextView;
    private CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatForMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        currentMonthTextView = (TextView) findViewById(R.id.current_month_textView);
        compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);

        LocalDateTime dateTime = LocalDateTime.now().plusDays(1);
        long timeInMilliSeconds = dateTime.atOffset(ZoneOffset.of("+03:00")).toInstant().toEpochMilli();

        Event ev1 = new Event(Color.GREEN, 1537747200000L, "Some data");
        compactCalendarView.addEvent(ev1);

        dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClick) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Day was clicked: " + dateClick,
                        Toast.LENGTH_SHORT);
                toast.show();
            }
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth){
                //Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
                currentMonthTextView.setText(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });
    }
}