package com.example.illumination_test.Fragment;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionEdit implements Parcelable {
    private String number;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;

    public QuestionEdit(String number, String question, String option1, String option2, String option3, String option4, String answer) {
        this.number = number;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
    }

    protected QuestionEdit(Parcel in) {
        number = in.readString();
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        answer = in.readString();

    }

    public static final Creator<QuestionEdit> CREATOR = new Creator<QuestionEdit>() {
        @Override
        public QuestionEdit createFromParcel(Parcel in) {
            return new QuestionEdit(in);
        }

        @Override
        public QuestionEdit[] newArray(int size) {
            return new QuestionEdit[size];
        }
    };

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
        return "QuestionEdit{" + "number='" + number + '\'' + ", question='" + question + '\'' + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(number);
        dest.writeString(question);
    }
}
