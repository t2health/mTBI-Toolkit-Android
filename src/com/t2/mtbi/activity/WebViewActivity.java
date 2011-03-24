package com.t2.mtbi.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.t2.mtbi.R;
import com.t2.mtbi.WebViewUtil;

public class WebViewActivity extends ABSNavigationActivity {
	private static final String TAG = WebViewActivity.class.getSimpleName();

	public static final String EXTRA_TITLE = "title";
	public static final String EXTRA_CONTENT = "content";

	public static final String EXTRA_TITLE_ID = "titleId";
	public static final String EXTRA_CONTENT_ID = "contentId";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = this.getIntent();

		//int titleId = intent.getIntExtra(EXTRA_TITLE_ID, -1);
		int contentId = intent.getIntExtra(EXTRA_CONTENT_ID, -1);

		//String titleString = intent.getStringExtra(EXTRA_TITLE);
		String contentString = intent.getStringExtra(EXTRA_CONTENT);

		Log.v(TAG, "contentId:"+contentId);
		/*if(titleString == null && titleId == -1) {
			this.finish();
		}*/
		if(contentString == null && contentId == -1) {
			this.finish();
		}

		/*if(titleId != -1) {
			titleString = getString(titleId);
		}*/
		if(contentId != -1) {
			contentString = getString(contentId);
		}

		/*if(titleString == null || contentString == null) {
			this.finish();
		}*/

		//this.setTitle(titleString);
		this.setContentView(R.layout.webview_layout);
		Log.v(TAG, "webContent:"+contentString);

		WebView wv = (WebView)this.findViewById(R.id.webview);


		TextView tv = new TextView(this);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		WebViewUtil.formatWebView(this, wv, contentString, Color.WHITE);
		/*
		int dpi = metrics.densityDpi;
		float textSizePixels = tv.getTextSize();
		int webViewFontSizePoints = (int)(textSizePixels * 72 / dpi * 2.75);
		//int color = tv.getTextColors().getDefaultColor();
		int color = Color.WHITE;

		StringBuffer contentBuffer = new StringBuffer();
		contentBuffer.append("\n<style type=\"text/css\">\n");
		contentBuffer.append("\tbody{\n");
			contentBuffer.append("\t\tcolor:rgb(");
			contentBuffer.append(Color.red(color));
			contentBuffer.append(",");
			contentBuffer.append(Color.green(color));
			contentBuffer.append(",");
			contentBuffer.append(Color.blue(color));
			contentBuffer.append(");\n");
		contentBuffer.append("\t}\n");
		contentBuffer.append("</style>");
		contentBuffer.append(contentString);

//		Log.v(TAG, "pixels:"+textSizePixels);
//		Log.v(TAG, "points:"+webViewFontSizePoints);
		WebSettings settings = wv.getSettings();
		settings.setDefaultFontSize(webViewFontSizePoints);
		settings.setDefaultFixedFontSize(webViewFontSizePoints);

		wv.setBackgroundColor(Color.TRANSPARENT); // make the bg transparent
		wv.loadDataWithBaseURL("fake:/blah", contentBuffer.toString(), "text/html", "utf-8", null);
		*/
	}
}
