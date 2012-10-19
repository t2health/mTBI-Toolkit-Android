package org.t2health.mtbi.activity;

import org.t2health.lib.activity.BaseTabActivity;
import org.t2health.lib.activity.WebViewActivity;
import org.t2health.lib.activity.XMLItemsBrowserActivity;
import org.t2health.lib.util.Eula;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;


import org.t2health.mtbi.ActivityIntents;
import org.t2health.mtbi.R;

public class MainActivity extends BaseTabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Eula.show(this);
		
		this.setContentView(R.layout.main_activity);

		Resources res = this.getResources();
		TabHost tabHost = this.getTabHost();
		TabHost.TabSpec spec;
		Intent intent;

		// Main Intro text
		intent = new Intent(this, WebViewActivity.class);
		intent.putExtra(WebViewActivity.EXTRA_CONTENT, getString(R.string.tbi_basics_content));
		intent.putExtra(WebViewActivity.EXTRA_TITLE_TEXT, getString(R.string.home_title));
		intent.putExtra(WebViewActivity.EXTRA_LEFT_BUTTON_VISIBILITY, View.GONE);
		spec = tabHost.newTabSpec("home")
				.setIndicator(
						getString(R.string.home_tab_title),
						res.getDrawable(R.drawable.home_new)
				)
				.setContent(intent);
		tabHost.addTab(spec);

		// CPG text
		intent = ActivityIntents.getItemBrowserIntent(this, R.xml.cpg, null);
		spec = tabHost.newTabSpec("guidelines")
			.setIndicator(
					getString(R.string.guidelines_tab_title),
					res.getDrawable(R.drawable.icon_list_bullets_new)
			)
			.setContent(intent);
		tabHost.addTab(spec);

		
		intent = ActivityIntents.getItemBrowserIntent(this, R.xml.icd9_coding, null);
		spec = tabHost.newTabSpec("coding")
			.setIndicator(
					getString(R.string.coding_tab_title),
					res.getDrawable(R.drawable.mercury)
			)
			.setContent(intent);
		tabHost.addTab(spec);

		intent = ActivityIntents.getItemBrowserIntent(this, R.xml.symptom_managment, null);
		spec = tabHost.newTabSpec("symptoms")
			.setIndicator(
					getString(R.string.symptoms_management_tab_title),
					res.getDrawable(R.drawable.scope)
			)
			.setContent(intent);
		tabHost.addTab(spec);

		intent = ActivityIntents.getItemBrowserIntent(this, R.xml.cognitive_rehab, null);
		spec = tabHost.newTabSpec("rehabilitation")
			.setIndicator(
					getString(R.string.cognitive_rehabilitation_tab_title),
					res.getDrawable(R.drawable.chair)
			)
			.setContent(intent);
		tabHost.addTab(spec);

		intent = ActivityIntents.getItemBrowserIntent(this, R.xml.education, null);
		spec = tabHost.newTabSpec("education")
			.setIndicator(
					getString(R.string.patient_education_tab_title),
					res.getDrawable(R.drawable.doc)
			)
			.setContent(intent);
		tabHost.addTab(spec);

		intent = ActivityIntents.getItemBrowserIntent(this, R.xml.tools_items, null);
		spec = tabHost.newTabSpec("tools")
			.setIndicator(
					getString(R.string.tools_tab_title),
					res.getDrawable(R.drawable.kit)
			)
			.setContent(intent);
		tabHost.addTab(spec);


		tabHost.setCurrentTab(0);
		
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		if(sharedPref.getBoolean("isFirstLoad", true)) {
			startActivity(ActivityIntents.getAboutIntent(this));
			sharedPref.edit().putBoolean("isFirstLoad", false).commit();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.preferences:
				startActivity(ActivityIntents.getPreferenceIntent(this));
				return true;
			case R.id.about:
				startActivity(ActivityIntents.getAboutIntent(this));
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
