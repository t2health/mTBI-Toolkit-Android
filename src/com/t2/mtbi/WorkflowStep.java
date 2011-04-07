package com.t2.mtbi;

import android.os.Parcel;
import android.os.Parcelable;

public class WorkflowStep implements Parcelable {
	public String id;
	public String title;
	public String negativeId;
	public String positiveId;
	public String neutralId;
	public String content;
	public String detailedContent;

	public boolean negativeButtonActive = false;
	public boolean positiveButtonActive = false;
	public boolean neutralButtonActive = false;

	@SuppressWarnings("unused")
	private static final Parcelable.Creator<WorkflowStep> CREATOR
		= new Parcelable.Creator<WorkflowStep>() {
			@Override
			public WorkflowStep createFromParcel(Parcel source) {
				return new WorkflowStep(source);
			}

			@Override
			public WorkflowStep[] newArray(int size) {
				return new WorkflowStep[size];
			}
		};

	public WorkflowStep(String id, String title,
			String positiveId, String negativeId, String neutralId) {
		this.id = id;
		this.title = title;
		this.positiveId = positiveId;
		this.negativeId = negativeId;
		this.neutralId = neutralId;
	}

	private WorkflowStep(Parcel in) {
		this.id = in.readString();
		this.title = in.readString();
		this.negativeId = in.readString();
		this.positiveId = in.readString();
		this.neutralId = in.readString();
		this.content = in.readString();
		this.detailedContent = in.readString();

		this.negativeButtonActive = in.readInt() > 0?true:false;
		this.positiveButtonActive = in.readInt() > 0?true:false;
		this.neutralButtonActive = in.readInt() > 0?true:false;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.id);
		dest.writeString(this.title);
		dest.writeString(this.negativeId);
		dest.writeString(this.positiveId);
		dest.writeString(this.neutralId);
		dest.writeString(this.content);
		dest.writeString(this.detailedContent);

		dest.writeInt(this.negativeButtonActive?1:0);
		dest.writeInt(this.positiveButtonActive?1:0);
		dest.writeInt(this.neutralButtonActive?1:0);
	}
}
