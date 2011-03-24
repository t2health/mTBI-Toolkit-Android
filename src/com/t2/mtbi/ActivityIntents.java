package com.t2.mtbi;

import com.t2.mtbi.activity.CPGActivity;
import com.t2.mtbi.activity.CognitiveRehabActivity;
import com.t2.mtbi.activity.EducationActivity;
import com.t2.mtbi.activity.ICD9Activity;
import com.t2.mtbi.activity.MainPreferenceActivity;
import com.t2.mtbi.activity.QAMAFManagerActivity;
import com.t2.mtbi.activity.QAPHQ9ManagerActivity;
import com.t2.mtbi.activity.SymptomManagementActivity;
import com.t2.mtbi.activity.ToolsActivity;
import com.t2.mtbi.activity.WebViewActivity;
import com.t2.mtbi.activity.XMLItemsBrowserActivity;
import com.t2.mtbi.activity.XMLWorkflowActivity;
import com.t2.mtbi.activity.qa.SumQAManagerActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ActivityIntents {
	public static Intent getItemBrowserIntent(Context c, int xmlResId, String baseId) {
		Intent intent = null;
		switch(xmlResId) {
		case R.xml.cognitive_rehab:
			intent = new Intent(c, CognitiveRehabActivity.class);
			break;

		case R.xml.cpg:
			intent = new Intent(c, CPGActivity.class);
			break;

		case R.xml.education:
			intent = new Intent(c, EducationActivity.class);
			break;

		case R.xml.icd9_coding:
			intent = new Intent(c, ICD9Activity.class);
			break;

		case R.xml.symptom_managment:
			intent = new Intent(c, SymptomManagementActivity.class);
			break;

		case R.xml.tools_items:
			intent = new Intent(c, ToolsActivity.class);
			break;

		default:
			intent = new Intent(c, XMLItemsBrowserActivity.class);
			break;
		}

		if(baseId != null && intent != null) {
			intent.putExtra(XMLItemsBrowserActivity.EXTRA_BASE_ITEM_ID, baseId);
		}

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
			intent.putExtra(SumQAManagerActivity.EXTRA_XML_RESID, R.xml.qa_dhi);
			return intent;

		case R.xml.qa_ess:
			intent = new Intent(c, SumQAManagerActivity.class);
			intent.putExtra(SumQAManagerActivity.EXTRA_XML_RESID, R.xml.qa_ess);
			return intent;

		case R.xml.qa_gcs:
			intent = new Intent(c, SumQAManagerActivity.class);
			intent.putExtra(SumQAManagerActivity.EXTRA_XML_RESID, R.xml.qa_gcs);
			return intent;

		case R.xml.qa_maf:
			intent = new Intent(c, QAMAFManagerActivity.class);
			intent.putExtra(QAMAFManagerActivity.EXTRA_XML_RESID, R.xml.qa_maf);
			return intent;

		case R.xml.qa_nsi:
			intent = new Intent(c, SumQAManagerActivity.class);
			intent.putExtra(SumQAManagerActivity.EXTRA_SHOW_TOTAL_SCORE, false);
			intent.putExtra(SumQAManagerActivity.EXTRA_XML_RESID, R.xml.qa_nsi);
			return intent;

		case R.xml.qa_pclm:
			intent = new Intent(c, SumQAManagerActivity.class);
			intent.putExtra(SumQAManagerActivity.EXTRA_XML_RESID, R.xml.qa_pclm);
			return intent;

		case R.xml.qa_phq9:
			intent = new Intent(c, QAPHQ9ManagerActivity.class);
			intent.putExtra(QAPHQ9ManagerActivity.EXTRA_XML_RESID, R.xml.qa_phq9);
			return intent;
		}

		return intent;
	}
	
	public static Intent getPreferenceIntent(Context c) {
		return new Intent(c, MainPreferenceActivity.class);
	}
	
	public static Intent getAboutIntent(Context c) {
		Intent i = new Intent(c, WebViewActivity.class);
		i.putExtra(WebViewActivity.EXTRA_TITLE, c.getString(R.string.about_title))
			.putExtra(WebViewActivity.EXTRA_CONTENT, c.getString(R.string.about_content));
		return i;
	}
}
