package org.t2health.mtbi;



import android.content.Context;
import android.content.Intent;
import android.view.View;

import org.t2health.lib.activity.WebViewActivity;
import org.t2health.lib.activity.XMLItemsBrowserActivity;
import org.t2health.lib.qa.SumQAManagerActivity;
import org.t2health.mtbi.R;
import org.t2health.mtbi.activity.MainPreferenceActivity;
import org.t2health.mtbi.activity.QAMAFManagerActivity;
import org.t2health.mtbi.activity.QAPHQ9ManagerActivity;
import org.t2health.mtbi.activity.XMLWorkflowActivity;

public class ActivityIntents {
	public static Intent getItemBrowserIntent(Context c, int xmlResId, String baseId) {
		Intent intent = new Intent(c, XMLItemsBrowserActivity.class);
		intent.putExtra(XMLItemsBrowserActivity.EXTRA_XML_RESOURCE, xmlResId);
		intent.putExtra(XMLItemsBrowserActivity.EXTRA_LEFT_BUTTON_VISIBILITY, View.GONE);
		intent.putExtra(XMLItemsBrowserActivity.EXTRA_LIST_SEPARATOR_RES_ID, R.layout.list_item_1_separator);
		intent.putExtra(XMLItemsBrowserActivity.EXTRA_LIST_ITEM_RES_ID, R.layout.list_item_1_indent);
		intent.putExtra(XMLItemsBrowserActivity.EXTRA_START_ID, baseId);
		return intent;
	}

	public static Intent getWorkflowIntent(Context c, int xmlResId) {
		Intent intent = new Intent(c, XMLWorkflowActivity.class);
		intent.putExtra(XMLWorkflowActivity.EXTRA_XML_RESOURCE, xmlResId);

		switch(xmlResId) {
		case R.xml.algorithm_a:
			intent.putExtra(XMLWorkflowActivity.EXTRA_EXPAND_ALL, true);
			break;

		case R.xml.algorithm_b:
			intent.putExtra(XMLWorkflowActivity.EXTRA_EXPAND_ALL, true);
			break;

		case R.xml.algorithm_c:
			break;

		case R.xml.cognitive_rehab_workflow:
			intent.putExtra(XMLWorkflowActivity.EXTRA_EXPAND_ALL, true);
			break;

		case R.xml.neck_stretches_workflow:
			intent.putExtra(XMLWorkflowActivity.EXTRA_EXPAND_ALL, true);
			break;

		case R.xml.vestibular_excersises_workflow:
			intent.putExtra(XMLWorkflowActivity.EXTRA_EXPAND_ALL, true);
			break;

		default:
			break;
		}

		return intent;
	}

	public static Intent getAssessmentIntent(Context c, int xmlResId) {
		Intent intent = null;
		switch(xmlResId) {
		case R.xml.qa_dhi:
			intent = new Intent(c, SumQAManagerActivity.class);
			intent.putExtra(SumQAManagerActivity.EXTRA_XML_RESOURCE, R.xml.qa_dhi);
			return intent;

		case R.xml.qa_ess:
			intent = new Intent(c, SumQAManagerActivity.class);
			intent.putExtra(SumQAManagerActivity.EXTRA_XML_RESOURCE, R.xml.qa_ess);
			return intent;

		case R.xml.qa_gcs:
			intent = new Intent(c, SumQAManagerActivity.class);
			intent.putExtra(SumQAManagerActivity.EXTRA_XML_RESOURCE, R.xml.qa_gcs);
			return intent;

		case R.xml.qa_maf:
			intent = new Intent(c, QAMAFManagerActivity.class);
			intent.putExtra(QAMAFManagerActivity.EXTRA_XML_RESOURCE, R.xml.qa_maf);
			return intent;

		case R.xml.qa_nsi:
			intent = new Intent(c, SumQAManagerActivity.class);
			intent.putExtra(SumQAManagerActivity.EXTRA_SHOW_TOTAL_SCORE, false);
			intent.putExtra(SumQAManagerActivity.EXTRA_XML_RESOURCE, R.xml.qa_nsi);
			return intent;

		case R.xml.qa_pclm:
			intent = new Intent(c, SumQAManagerActivity.class);
			intent.putExtra(SumQAManagerActivity.EXTRA_XML_RESOURCE, R.xml.qa_pclm);
			return intent;

		case R.xml.qa_phq9:
			intent = new Intent(c, QAPHQ9ManagerActivity.class);
			intent.putExtra(QAPHQ9ManagerActivity.EXTRA_XML_RESOURCE, R.xml.qa_phq9);
			return intent;
		}

		return intent;
	}
	
	public static Intent getPreferenceIntent(Context c) {
		return new Intent(c, MainPreferenceActivity.class);
	}
	
	public static Intent getAboutIntent(Context c) {
		Intent i = new Intent(c, WebViewActivity.class);
		i.putExtra(WebViewActivity.EXTRA_TITLE_TEXT, c.getString(R.string.about_title))
			.putExtra(WebViewActivity.EXTRA_CONTENT, c.getString(R.string.about_content));
		return i;
	}
}
