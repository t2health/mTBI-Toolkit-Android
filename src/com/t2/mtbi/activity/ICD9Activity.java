package com.t2.mtbi.activity;

import android.os.Bundle;

import com.t2.mtbi.R;

public class ICD9Activity extends XMLItemsBrowserActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.setXMLResource(R.xml.icd9_coding);
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
