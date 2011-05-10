package org.t2health.mtbi.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.t2health.lib.activity.BaseNavigationActivity;
import org.t2health.lib.activity.WebViewActivity;
import org.t2health.lib.util.WebViewUtil;
import org.t2health.mtbi.WorkflowStep;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;


import org.t2health.mtbi.R;

public class XMLWorkflowActivity extends BaseNavigationActivity {
	private static final String TAG = XMLWorkflowActivity.class.getSimpleName();
	public static final String EXTRA_XML_RESOURCE = "xmlResourceId";
	public static final String EXTRA_JUMPTO_NEXT = "jumpToNext";
	public static final String EXTRA_EXPAND_ALL = "expandAll";
	private ListView listView;
	private int xmlResourceId;
	private LinkedHashMap<String, WorkflowStep> workflowSteps = new LinkedHashMap<String, WorkflowStep>();
	private WorkflowStepAdapter adapter;
	private ArrayList<WorkflowStep> currentSteps;
	private Activity thisActivity = this;
	private boolean jumpToNext;
	private boolean expandAll;
	private LinearLayout contentArea;
	private ScrollView scrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		this.jumpToNext = intent.getBooleanExtra(EXTRA_JUMPTO_NEXT, false);
		this.expandAll = intent.getBooleanExtra(EXTRA_EXPAND_ALL, false);

		this.xmlResourceId = intent.getIntExtra(EXTRA_XML_RESOURCE, -1);
		if(this.xmlResourceId == -1) {
			this.finish();
			return;
		}

