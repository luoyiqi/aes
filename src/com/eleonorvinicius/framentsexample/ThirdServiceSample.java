package com.eleonorvinicius.framentsexample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ThirdServiceSample extends Service {

	private IBinder iBinder;
	private List<String> items;

	public ThirdServiceSample() {
		this.items = new ArrayList<String>();
		this.iBinder = new MyBinder();
	}

	public List<String> getItems() {
		return this.items;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return this.iBinder;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// super.onStartCommand(intent, flags, startId);

		Random random = new Random();
		if (random.nextBoolean()) {
			this.items.add("Linux");
		}
		if (random.nextBoolean()) {
			this.items.add("Android");
		}
		if (random.nextBoolean()) {
			this.items.add("iPhone");
		}
		if (random.nextBoolean()) {
			this.items.add("Windows7");
		}
		if (this.items.size() >= 25) {
			this.items.remove(0);
		}

		return Service.START_NOT_STICKY;
	}

	public class MyBinder extends Binder {
		public ThirdServiceSample getService() {
			return ThirdServiceSample.this;
		}
	}
}