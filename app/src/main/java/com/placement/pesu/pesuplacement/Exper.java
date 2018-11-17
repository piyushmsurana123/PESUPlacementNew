package com.placement.pesu.pesuplacement;

import android.os.Parcelable;
import android.os.Parcel;

public class Exper implements Parcelable {

    String experText;
    String experTitle;

    public Exper(Parcel in) {
        experText = in.readString();
        experTitle = in.readString();
    }
    public Exper(String experText,String experTitle){
        this.experText=experText;
        this.experTitle=experTitle;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
// Write data in any order
        dest.writeString(experText);
        dest.writeString(experTitle);
    }

    // This is to de-serialize the object
    public static final Parcelable.Creator<Exper> CREATOR = new Parcelable.Creator<Exper>(){
        public Exper createFromParcel(Parcel in) {
            return new Exper(in);
        }
        public Exper[] newArray(int size) {
            return new Exper[size];
        }
    };
    @Override
    public int describeContents() {return 0;}

}
