package com.placement.pesu.pesuplacement;

import java.util.ArrayList;
import java.util.List;

public class ExperList {

    List<Exper> eList=new ArrayList<Exper>();

    public void add (String item){
        Exper e = new Exper(item,item.substring(0,50)+"....");
        this.eList.add(e);
    }

}
