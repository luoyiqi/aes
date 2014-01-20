package com.eleonorvinicius.androidexamples.um;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eleonorvinicius.androidexamples.iAES;
import com.eleonorvinicius.framentsexample.R;

public class FormularioFragment extends Fragment implements iAES {

	private static LinearLayout linearLayout;

	static Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case FORMULARIO_ENVIAR_ASYNC:
				Bundle data = msg.getData();
				int int1 = data.getInt(RESULTADO);
				((TextView) linearLayout.findViewById(R.id.textView1)).setText(String.valueOf(int1));
				break;
			default:
				break;
			}
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		linearLayout = (LinearLayout) inflater.inflate(R.layout.formulario, null);
		Button enviar = (Button) linearLayout.findViewById(R.id.button1);
		enviar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread() {
					@Override
					public void run() {
						Integer k = calcular();

						Bundle bundle = new Bundle();
						bundle.putInt(RESULTADO, k);

						Message message = new Message();
						message.what = FORMULARIO_ENVIAR_ASYNC;

						message.setData(bundle);
						handler.sendMessage(message);
					}
				}.start();
			}
		});

		Button enviarAsync = (Button) linearLayout.findViewById(R.id.button2);
		enviarAsync.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				new AsyncTask<Void, Integer, Integer>() {
					@Override
					protected Integer doInBackground(Void... params) {
						publishProgress(0);
						Integer calcular = calcular();
						publishProgress(1);
						return calcular;
					}

					@Override
					protected void onPostExecute(Integer result) {
						super.onPostExecute(result);
						((TextView) linearLayout.findViewById(R.id.textView2)).setText(String.valueOf(result));
					}

					@Override
					protected void onProgressUpdate(Integer... values) {
						super.onProgressUpdate(values);
						Log.i("FormularioFragment.enviarAsync", String.valueOf(values[0]));
					}
				}.execute();
			}
		});

		Button enviarBroad = (Button) linearLayout.findViewById(R.id.button3);
		enviarBroad.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new AsyncTask<Void, Integer, Integer>() {
					@Override
					protected Integer doInBackground(Void... params) {
						publishProgress(0);
						Integer calcular = calcular();
						publishProgress(1);
						return calcular;
					}

					@Override
					protected void onPostExecute(Integer result) {
						super.onPostExecute(result);
						Intent intent = new Intent(ENVIAR_BROADCAST);
						intent.putExtra(RESULTADO, result);
						LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
					}

					@Override
					protected void onProgressUpdate(Integer... values) {
						super.onProgressUpdate(values);
						Log.i("FormularioFragment.enviarBroad", String.valueOf(values[0]));
					}
				}.execute();
			}
		});
		return linearLayout;
	}

	private Integer calcular() {
		EditText editText1 = (EditText) getView().findViewById(R.id.editText1);
		EditText editText2 = (EditText) getView().findViewById(R.id.editText2);

		String string = editText1.getText().toString();
		String string2 = editText2.getText().toString();

		Integer i = new Integer(string);
		Integer j = new Integer(string2);
		return i + j;
	}
}