package com.example.neilpelow.memoapp;

/**
 * Created by neilpelow on 14/11/15.
 */
public class Memos {

    private int _id;
    private String _memoname;

    public Memos() {

    }


    public Memos(String memoname) {
        this._memoname = memoname;
    }

    public void set_id(int _id){
        this._id = _id;

    }

    public void set_memoname(String _memoname) {
        this._memoname = _memoname;
    }

    public int get_id(){
        return _id;

    }

    public String get_memoname(){
        return _memoname;

    }


}
