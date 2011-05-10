package org.t2health.mtbi.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;


import org.t2health.lib.activity.BaseNavigationActivity;
import org.t2health.lib.widget.PersistantStateButton;
import org.t2health.mtbi.R;

public class VCodesActivity extends BaseNavigationActivity implements OnClickListener {
	private PersistantStateButton[] globalWarButtonList;
	private PersistantStateButton[] severityButtons;
	private int[] globalWarValueList;
	private int[] severityValueList;
	private EditText vCodeResultText;
	private String[] vCodesArray;

	private static final int YES = 0;
	private static final int NO = 5;
	private static final int UNKNOWN = 10;

	private static final int SEV_UNKNOWN = 1;
	private static final int SEV_MILD = 2;
	private static final int SEV_MODERATE = 3;
	private static final int SEV_SEVERE = 4;
	private static final int SEV_PENETRATING = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.vCodesArray = this.getResources().getStringArray(R.array.v_codes);

		this.setContentView(R.layout.vcodes_activity);
		this.setTitle(getString(R.string.v_codes_title));

		this.vCodeResultText = (EditText)this.findViewById(R.id.vcodeResult);

		globalWarButtonList = new PersistantStateButton[]{
				(PersistantStateButton)this.findViewById(R.id.yesButton),
				(PersistantStateButton)this.findViewById(R.id.noButton),
				(PersistantStateButton)this.findViewById(R.id.unknownButton),
		};
		globalWarValueList = new int[] {
				YES,
				NO,
				UNKNOWN,
		};

		severityButtons = new PersistantStateButton[] {
				(PersistantStateButton)this.findViewById(R.id.severityMildButton),
				(PersistantStateButton)this.findViewById(R.id.severityModerateButton),
				(PersistantStateButton)this.findViewById(R.id.severityPenetratingButton),
				(PersistantStateButton)this.findViewById(R.id.severitySevereButton),
				(PersistantStateButton)this.findViewById(R.id.severityUnknownButton),
		};
		severityValueList = new int[] {
				SEV_MILD,
				SEV_MODERATE,
				SEV_PENETRATING,
				SEV_SEVERE,
				SEV_UNKNOWN
		};

		for(int i = 0; i < globalWarButtonList.length; ++i) {
			globalWarButtonList[i].setOnClickListener(this);
		}
		for(int i = 0; i < severityButtons.length; ++i) {
			severityButtons[i].setOnClickListener(this);
		}

		// set the initial vcode.
		checkGlobalWarButton(null, false);
		checkSeverityButton(null, false);
		updateVCode();
	}

	private boolean isGlobalWarButton(View v) {
		for(int i = 0; i < globalWarButtonList.length; ++i) {
			if(globalWarButtonList[i] == v) {
				return true;
			}
		}
		return false;
	}

	private boolean isSeverityButton(View v) {
		for(int i = 0; i < severityButtons.length; ++i) {
			if(severityButtons[i] == v) {
				return true;
			}
		}
		return false;
	}

	private void checkGlobalWarButton(PersistantStateButton v, boolean checked) {
		for(int i = 0; i < globalWarButtonList.length; ++i) {
			globalWarButtonList[i].setChecked(false);
		}

		if(v != null) {
			v.setChecked(checked);
		}
	}

	private void checkSeverityButton(PersistantStateButton v, boolean checked) {
		for(int i = 0; i < severityButtons.length; ++i) {
			severityButtons[i].setChecked(false);
		}

		if(v != null) {
			v.setChecked(checked);
		}
	}

	private int getGlobalWarValue() {
		for(int i = 0; i < globalWarButtonList.length; ++i) {
			if(globalWarButtonList[i].isChecked()) {
				return this.globalWarValueList[i];
			}
		}
		return -1;
	}

	private int getSeverityValue() {
		for(int i = 0; i < severityButtons.length; ++i) {
			if(severityButtons[i].isChecked()) {
				return this.severityValueList[i];
			}
		}
		return -1;
	}

	private void updateVCode() {
		int warValue = getGlobalWarValue();
		int severityValue = getSeverityValue();
		int vCodeIndex = warValue + severityValue;

		this.vCodeResultText.setText("");

		if(warValue < 0 && severityValue < 0) {
			vCodeIndex = 0;
		} else if(warValue < 0 || severityValue < 0) {
			vCodeIndex = -1;
		}

		if(vCodeIndex > -1 && vCodeIndex < this.vCodesArray.length) {
			this.vCodeResultText.setText(this.vCodesArray[vCodeIndex]);
		}
	}

	@Override
	public void onClick(View arg0) {
		if(isSeverityButton(arg0)) {
			PersistantStateButton tb = (PersistantStateButton)arg0;
			checkSeverityButton((PersistantStateButton)arg0, !tb.isChecked());
			updateVCode();

		} else if(isGlobalWarButton(arg0)) {
			PersistantStateButton tb = (PersistantStateButton)arg0;
			checkGlobalWarButton((PersistantStateButton)arg0, !tb.isChecked());
			updateVCode();
		}
	}
}
