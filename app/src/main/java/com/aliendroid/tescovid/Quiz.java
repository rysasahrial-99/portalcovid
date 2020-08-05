package com.aliendroid.tescovid;

import java.util.List;

public class Quiz {

    public List<Question> listOfQuestions;
    private int currentQuestion;
    private int scoretest =0;


    public Quiz(List<Question> listOfQuestions) {
        this.listOfQuestions = listOfQuestions;
        currentQuestion = 1;
    }

    public List<Question> getListOfQuestions() {
        return listOfQuestions;
    }


    public int getScoretest() {
        return scoretest;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }
    public void checkAnswer(boolean answer) {
        if (answer == listOfQuestions.get(currentQuestion - 1).getAnswer()) {
            scoretest++;
        }
        currentQuestion++;
    }

    public boolean hasQuestions() {
        if(currentQuestion >= listOfQuestions.size()) {
            return false;
        }
        else {
            return true;
        }
    }
}