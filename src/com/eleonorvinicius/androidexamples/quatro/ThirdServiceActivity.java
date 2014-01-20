package com.eleonorvinicius.androidexamples.quatro;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.eleonorvinicius.androidexamples.dois.ServiceSample;
import com.eleonorvinicius.framentsexample.R;

public class ThirdServiceActivity extends ListActivity {

	private ArrayAdapter<String> adapter;
	private List<String> wordList;
	private ThirdServiceSample thirdServiceSample;

	private ServiceConnection serviceConnection = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName componentName) {
			thirdServiceSample = null;
		}

		@Override
		public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
			ThirdServiceSample.MyBinder myBinder = (ThirdServiceSample.MyBinder) iBinder;
			thirdServiceSample = myBinder.getService();
			Toast.makeText(ThirdServiceActivity.this, "Connected", Toast.LENGTH_SHORT).show();
		}
	};

	public void status(View view) {
		if (this.thirdServiceSample != null) {
			Toast.makeText(this, "Number of elements" + this.thirdServiceSample.getItems().size(), Toast.LENGTH_SHORT).show();
			this.wordList.clear();
			this.wordList.addAll(this.thirdServiceSample.getItems());
			this.adapter.notifyDataSetChanged();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		Intent intent = new Intent(this, ThirdServiceSample.class);
		bindService(intent, this.serviceConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onPause() {
		super.onPause();
		unbindService(this.serviceConnection);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service);

		this.wordList = new ArrayList<String>();
		this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, this.wordList);

		setListAdapter(this.adapter);
	}

	public void start(View view) {
		/*
		 * FIXME ServiceSample ou ThirdServiceSample
		 */
		startService(new Intent(this, ServiceSample.class));
	}

	public void stop(View view) {
		/*
		 * FIXME ServiceSample ou ThirdServiceSample
		 */
		stopService(new Intent(this, ServiceSample.class));
	}
}
