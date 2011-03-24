package com.t2.mtbi.activity;

import com.t2.mtbi.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MainPreferenceActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.addPreferencesFromResource(R.xml.main_preference_activity);
	}

}
