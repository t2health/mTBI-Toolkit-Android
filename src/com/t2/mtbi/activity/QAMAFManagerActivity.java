package com.t2.mtbi.activity;

import android.content.Intent;

import com.t2.mtbi.activity.qa.SumQAManagerActivity;

public class QAMAFManagerActivity extends SumQAManagerActivity {
	private static final String TAG = QAMAFManagerActivity.class.getSimpleName();

	@Override
	protected Question getNextQuestion(Question previousQuestion) {
		Question nextQuestion = super.getNextQuestion(previousQuestion);
		if(nextQuestion != null && nextQuestion.id.equals("2")) {
			if(this.selectdAnswers.get(previousQuestion.id)[0].value > 1) {
				return nextQuestion;
			}
			return null;
		}

		return nextQuestion;
	}

	@Override
	protected Intent getQuestionIntent(Question question, Answer[] answers,
			int totalQuestions, int questionIndex) {

		if(question.id.equals("15") || question.id.equals("16")) {
			return super.getQuestionIntent(question, answers, totalQuestions, questionIndex);
		}

		return new Intent(this, QAMAFSliderQuestionActivity.class);
	}

	@Override
	protected double getTotalScore() {
		// if the first question indicated nothing, return min value.
		if(this.selectdAnswers.get("1")[0].value <= 1) {
			return 1;
		}

		double firstSum = 0;
		double secondSum = 0;
		double secondCount = 0;
		for(String qId: this.questions.keySet()) {
			Question q = this.questions.get(qId);
			Answer selectedAnswer = this.selectdAnswers.get(qId)[0];
			if(selectedAnswer.value < 0) {
				continue;
			}

			if(q.id.equals("16")) {
				continue;
			}

			if(q.id.equals("1") || q.id.equals("2") || q.id.equals("3")) {
				firstSum += selectedAnswer.value;
			} else if(selectedAnswer.value <= 0) {
				continue;
			} else if(q.id.equals("15")) {
				secondSum += (selectedAnswer.value * 2.5);
				++secondCount;
			} else {
				secondSum += selectedAnswer.value;
				++secondCount;
			}
		}

		return (int)(firstSum + (secondSum / secondCount));
	}
}
