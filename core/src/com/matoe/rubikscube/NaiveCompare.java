package com.matoe.rubikscube;

import java.util.Comparator;

class NaiveCompare implements Comparator<NaiveCubePath> {
    public int compare(NaiveCubePath o1, NaiveCubePath o2){
        if(o1.cube.isSolved()){
            return -1;
        }else if(o2.cube.isSolved()){
            return 1;
        }
        if(o1.path.equals(o2.path)) {
            return 0;
        }
        if(o1.path.size() - o2.path.size() != 0) {
            return o1.path.size() - o2.path.size();
        }
        return -1;
    }
}
