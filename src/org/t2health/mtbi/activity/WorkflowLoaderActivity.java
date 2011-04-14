package org.t2health.mtbi.activity;

import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class WorkflowLoaderActivity extends ABSActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Uri data = getIntent().getData();
		List<String> segments = data.getPathSegments();
		if(segments != null && segments.size() > 0) {
			String workflow = segments.get(0);

			int xmlId = getResources().getIdentifier(workflow, "xml", this.getPackageName());
			if(xmlId != 0) {
				Intent intent = new Intent(this, XMLWorkflowActivity.class);
				intent.putExtra(XMLWorkflowActivity.EXTRA_XML_RESOURCE, xmlId);
				this.startActivity(intent);
			}
		}
		this.finish();
	}

}
