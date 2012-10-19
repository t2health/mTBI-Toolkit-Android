/*
 * Mild Traumatic Brain Injury Toolkit
 *
 * Copyright © 2009-2012 United States Government as represented by
 * the Chief Information Officer of the National Center for Telehealth
 * and Technology. All Rights Reserved.
 *
 * Copyright © 2009-2012 Contributors. All Rights Reserved.
 *
 * THIS OPEN SOURCE AGREEMENT ("AGREEMENT") DEFINES THE RIGHTS OF USE,
 * REPRODUCTION, DISTRIBUTION, MODIFICATION AND REDISTRIBUTION OF CERTAIN
 * COMPUTER SOFTWARE ORIGINALLY RELEASED BY THE UNITED STATES GOVERNMENT
 * AS REPRESENTED BY THE GOVERNMENT AGENCY LISTED BELOW ("GOVERNMENT AGENCY").
 * THE UNITED STATES GOVERNMENT, AS REPRESENTED BY GOVERNMENT AGENCY, IS AN
 * INTENDED THIRD-PARTY BENEFICIARY OF ALL SUBSEQUENT DISTRIBUTIONS OR
 * REDISTRIBUTIONS OF THE SUBJECT SOFTWARE. ANYONE WHO USES, REPRODUCES,
 * DISTRIBUTES, MODIFIES OR REDISTRIBUTES THE SUBJECT SOFTWARE, AS DEFINED
 * HEREIN, OR ANY PART THEREOF, IS, BY THAT ACTION, ACCEPTING IN FULL THE
 * RESPONSIBILITIES AND OBLIGATIONS CONTAINED IN THIS AGREEMENT.
 *
 * Government Agency: The National Center for Telehealth and Technology
 * Government Agency Original Software Designation: mTBI001
 * Government Agency Original Software Title: Mild Traumatic Brain Injury
 * User Registration Requested. Please send email
 * with your contact information to: robert.kayl2@us.army.mil
 * Government Agency Point of Contact for Original Software: robert.kayl2@us.army.mil
 *
 */
package org.t2health.mtbi.activity;

import java.util.List;

import org.t2health.mtbi.ActivityIntents;

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
