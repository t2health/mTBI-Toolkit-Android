package org.t2health.mtbi.activity;

import org.t2health.mtbi.ActivityIntents;
import org.t2health.mtbi.Eula;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TabHost;


import org.t2health.mtbi.R;

public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Eula.show(this);
		
		this.setContentView(R.layout.main_activity);

		Resources res = this.getResources();
		TabHost tabHost = this.getTabHost();
		TabHost.TabSpec spec;
		Intent intent;

		intent = new Intent(this, WebViewActivity.class);
		intent.putExtra(WebViewActivity.EXTRA_CONTENT, getString(R.string.tbi_basics_content));
		intent.putExtra(WebViewActivity.EXTRA_TITLE, getString(R.string.home_title));
		intent.putExtra(WebViewActivity.EXTRA_BACK_BUTTON_VISIBILITY, View.GONE);
		spec = tabHost.newTabSpec("home")
				.setIndicator(
						getString(R.string.home_tab_title),
						res.getDrawable(R.drawable.home_new)
				)
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent(this, CPGActivity.class);
		intent.putExtra(CPGActivity.EXTRA_BACK_BUTTON_VISIBILITY, View.GONE);
		spec = tabHost.newTabSpec("guidelines")
		.setIndicator(
				getString(R.string.guidelines_tab_title),
				res.getDrawable(R.drawable.icon_list_bullets_new)
		)
		.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent(this, ICD9Activity.class);
		intent.putExtra(ICD9Activity.EXTRA_BACK_BUTTON_VISIBILITY, View.GONE);
		spec = tabHost.newTabSpec("coding")
		.setIndicator(
				getString(R.string.coding_tab_title),
				res.getDrawable(R.drawable.mercury)
		)
		.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent(this, SymptomManagementActivity.class);
		intent.putExtra(SymptomManagementActivity.EXTRA_BACK_BUTTON_VISIBILITY, View.GONE);
		spec = tabHost.newTabSpec("symptoms")
		.setIndicator(
				getString(R.string.symptoms_managment_tab_title),
				res.getDrawable(R.drawable.scope)
		)
		.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent(this, CognitiveRehabActivity.class);
		intent.putExtra(CognitiveRehabActivity.EXTRA_BACK_BUTTON_VISIBILITY, View.GONE);
		spec = tabHost.newTabSpec("rehabilitation")
		.setIndicator(
				getString(R.string.cognitive_rehabilitation_tab_title),
				res.getDrawable(R.drawable.chair)
		)
		.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent(this, EducationActivity.class);
		intent.putExtra(EducationActivity.EXTRA_BACK_BUTTON_VISIBILITY, View.GONE);
		spec = tabHost.newTabSpec("education")
		.setIndicator(
				getString(R.string.patient_education_tab_title),
				res.getDrawable(R.drawable.doc)
		)
		.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent(this, ToolsActivity.class);
		intent.putExtra(ToolsActivity.EXTRA_BACK_BUTTON_VISIBILITY, View.GONE);
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

}
