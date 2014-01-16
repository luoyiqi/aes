package com.eleonorvinicius.framentsexample;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MenuActivity extends ListActivity {

	private List<String> itensMenu;

	public MenuActivity() {
		this.itensMenu = new ArrayList<String>();
		this.itensMenu.add("Service");
		this.itensMenu.add("IntentService");
		this.itensMenu.add("Bind");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.itensMenu));
		sendBroadcast(new Intent(this, MyScheduleServiceBroadcastReceiver.class));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		switch (position) {
		case 0:
			startActivity(new Intent(this, ServiceActivity.class));
			break;
		case 1:
			startActivity(new Intent(this, SecondServiceActivity.class));
			break;
		case 2:
			startActivity(new Intent(this, ThirdServiceActivity.class));
			break;
		default:
			Toast.makeText(this, R.string.not_yet, Toast.LENGTH_SHORT).show();
			break;
		}
	}
}