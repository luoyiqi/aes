package com.eleonorvinicius.framentsexample;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/*
 * nao consegue parar um intentservice
 * nao consegue "interagir" (para um player de musica, por exemplo)
 */
public class SecondServiceSample extends IntentService {
	
	private static final String SECOND_SERVICE_SAMPLE = "SecondServiceSample";
	private int count;
	
	public SecondServiceSample() {
		super(SECOND_SERVICE_SAMPLE);
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		Log.i(SECOND_SERVICE_SAMPLE, "run");
		while (count < 10){
			try{
				Thread.sleep(1 * 1000);
				count += 1;
				Log.i(SECOND_SERVICE_SAMPLE, "run - count: " + count);
			} catch (InterruptedException exception) {
				
			}
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(SECOND_SERVICE_SAMPLE, "onDestroy");
	}
}