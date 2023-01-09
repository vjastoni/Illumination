package com.example.illumination_test.Fragment;

public class QuestionEdit {
    private String number;
    private String question;

    public QuestionEdit(String number, String question) {
        this.number = number;
        this.question = question;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "QuestionEdit{" +
                "number='" + number + '\'' +
                ", question='" + question + '\'' +
                '}';
    }
}
