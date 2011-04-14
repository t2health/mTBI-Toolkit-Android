package org.t2health.mtbi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class ToggledButton extends Button {

	private boolean isChecked = false;

	public ToggledButton(Context context) {
		super(context);
	}

	public ToggledButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ToggledButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
		this.refreshDrawableState();
	}

	public boolean isChecked() {
		return isChecked;
	}

	@Override
	protected int[] onCreateDrawableState(int extraSpace) {
		int[] states;

		if(this.isChecked()) {
			states = Button.PRESSED_WINDOW_FOCUSED_STATE_SET;
		} else {
			states = super.onCreateDrawableState(extraSpace);
		}

		return states;
	}
}
