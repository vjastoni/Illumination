package com.example.illumination_test.OOP;

import android.os.Parcel;
import android.os.Parcelable;

public class CurrentQuiz implements Parcelable {
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctAns;
    private String selectedAns;
    private String questionNo;
    private String subject;

    protected CurrentQuiz(Parcel in) {
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        correctAns = in.readString();
        selectedAns = in.readString();
        questionNo = in.readString();
        subject = in.readString();
    }

    public static final Creator<CurrentQuiz> CREATOR = new Creator<CurrentQuiz>() {
        @Override
        public CurrentQuiz createFromParcel(Parcel in) {
            return new CurrentQuiz(in);
        }

        @Override
        public CurrentQuiz[] newArray(int size) {
            return new CurrentQuiz[size];
        }
    };

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

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    public String getSelectedAns() {
        return selectedAns;
    }

    public void setSelectedAns(String selectedAns) {
        this.selectedAns = selectedAns;
    }

    public String getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(String questionNo) {
        this.questionNo = questionNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public CurrentQuiz(String question, String option1, String option2, String option3, String option4, String correctAns, String questionNo, String subject) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAns = correctAns;
        this.questionNo = questionNo;
        this.subject = subject;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeString(option4);
        dest.writeString(correctAns);
        dest.writeString(selectedAns);
        dest.writeString(questionNo);
        dest.writeString(subject);
    }
}
