package com.example.neilpelow.memoapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Memos implements Parcelable {

    private int _id;
    private String _memobody;
    private String address;
    private String imagePath;

    public Memos() {
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_memobody() {
        return _memobody;
    }

    public void set_memobody(String _memobody) {
        this._memobody = _memobody;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this._id);
        dest.writeString(this._memobody);
        dest.writeString(this.address);
        dest.writeString(this.imagePath);
    }

    protected Memos(Parcel in) {
        this._id = in.readInt();
        this._memobody = in.readString();
        this.address = in.readString();
        this.imagePath = in.readString();
    }

    public static final Creator<Memos> CREATOR = new Creator<Memos>() {
        public Memos createFromParcel(Parcel source) {
            return new Memos(source);
        }

        public Memos[] newArray(int size) {
            return new Memos[size];
        }
    };
}
