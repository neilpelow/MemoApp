package com.example.neilpelow.memoapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Memos implements Parcelable {

    private int _id;
    private String _memoname;

    public Memos() {
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_memoname() {
        return _memoname;
    }

    public void set_memoname(String _memoname) {
        this._memoname = _memoname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this._id);
        dest.writeString(this._memoname);
    }

    protected Memos(Parcel in) {
        this._id = in.readInt();
        this._memoname = in.readString();
    }

    public static final Parcelable.Creator<Memos> CREATOR = new Parcelable.Creator<Memos>() {
        public Memos createFromParcel(Parcel source) {
            return new Memos(source);
        }

        public Memos[] newArray(int size) {
            return new Memos[size];
        }
    };
}