		contentArea = new LinearLayout(this);
		contentArea.setOrientation(LinearLayout.VERTICAL);
		scrollView = new ScrollView(this);
		scrollView.addView(contentArea, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		this.setContentView(scrollView);
		
		this.workflowSteps = this.getSteps(xmlResourceId);
		this.currentSteps = new ArrayList<WorkflowStep>();
		this.adapter = new WorkflowStepAdapter(this, this.currentSteps);

		/*listView = new ListView(this);
		listView.setScrollingCacheEnabled(false);
		listView.setCacheColorHint(Color.TRANSPARENT);
		listView.setAdapter(this.adapter);
		this.setContentView(listView);*/


		// add the steps from the previous orientation shift.
		if(savedInstanceState != null) {
			this.expandAll = savedInstanceState.getBoolean(EXTRA_EXPAND_ALL);
			this.adapter.setButtonsHidden(!expandAll);

			ArrayList<WorkflowStep> tmpSteps = savedInstanceState.getParcelableArrayList("currentSteps");
			String lastId = "";
			for(int i = 0; i < tmpSteps.size(); ++i) {
				WorkflowStep step = tmpSteps.get(i);
Log.v(TAG, "neutral:"+step.neutralButtonActive);
				workflowSteps.put(step.id, step);
				addStepToList(lastId, step.id);

				lastId = step.id;
			}
			/*if(tmpSteps != null && tmpSteps.size() > 0) {
				this.currentSteps.clear();
				this.currentSteps.addAll(tmpSteps);
			}
			//adapter.notifyDataSetChanged();
			for(int i = 0; i < currentSteps.size(); ++i) {
				contentArea.addView(adapter.getView(i, null, null), new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			}*/
		}

		// add the first step.
		if(this.currentSteps.size() == 0) {
			WorkflowStep[] steps = this.workflowSteps.values().toArray(new WorkflowStep[this.workflowSteps.size()]);
			if(steps != null && steps.length > 0) {
				if(expandAll) {
					adapter.setButtonsHidden(true);
					for(int i = 0; i < steps.length; ++i) {
						steps[i].neutralButtonActive = i < steps.length - 1;
						this.currentSteps.add(steps[i]);
					}
					//adapter.notifyDataSetChanged();
					for(int i = 0; i < currentSteps.size(); ++i) {
						contentArea.addView(adapter.getView(i, null, null), new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
					}
				} else {
					addStepToList("", steps[0].id);
				}
			}
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(EXTRA_EXPAND_ALL, this.expandAll);
		outState.putParcelableArrayList("currentSteps", this.currentSteps);
	}

	private LinkedHashMap<String,WorkflowStep> getSteps(int xmlResourceId) {
		LinkedHashMap<String,WorkflowStep> steps = new LinkedHashMap<String,WorkflowStep>();

		boolean inWorkflow = false;
		boolean inStep = false;
		WorkflowStep currentStep = null;
		try {
			XmlResourceParser parser = this.getResources().getXml(xmlResourceId);
			int eventType = parser.getEventType();
			while(eventType != XmlPullParser.END_DOCUMENT) {
				String tag = parser.getName();

				if(eventType == XmlPullParser.START_TAG) {
					if(tag.equals("workflow") && !inWorkflow) {
						inWorkflow = true;
						String title = parser.getAttributeValue(null, "title");
						if(title != null && title.length() > 0) {
							this.setTitle(title);
						}

					} else if(tag.equals("step") && !inStep) {
						currentStep = new WorkflowStep(
								parser.getAttributeValue(null, "id"),
								parser.getAttributeValue(null, "title"),
								parser.getAttributeValue(null, "positiveStepId"),
								parser.getAttributeValue(null, "negativeStepId"),
								parser.getAttributeValue(null, "neutralStepId")
						);
						inStep = true;

					} else if(tag.equals("title") && inStep) {
						if(parser.next() == XmlPullParser.TEXT) {
							currentStep.title = parser.getText();
						}

					} else if(tag.equals("content") && inStep) {
						if(parser.next() == XmlPullParser.TEXT) {
							currentStep.content = parser.getText();
						}

					} else if(tag.equals("detailedContent") && inStep) {
						if(parser.next() == XmlPullParser.TEXT) {
							currentStep.detailedContent = parser.getText();
						}
					}
				} else if(eventType == XmlPullParser.END_TAG) {
					if(tag.equals("step")) {
						inStep = false;
						steps.put(currentStep.id, currentStep);
					}
				}

				eventType = parser.next();
			}
			parser.close();

		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return steps;
	}

	private void onDetailsButtonPressed(String id) {
		WorkflowStep step = this.workflowSteps.get(id);
		Intent intent = new Intent(this, WebViewActivity.class);
		intent.putExtra(WebViewActivity.EXTRA_CONTENT, step.detailedContent);
		this.startActivity(intent);
	}

	private void addStepToList(String fromId, String id) {
		rollbackToStep(fromId, false);

		if(this.currentSteps.size() > 0) {
			this.contentArea.removeViewAt(contentArea.getChildCount()-1);
			this.contentArea.addView(
					adapter.getView(this.currentSteps.size()-1, null, null),
					new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT)
			);
		}

		WorkflowStep newStep = this.workflowSteps.get(id);
		newStep.negativeButtonActive = false;
		newStep.positiveButtonActive = false;
		newStep.neutralButtonActive = false;
		this.currentSteps.add(newStep);
		this.contentArea.addView(
				adapter.getView(this.currentSteps.size()-1, null, null),
				new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT)
		);
		//this.adapter.notifyDataSetChanged();

		if(this.jumpToNext) {
			listView.setSelection(this.currentSteps.size()-1);
		}
	}

	private void rollbackToStep(String id, boolean inclusive) {
		for(int i = this.currentSteps.size()-1; i >= 0; --i) {
			boolean idsMatch = this.currentSteps.get(i).id.equals(id);
			if(idsMatch && !inclusive) {
				break;
			}

			this.currentSteps.remove(i);
			this.contentArea.removeViewAt(i);

			if(idsMatch && inclusive) {
				break;
			}
		}
	}

	private class WorkflowStepAdapter extends ArrayAdapter<WorkflowStep> {

		private LayoutInflater layoutInflater;
		private boolean hideButtons;

		public WorkflowStepAdapter(Context context, List<WorkflowStep> objects) {
			super(context, 0, objects);
			this.layoutInflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
		}

		public void setButtonsHidden(boolean b) {
			this.hideButtons = b;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			WorkflowStep item = this.getItem(position);

			View newView = convertView;
			if(newView == null) {
				newView = layoutInflater.inflate(R.layout.workflow_step, null);
			}


			if(item.title == null || item.title.length() == 0) {
				((ViewGroup)newView.findViewById(R.id.text1).getParent()).removeView(newView.findViewById(R.id.text1));
			} else {
				((TextView)newView.findViewById(R.id.text1)).setText(item.title);
			}

			if(item.content == null || item.content.length() == 0) {
				((ViewGroup)newView.findViewById(R.id.text2).getParent()).removeView(newView.findViewById(R.id.text2));
			} else {
				WebView wv = (WebView)newView.findViewById(R.id.text2);
				WebViewUtil.formatWebViewText(thisActivity, wv, item.content, Color.BLACK);
			}

			newView.findViewById(R.id.detailsButton).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					onDetailsButtonPressed(getItem(position).id);
				}
			});
			newView.findViewById(R.id.negativeButton).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					WorkflowStep s = getItem(position);
					s.negativeButtonActive = true;
					s.positiveButtonActive = false;
					s.neutralButtonActive = false;
					addStepToList(s.id, s.negativeId);
				}
			});
			newView.findViewById(R.id.positiveButton).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					WorkflowStep s = getItem(position);
					s.negativeButtonActive = false;
					s.positiveButtonActive = true;
					s.neutralButtonActive = false;
					addStepToList(s.id, s.positiveId);
				}
			});
			newView.findViewById(R.id.neutralButton).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					WorkflowStep s = getItem(position);
					s.negativeButtonActive = false;
					s.positiveButtonActive = false;
					s.neutralButtonActive = true;
					addStepToList(s.id, s.neutralId);
				}
			});

			newView.findViewById(R.id.detailsButton).setVisibility(View.INVISIBLE);
			newView.findViewById(R.id.negativeButton).setVisibility(View.INVISIBLE);
			newView.findViewById(R.id.positiveButton).setVisibility(View.INVISIBLE);
			newView.findViewById(R.id.neutralButton).setVisibility(View.INVISIBLE);
			if(item.detailedContent != null && item.detailedContent.length() > 0) {
				newView.findViewById(R.id.detailsButton).setVisibility(View.VISIBLE);
			} else {
				((ViewGroup)newView.findViewById(R.id.detailsButton).getParent()).removeView(newView.findViewById(R.id.detailsButton));
			}
			if(item.negativeId != null && item.negativeId.length() > 0) {
				newView.findViewById(R.id.negativeButton).setVisibility(View.VISIBLE);
			}
			if(item.positiveId != null && item.positiveId.length() > 0) {
				newView.findViewById(R.id.positiveButton).setVisibility(View.VISIBLE);
			}
			if(item.neutralId != null && item.neutralId.length() > 0) {
				newView.findViewById(R.id.neutralButton).setVisibility(View.VISIBLE);
			}

			if(hideButtons) {
				newView.findViewById(R.id.negativeButton).setVisibility(View.GONE);
				newView.findViewById(R.id.positiveButton).setVisibility(View.GONE);
				newView.findViewById(R.id.neutralButton).setVisibility(View.GONE);
			}

			newView.findViewById(R.id.negativeArrow).setVisibility(View.INVISIBLE);
			newView.findViewById(R.id.positiveArrow).setVisibility(View.INVISIBLE);
			newView.findViewById(R.id.neutralArrow).setVisibility(View.INVISIBLE);
			if(item.negativeButtonActive) {
				newView.findViewById(R.id.negativeArrow).setVisibility(View.VISIBLE);
			}
			if(item.positiveButtonActive) {
				newView.findViewById(R.id.positiveArrow).setVisibility(View.VISIBLE);
			}
			if(item.neutralButtonActive) {
				newView.findViewById(R.id.neutralArrow).setVisibility(View.VISIBLE);
			}

			return newView;
		}

		@Override
		public boolean areAllItemsEnabled() {
			return false;
		}

		@Override
		public boolean isEnabled(int position) {
			return false;
		}
	}
}
