package org.t2health.mtbi.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		WebView wv = new WebView(this);
		this.setContentView(wv);
		wv.setWebViewClient(new WebViewClient());
		wv.loadDataWithBaseURL("fake://", "<a href=\"tel:1234567890\">tel:1234567890</a>", "text/html", "utf-8", null);
	}
}
