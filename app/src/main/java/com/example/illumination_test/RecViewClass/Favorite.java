package com.example.illumination_test.RecViewClass;

public class Favorite {
    private String number;
    private String answer;

    public Favorite(String number, String answer) {
        this.number = number;
        this.answer = answer;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String   toString() {
        return "Favorite{" +
                "number='" + number + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
