package com.example.illumination_test.RecViewClass;

import android.os.Parcel;
import android.os.Parcelable;

public class Quiz implements Parcelable  {

    public Quiz() {
    }

    private int id;
    private String subject;
    private int numberItems;
    private int progressNumber;


    //Constructors
    public Quiz(int id, String subject, int numberItems, int progressNumber) {
        this.id = id;
        this.subject = subject;
        this.numberItems = numberItems;
        this.progressNumber = progressNumber;
    }


    protected Quiz(Parcel in) {
        id = in.readInt();
        subject = in.readString();
        numberItems = in.readInt();
        progressNumber = in.readInt();
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };

    //toString
    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", numberItems='" + numberItems + '\'' +
                ", progressNumber=" + progressNumber +
                '}';
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getNumberItems() {
        return numberItems;
    }

    public void setNumberItems(String numberItems) {
        this.numberItems = Integer.parseInt(numberItems);
    }

    public int getProgressNumber() {
        return progressNumber;
    }

    public void setProgressNumber(int progressNumber) {
        this.progressNumber = progressNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(subject);
        parcel.writeInt(numberItems);
        parcel.writeInt(progressNumber);
    }
}
