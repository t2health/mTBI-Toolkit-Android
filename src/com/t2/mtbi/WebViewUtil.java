package com.t2.mtbi;

import android.app.Activity;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class WebViewUtil {
	public static void formatWebView(Activity c, WebView wv, String contentString, int textColor) {
		if(contentString == null) {
			contentString = "<span></span>";
		}

		TextView tv = new TextView(c);
		DisplayMetrics metrics = new DisplayMetrics();
		c.getWindowManager().getDefaultDisplay().getMetrics(metrics);

		int dpi = metrics.densityDpi;
		float textSizePixels = tv.getTextSize();
		int webViewFontSizePoints = (int)(textSizePixels * 72 / dpi * 2.75);
		//int color = tv.getTextColors().getDefaultColor();
		int color = textColor;

		StringBuffer contentBuffer = new StringBuffer();
		contentBuffer.append("\n<style type=\"text/css\">\n");
		contentBuffer.append("\tbody,a {\n");
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
		settings.setJavaScriptEnabled(true);

		wv.setBackgroundColor(Color.TRANSPARENT); // make the bg transparent
		wv.loadDataWithBaseURL("fake:/blah", contentBuffer.toString(), "text/html", "utf-8", null);
	}
}
