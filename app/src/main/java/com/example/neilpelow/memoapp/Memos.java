package com.example.neilpelow.memoapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Memos implements Parcelable {

    private int _id;
    private String _memobody;

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

    public void set_memobody(String _memoname) {
        this._memobody = _memoname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this._id);
        dest.writeString(this._memobody);
    }

    protected Memos(Parcel in) {
        this._id = in.readInt();
        this._memobody = in.readString();
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
