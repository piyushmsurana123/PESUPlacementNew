package com.placement.pesu.pesuplacement;


import java.util.ArrayList;
import java.util.Hashtable;
import android.os.Parcelable;
import android.os.Parcel;
import java.util.Set;


public class ChosenFilters  implements Parcelable{

    Hashtable<String, ArrayList<String>> cFilters = new Hashtable<String, ArrayList<String>>();

    public ChosenFilters(ArrayList<String> keysList){
        for(int i=0;i<keysList.size();i++){
            this.cFilters.put(keysList.get(i), new ArrayList<String>());
        }
    }
    public ChosenFilters(Parcel in) {
        this.cFilters = (Hashtable<String, ArrayList<String>>) in.readSerializable();
    }
    public void addElement(String key, String value){
        ArrayList valueList=this.cFilters.get(key);
        valueList.add(value);
        this.cFilters.put(key,valueList);
    }
    public void removeElement(String key, String value){
        ArrayList valueList=this.cFilters.get(key);
        valueList.remove(value);
        this.cFilters.put(key,valueList);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.cFilters);
    }

    // This is to de-serialize the object
    public static final Parcelable.Creator<ChosenFilters> CREATOR = new Parcelable.Creator<ChosenFilters>(){
        public ChosenFilters createFromParcel(Parcel in) {
            return new ChosenFilters(in);
        }
        public ChosenFilters[] newArray(int size) {
            return new ChosenFilters[size];
        }
    };
    @Override
    public int describeContents() {return 0;}

    public String MakeString(){
        String result="";
        Set<String> keys = this.cFilters.keySet();
        for(String key: keys){
            ArrayList valueList=this.cFilters.get(key);
            if(valueList.size()>0){
                result=result+" "+key;
                for(int i=0;i<valueList.size();i++) {
                    String val=valueList.get(i).toString();

                    result = result + " " + val+" "+val.length();
                }
                result=result+"\n";
            }

        }
        return result;

    }
}
