package com.eleonorvinicius.framentsexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceSample extends Service implements Runnable {

	private static final String SERVICE_SAMPLE = "ServiceSample";
	private boolean ativo;
	private int count;

	@Override
	public IBinder onBind(Intent arg0) {
		Log.i(SERVICE_SAMPLE, "onBind");
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(SERVICE_SAMPLE, "onCreate");
		new Thread(this).start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(SERVICE_SAMPLE, "onDestroy");
		ativo = false;
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.i(SERVICE_SAMPLE, "onStart - startId: " + startId);
		ativo = true;
	}

	@Override
	public void run() {
		Log.i(SERVICE_SAMPLE, "run");
		while (ativo && count < 20) {
			try {
				Thread.sleep(1 * 1000);
				count += 1;
				Log.i(SERVICE_SAMPLE, "run - count: " + count);
			} catch (InterruptedException exception) {

			}
		}
		stopSelf();
	}
}