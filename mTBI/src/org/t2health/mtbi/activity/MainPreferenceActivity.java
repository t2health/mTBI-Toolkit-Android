package org.t2health.mtbi.activity;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;


import org.t2health.lib.SharedPref;
import org.t2health.lib.preference.BasePreferenceNavigationActivity;
import org.t2health.mtbi.R;

public class MainPreferenceActivity extends BasePreferenceNavigationActivity implements OnPreferenceChangeListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.addPreferencesFromResource(R.xml.main_preference_activity);
		this.findPreference("send_anon_data").setDefaultValue(SharedPref.Analytics.isEnabled(this));
		this.findPreference("send_anon_data").setOnPreferenceChangeListener(this);
		
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		if(preference.getKey().equals("send_anon_data")) {
			SharedPref.Analytics.setIsEnabled(this, (Boolean)newValue);
			return true;
		}
		
		return false;
	}

}
