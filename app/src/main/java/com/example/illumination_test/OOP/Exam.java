package com.example.illumination_test.OOP;

import java.util.Map;

public class Exam {
    private String subject;
    private Map<String, Question> questions;

    public Exam() {
    }

    public Exam(String subject, Map<String, Question> questions) {
        this.subject = subject;
        this.questions = questions;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Map<String, Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<String, Question> questions) {
        this.questions = questions;
    }
}
