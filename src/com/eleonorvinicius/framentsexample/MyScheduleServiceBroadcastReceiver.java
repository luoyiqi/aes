package com.eleonorvinicius.framentsexample;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyScheduleServiceBroadcastReceiver extends BroadcastReceiver implements iAES {

	private static final long REPEAT_TIME = 10 * 1000;

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(BIND_SERVICE, "MyScheduleServiceBroadcastReceiver.onReceive");
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent2 = new Intent(context, MyStartServiceBroadcastReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent2, PendingIntent.FLAG_CANCEL_CURRENT);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, 5);
		alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), REPEAT_TIME, pendingIntent);
	}
}