/*
 *
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

package org.t2health.mtbi.activity;

import java.util.LinkedHashMap;

import org.t2health.lib.qa.Answer;
import org.t2health.lib.qa.Question;
import org.t2health.lib.qa.SumQAManagerActivity;

import android.content.Intent;


public class QAMAFManagerActivity extends SumQAManagerActivity {

	@Override
	protected Question getNextQuestion(Question previousQuestion) {
		Question nextQuestion = super.getNextQuestion(previousQuestion);
		if(nextQuestion != null && nextQuestion.id.equals("2")) {
			if(this.getSelectdAnswers().get(previousQuestion.id)[0].value > 1) {
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
		LinkedHashMap<String, Answer[]> selectedAnswers = this.getSelectdAnswers();
		LinkedHashMap<String, Question> questions = this.getQuestions();
		
		// if the first question indicated nothing, return min value.
		if(selectedAnswers.get("1")[0].value <= 1) {
			return 1;
		}
		
		double firstSum = 0;
		double secondSum = 0;
		double secondCount = 0;
		for(String qId: questions.keySet()) {
			Question q = questions.get(qId);
			Answer selectedAnswer = selectedAnswers.get(qId)[0];
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
