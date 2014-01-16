package com.eleonorvinicius.framentsexample;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RespostaFragment extends Fragment implements iAES{

	private static LinearLayout linearLayout;

	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			
			int resultado = intent.getIntExtra(RESULTADO, DEFAULT_INT_VALUE);
			
			TextView textView = (TextView) linearLayout.findViewById(R.id.textView1);

			
			
		}
	};
	
	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			ColorDrawable colorDrawable = (ColorDrawable) linearLayout.getBackground();
			if (colorDrawable == null) {
				linearLayout.setBackgroundColor(Color.BLUE);
				return;
			}
			int color = colorDrawable.getColor();
			if (color == Color.BLUE) {
				linearLayout.setBackgroundColor(Color.RED);
			} else {
				linearLayout.setBackgroundColor(Color.BLUE);
			}
		}
	};

	private BroadcastReceiver localReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			ColorDrawable colorDrawable = (ColorDrawable) linearLayout.getBackground();
			if (colorDrawable == null) {
				linearLayout.setBackgroundColor(Color.BLUE);
				return;
			}
			int color = colorDrawable.getColor();
			if (color == Color.BLUE) {
				linearLayout.setBackgroundColor(Color.RED);
			} else {
				linearLayout.setBackgroundColor(Color.BLUE);
			}
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		linearLayout = (LinearLayout) inflater.inflate(R.layout.resposta, null);
		return linearLayout;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		getActivity().registerReceiver(receiver, new IntentFilter(CHANGE_COLOR));
		LocalBroadcastManager.getInstance(activity).registerReceiver(localReceiver, new IntentFilter(LOCAL_CHANGE_COLOR));
	}

	@Override
	public void onDetach() {
		super.onDetach();
		getActivity().unregisterReceiver(receiver);
		LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(localReceiver);
	}
}