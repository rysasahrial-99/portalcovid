package com.aliendroid.tescovid;

public class Question {

    private boolean answer;
    private String question;


    private String cat_skor;

    public Question(boolean answer, String question, String cat_skor) {
        this.answer = answer;
        this.question = question;
        this.cat_skor = cat_skor;
    }

    public boolean getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }
    public String getKateogri() {
        return cat_skor;
    }
}
