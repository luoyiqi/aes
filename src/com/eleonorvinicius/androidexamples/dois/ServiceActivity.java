package com.eleonorvinicius.androidexamples.dois;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.eleonorvinicius.framentsexample.R;

public class ServiceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service);
	}

	public void start(View view) {
		startService(new Intent(this, ServiceSample.class));
	}

	public void stop(View view) {
		stopService(new Intent(this, ServiceSample.class));
	}
}
