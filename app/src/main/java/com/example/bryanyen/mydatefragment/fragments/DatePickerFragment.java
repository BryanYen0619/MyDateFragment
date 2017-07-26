package com.example.bryanyen.mydatefragment.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bryan.yen on 2017/7/26
 */

public class DatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private String currentDate;
    private int openDays = -1;

    public DatePickerFragment() {
    }

    public void setCallBack(DatePickerDialog.OnDateSetListener onDateSetListener) {
        this.onDateSetListener = onDateSetListener;
    }

    public void setArguments(Bundle args) {
        super.setArguments(args);
        currentDate = args.getString("dateAsText");
        openDays = args.getInt("openDays");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int year, month, day;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);
        simpleDateFormat.setLenient(false);
        Date date;
        Calendar calendar = Calendar.getInstance();
        try {
            date = simpleDateFormat.parse(currentDate);
            calendar.setTime(date);
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                onDateSetListener, year, month, day);

        Calendar nowCalendar = Calendar.getInstance();

        // 注意：如果不提前一秒的話會報錯"java.lang.IllegalArgumentException:
        // fromDate: XXX does not precede toDate: XXX"
        long minDate = nowCalendar.getTimeInMillis() - 1000L;

        DatePicker datePicker = datePickerDialog.getDatePicker();

        //注意：如果使用了setMaxDate必須關掉CalenderView,否則會報錯"FATAL EXCEPTION: main java.lang.NullPointerException"
        datePicker.setCalendarViewShown(false);
        datePicker.setMinDate(minDate);

        if (openDays > 0) {
            // 限制開放天數範圍
            nowCalendar.add(Calendar.DAY_OF_YEAR, openDays);
        } else {
            // 限制3個月範圍
            nowCalendar.add(Calendar.MONTH, 3);
        }

        if (Build.VERSION.SDK_INT == 22) {
            nowCalendar.add(Calendar.DAY_OF_YEAR, 1);        // Android 5.1無法選擇迄止日期，所以多加一天。
        }

        datePicker.setMaxDate(nowCalendar.getTimeInMillis());

        return datePickerDialog;
    }
}
