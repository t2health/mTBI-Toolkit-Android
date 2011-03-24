package com.t2.mtbi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.t2.mtbi.R;

public class ABSNavigationActivity extends ABSActivity {
	public static final String EXTRA_TITLE = "title";
	public static final String EXTRA_BACK_BUTTON_VISIBILITY = "backButtonVisibility";
	public static final String EXTRA_INFO_BUTTON_VISIBILITY = "infoButtonVisibility";
	public static final int RESULT_BACK = 349857;

	private int backButtonVisibility = View.VISIBLE;
	private int infoButtonVisibility = View.GONE;
	private NavigationItemEventListener navItemEventListener;
	private String title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.abs_navigation_activity);

		navItemEventListener = new NavigationItemEventListener();

		Intent intent = this.getIntent();
		backButtonVisibility = intent.getIntExtra(EXTRA_BACK_BUTTON_VISIBILITY, View.VISIBLE);
		infoButtonVisibility = intent.getIntExtra(EXTRA_INFO_BUTTON_VISIBILITY, View.GONE);
		title = intent.getStringExtra(EXTRA_TITLE);
	}

	@Override
	public void setContentView(int layoutResID) {
		((FrameLayout)this.findViewById(R.id.navigationContent)).addView(
				this.getLayoutInflater().inflate(layoutResID, null),
				LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT
		);
		initialize();
	}

	@Override
	public void setContentView(View view, LayoutParams params) {
		((FrameLayout)this.findViewById(R.id.navigationContent)).addView(view, params);
		initialize();
	}

	@Override
	public void setContentView(View view) {
		((FrameLayout)this.findViewById(R.id.navigationContent)).addView(
				view,
				LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT
		);
		initialize();
	}

	@Override
	public void setTitle(CharSequence title) {
		if(title != null) {
			this.title = title.toString();
		} else {
			this.title = null;
		}
		((TextView)this.findViewById(R.id.navigationTitle)).setText(title);
	}

	@Override
	public void setTitle(int titleId) {
		this.title = getString(titleId);
		((TextView)this.findViewById(R.id.navigationTitle)).setText(titleId);
	}

	@Override
	public void setTitleColor(int textColor) {
		((TextView)this.findViewById(R.id.navigationTitle)).setTextColor(textColor);
	}

	private void initialize() {
		this.setBackButtonVisibility(backButtonVisibility);
		this.setInfoButtonVisibility(infoButtonVisibility);

		this.findViewById(R.id.navigationBackButton).setOnClickListener(navItemEventListener);
		this.findViewById(R.id.navigationInfoButton).setOnClickListener(navItemEventListener);

		this.setTitle(this.title);
	}

	protected void setBackButtonVisibility(int v) {
		this.backButtonVisibility = v;
		this.findViewById(R.id.navigationBackButton).setVisibility(this.backButtonVisibility);
	}

	protected void setInfoButtonVisibility(int v) {
		this.infoButtonVisibility = v;
		this.findViewById(R.id.navigationInfoButton).setVisibility(this.infoButtonVisibility);
	}

	protected void onBackButtonPressed() {
		this.setResult(RESULT_BACK);
		this.finish();
	}

	protected void onInfoButtonPressed() {

	}

	private class NavigationItemEventListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			switch(arg0.getId()) {
			case R.id.navigationBackButton:
				onBackButtonPressed();
				break;
			case R.id.navigationInfoButton:
				onInfoButtonPressed();
				break;
			}
		}
	}
}
