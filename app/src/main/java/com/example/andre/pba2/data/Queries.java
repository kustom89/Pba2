package com.example.andre.pba2.data;

import com.example.andre.pba2.models.PendingFood;

import java.util.ArrayList;
import java.util.List;

public class Queries {

    public List<PendingFood> pendingFoods(){
        List<PendingFood> pendingFoods=new ArrayList<>();
        List<PendingFood> pendingFoodList=PendingFood.find(PendingFood.class, "done=0");
        if(pendingFoodList!=null && pendingFoodList.size()>0){
            pendingFoods.addAll( pendingFoodList);
        }return pendingFoods;
    }
}
