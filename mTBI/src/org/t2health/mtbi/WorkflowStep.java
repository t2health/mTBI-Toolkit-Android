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

package org.t2health.mtbi;

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
