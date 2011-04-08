package com.t2.mtbi.activity.qa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.t2.mtbi.activity.ABSNavigationActivity;
import com.t2.mtbi.activity.qa.XMLQAManager.Answer;
import com.t2.mtbi.activity.qa.XMLQAManager.Question;

public abstract class XMLQAQuestion extends ABSNavigationActivity {
	public static final String EXTRA_QUESTION = "questionObject";
	public static final String EXTRA_ANSWERS = "answersList";
	public static final String EXTRA_TOTAL_QUESIONS = "questionsCount";
	public static final String EXTRA_QUESTION_INDEX = "questionIndex";
	protected Question question;
	protected Answer[] answers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = this.getIntent();
		question = (Question)intent.getParcelableExtra(EXTRA_QUESTION);

		Parcelable[] ansParcels = intent.getParcelableArrayExtra(EXTRA_ANSWERS);
		answers = new Answer[ansParcels.length];
		for(int i = 0; i < answers.length; ++i) {
			answers[i] = (Answer)ansParcels[i];
		}

		if(question == null) {
			this.finish();
			return;
		}

		if(answers == null || answers.length <= 0) {
			this.finish();
			return;
		}
	}

	protected final void finish(Question q, Answer selectedAnswer) {
		Intent i = new Intent();
		i.putExtra(EXTRA_QUESTION, q);
		i.putExtra(EXTRA_ANSWERS, new Answer[]{selectedAnswer});
		this.setResult(RESULT_OK, i);
		this.finish();
	}

	public static class AnswerAdapter extends ArrayAdapter<Answer> {
		public AnswerAdapter(Context context, int textViewResourceId,
				Answer[] objects) {
			super(context, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Answer ans = getItem(position);
			if(convertView == null) {
				convertView = super.getView(position, convertView, parent);
			}

			View titleView = convertView.findViewById(android.R.id.text1);
			View descView = convertView.findViewById(android.R.id.text2);

			if(titleView != null) {
				((TextView)titleView).setText(ans.title);
			}
			if(descView != null) {
				((TextView)descView).setText(ans.desc);
			}

			return convertView;
		}
	}
}
