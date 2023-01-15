package com.example.illumination_test.OOP;

public class Question {
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private String subject;

    public Question(){

    }

    public Question(String question, String option1, String option2, String option3, String option4, String answer, String subject) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.subject = subject;
    }

    public String getSubject() {return subject;}

    public void setSubject(String subject) {this.subject = subject;}

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

//import java.util.List;
//
//public class Question {
//    private List<Option> options;
//    private String answer;
//
//    public Question() {
//        // Default constructor required for calls to DataSnapshot.getValue(Question.class)
//    }
//
//    public Question(List<Option> options, String answer) {
//        this.options = options;
//        this.answer = answer;
//    }
//
//    public List<Option> getOptions() {
//        return options;
//    }
//
//    public String getAnswer() {
//        return answer;
//    }
//}
