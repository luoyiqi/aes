package com.eleonorvinicius.androidexamples.tres;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.eleonorvinicius.framentsexample.R;

public class SecondServiceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service);
	}

	public void start(View view) {
		startService(new Intent(this, SecondServiceSample.class));
	}

	public void stop(View view) {
		stopService(new Intent(this, SecondServiceSample.class));
	}
}
