package com.example.illumination_test.OOP;

public class Option {
    private String option;
    private String question;

    public Option() {
        // Default constructor required for calls to DataSnapshot.getValue(Option.class)
    }

    public Option(String option, String question) {
        this.option = option;
        this.question = question;
    }

    public String getOption() {
        return option;
    }

    public String getQuestion() {
        return question;
    }
}
