package com.eleonorvinicius.androidexamples.dois;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.eleonorvinicius.androidexamples.iAES;
import com.eleonorvinicius.androidexamples.quatro.ThirdServiceSample;

public class MyStartServiceBroadcastReceiver extends BroadcastReceiver implements iAES {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(BIND_SERVICE, "MyStartServiceBroadcastReceiver.onReceive");
		/*
		 * FIXME ThirdServiceSample?
		 */
		Intent intent2 = new Intent(context, ThirdServiceSample.class);
		context.startService(intent2);
	}
}