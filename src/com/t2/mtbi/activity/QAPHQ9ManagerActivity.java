package com.t2.mtbi.activity;

import com.t2.mtbi.activity.qa.SumQAManagerActivity;

public class QAPHQ9ManagerActivity extends SumQAManagerActivity {

	@Override
	protected Question getNextQuestion(Question previousQuestion) {
		Question nextQuestion = super.getNextQuestion(previousQuestion);

		if(nextQuestion != null && nextQuestion.id.equals("10")) {
			double total = this.getTotalScore();
			if(total > 0) {
				return nextQuestion;
			}
			return null;
		}

		return nextQuestion;
	}

}
