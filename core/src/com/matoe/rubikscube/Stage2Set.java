package com.matoe.rubikscube;

import java.util.Collection;
import java.util.TreeSet;

public class Stage2Set {
    TreeSet<Stage2Cube> solutionSet;

    public TreeSet<Stage2Cube> getSolutionSet() {
        return solutionSet;
    }
    public void add(Stage2Cube cube){
        solutionSet.add(cube);
    }
    public Stage2Set(){
        solutionSet = new TreeSet<>(new Stage2Compare());
    }
    public Stage2Set(Collection<Stage2Cube> set){
        solutionSet = new TreeSet<>(new Stage2Compare());
        solutionSet.addAll(set);
    }
    public void addAll(Stage2Set set){
        solutionSet.addAll(set.getSolutionSet());
    }
    public void remove(Stage2Cube cube){
        solutionSet.remove(cube);
    }
    public Stage2Cube getBest(){
        return solutionSet.first();
    }
    @Override
    public String toString(){
        return solutionSet.toString();
    }
}
