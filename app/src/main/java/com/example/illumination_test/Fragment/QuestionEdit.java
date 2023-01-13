package com.example.illumination_test.Fragment;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionEdit implements Parcelable {
    private String number;
    private String question;

    public QuestionEdit(String number, String question) {
        this.number = number;
        this.question = question;
    }

    protected QuestionEdit(Parcel in) {
        number = in.readString();
        question = in.readString();
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
