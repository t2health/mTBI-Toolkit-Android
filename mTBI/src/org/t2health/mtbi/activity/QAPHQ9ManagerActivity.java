package org.t2health.mtbi.activity;

import org.t2health.lib.qa.Question;
import org.t2health.lib.qa.SumQAManagerActivity;


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
