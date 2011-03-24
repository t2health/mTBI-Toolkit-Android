package com.t2.mtbi.activity.qa;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.t2.mtbi.R;
import com.t2.mtbi.activity.qa.XMLQAManager.Answer;

public class SingleChoiceQuestionActivity extends XMLQAQuestion implements OnItemClickListener, OnClickListener {
	private Answer selectedAnswer;
	private View nextButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.single_choice_question_activity);

		((TextView)this.findViewById(R.id.text1)).setText(this.question.title);
		ListView listView = (ListView) this.findViewById(R.id.list);
		listView.setAdapter(new AnswerAdapter(
				this,
				android.R.layout.simple_list_item_single_choice,
				this.answers
		));
		listView.setOnItemClickListener(this);

		this.nextButton = this.findViewById(R.id.nextButton);
		this.nextButton.setOnClickListener(this);
		this.nextButton.setEnabled(false);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		this.selectedAnswer = (Answer)arg0.getItemAtPosition(arg2);
		this.nextButton.setEnabled(true);
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()) {
		case R.id.nextButton:
			this.finish(question, selectedAnswer);
			break;
		}
	}
}
