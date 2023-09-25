package com.matoe.rubikscube;

import java.util.Comparator;

public class Stage2Compare implements Comparator<Stage2Cube> {
    @Override
    public int compare(Stage2Cube c1, Stage2Cube c2){
        int c1R = c1.getRating();
        int c2R = c2.getRating();
        if(c1.getMoves().size() > 20){
            return 0;
        }
        if(c2.getMoves().size()> 20){
            return 0;
        }
        if((c1R) == (c2R)){
            if(c1.equals(c2)){
                return 0;
            }
            return -1;
        }else {
            return (c1R) - (c2R);
        }
    }
}
