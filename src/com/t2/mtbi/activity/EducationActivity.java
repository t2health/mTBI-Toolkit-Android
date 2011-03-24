package com.t2.mtbi.activity;

import android.content.Intent;
import android.os.Bundle;

import com.t2.mtbi.ActivityIntents;
import com.t2.mtbi.R;

public class EducationActivity extends XMLItemsBrowserActivity {
	private static final String TAG = EducationActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.setXMLResource(R.xml.education);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected int getHeaderLayoutResId() {
		return R.layout.list_item_1_separator;
	}

	@Override
	protected int getItemLayoutResId() {
		return R.layout.list_item_1_indent;
	}
}
