package com.t2.mtbi.activity;

import java.util.List;

import com.t2.mtbi.ActivityIntents;
import com.t2.mtbi.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class ActivityUriLoader extends Activity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		Uri data = getIntent().getData();
		Intent intent = getUriIntent(this, data);

		if(intent != null) {
			this.startActivity(intent);
		}

		this.finish();
		return;
	}

	public static Intent getUriIntent(Activity activity, Uri uri) {
		String host = uri.getHost().toLowerCase();

		Intent intent = null;
		if(host.equals("itemsbrowser")) {
			intent = getItemsBrowserIntent(activity, uri);
		} else if(host.equals("workflow")) {
			intent = getWorkflowIntent(activity, uri);
		} else if(host.equals("assessment")) {
			intent = getAssessmentIntent(activity, uri);
		}

		return intent;
	}

	private static Intent getItemsBrowserIntent(Context c, Uri data) {
		List<String> path = data.getPathSegments();
		String xmlResourceStr = null;
		String baseId = null;
		if(path == null || path.size() <= 0) {
			return null;
		}

		xmlResourceStr = path.get(0);
		if(path.size() > 1) {
			baseId = path.get(1);
		}

		int xmlId = c.getResources().getIdentifier(xmlResourceStr, "xml", c.getPackageName());
		if(xmlId == 0) {
			return null;
		}

		return ActivityIntents.getItemBrowserIntent(c, xmlId, baseId);
	}

	private static Intent getWorkflowIntent(Context c, Uri data) {
		List<String> path = data.getPathSegments();
		String xmlResourceStr = null;
		if(path == null || path.size() <= 0) {
			return null;
		}

		xmlResourceStr = path.get(0);

		int xmlId = c.getResources().getIdentifier(xmlResourceStr, "xml", c.getPackageName());
		if(xmlId == 0) {
			return null;
		}

		return ActivityIntents.getWorkflowIntent(c, xmlId);
	}

	private static Intent getAssessmentIntent(Context c, Uri data) {
		List<String> path = data.getPathSegments();
		String xmlResourceStr = null;
		if(path == null || path.size() <= 0) {
			return null;
		}

		xmlResourceStr = path.get(0);

		int xmlId = c.getResources().getIdentifier(xmlResourceStr, "xml", c.getPackageName());
		if(xmlId == 0) {
			return null;
		}

		return ActivityIntents.getAssessmentIntent(c, xmlId);
	}

}
