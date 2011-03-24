package com.t2.mtbi.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.t2.mtbi.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

public class SplashActivity extends ABSActivity implements OnClickListener {
	private Timer timer = new Timer();
	private Handler startHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			startNextActivity();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.splash_activity);

		this.findViewById(R.id.baseView).setOnClickListener(this);

		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				startHandler.sendEmptyMessage(0);
			}
		}, 5000);
	}

	private void startNextActivity() {
		this.startActivity(new Intent(this, MainActivity.class));
		this.finish();
	}

	@Override
	public void onClick(View arg0) {
		timer.cancel();
		startNextActivity();
	}

}
