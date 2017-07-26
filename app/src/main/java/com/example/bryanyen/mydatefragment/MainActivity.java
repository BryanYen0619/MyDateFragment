package com.example.bryanyen.mydatefragment;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bryanyen.mydatefragment.fragments.DatePickerFragment;
import com.example.bryanyen.mydatefragment.utils.Utils;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ImageButton calendarImageButton;
    private TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        setListener();
        dateTextViewInit();
    }

    private void findView() {
        dateTextView = (TextView) findViewById(R.id.datePickerTextView);
        calendarImageButton = (ImageButton) findViewById(R.id.calendarImageButton);
    }

    private void setListener() {
        calendarImageButton.setOnClickListener(calendarImageButtonOnClickListener);
    }

    private void dateTextViewInit() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        String stringOfDate = Utils.convertDateToDateString(year, month, day);
        dateTextView.setText(stringOfDate);
    }

    private ImageButton.OnClickListener calendarImageButtonOnClickListener = new ImageButton.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatePickerFragment datePickerFragment = new DatePickerFragment();

            Bundle bundle = new Bundle();
            bundle.putString("dateAsText", dateTextView.getText().toString());
            datePickerFragment.setArguments(bundle);

            datePickerFragment.setCallBack(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    if (Utils.isLegalDate(year, monthOfYear, dayOfMonth) && Utils.isInOpenDays(year, monthOfYear,
                            dayOfMonth, -1)) {
                        String stringOfDate = Utils.convertDateToDateString(year, monthOfYear, dayOfMonth);
                        dateTextView.setText(stringOfDate);

                    } else {
                        Calendar mCalendar = Calendar.getInstance();
                        int nowYear = mCalendar.get(Calendar.YEAR);
                        int nowMonth = mCalendar.get(Calendar.MONTH);
                        int nowDay = mCalendar.get(Calendar.DAY_OF_MONTH);

                        onDateSet(view, nowYear, nowMonth, nowDay);
                    }
                }
            });

            // 避免重複顯示fragment
            FragmentManager fragmentManager = getFragmentManager();
            Fragment fragment = fragmentManager.findFragmentByTag("Date Picker");
            if (fragment == null) {
                datePickerFragment.show(getFragmentManager(), "Date Picker");
            }
        }
    };
}
