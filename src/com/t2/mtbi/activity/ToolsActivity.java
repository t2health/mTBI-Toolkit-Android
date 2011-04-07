package com.t2.mtbi.activity;

import android.os.Bundle;

import com.t2.mtbi.R;

public class ToolsActivity extends XMLItemsBrowserActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.setXMLResource(R.xml.tools_items);
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
