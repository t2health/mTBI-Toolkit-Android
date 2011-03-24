package com.t2.mtbi.activity;

import android.content.Intent;
import android.os.Bundle;

import com.t2.mtbi.R;
import com.t2.mtbi.activity.qa.SumQAManagerActivity;

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
