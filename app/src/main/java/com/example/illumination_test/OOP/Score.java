package com.example.illumination_test.OOP;

import android.os.Parcel;
import android.os.Parcelable;

public class Score implements Parcelable {
    private int total;
    private int correctAns;
    private int wrongAns;
    private int noAns;

    public Score(){
        total = 0;
        correctAns = 0;
        wrongAns = 0;
        noAns = 0;
    }

    public Score(int total, int correctAns, int wrongAns, int noAns) {
        this.total = total;
        this.correctAns = correctAns;
        this.wrongAns = wrongAns;
        this.noAns = noAns;
    }

    protected Score(Parcel in) {
        total = in.readInt();
        correctAns = in.readInt();
        wrongAns = in.readInt();
        noAns = in.readInt();
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }

    public int getWrongAns() {
        return wrongAns;
    }

    public void setWrongAns(int wrongAns) {
        this.wrongAns = wrongAns;
    }

    public int getNoAns() {
        return noAns;
    }

    public void setNoAns(int noAns) {
        this.noAns = noAns;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(total);
        dest.writeInt(correctAns);
        dest.writeInt(wrongAns);
        dest.writeInt(noAns);
    }
}
