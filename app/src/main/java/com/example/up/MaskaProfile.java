package com.example.up;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class MaskaProfile implements Parcelable {

    public MaskaProfile(int ID, String image, String time) {
        this.ID = ID;
        Image = image;
        Time = time;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    private int ID;
    private String Image;
    private String Time;

    protected MaskaProfile(Parcel in) {
        ID = in.readInt();
        Image = in.readString();
        Time = in.readString();
    }

    public static final Creator<MaskaProfile> CREATOR = new Creator<MaskaProfile>() {
        @Override
        public MaskaProfile createFromParcel(Parcel in) {
            return new MaskaProfile(in);
        }

        @Override
        public MaskaProfile[] newArray(int size) {
            return new MaskaProfile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(Image);
        dest.writeString(Time);
    }
}