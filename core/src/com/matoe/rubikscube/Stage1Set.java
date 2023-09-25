package com.matoe.rubikscube;

import java.util.Collection;
import java.util.TreeSet;

public class Stage1Set {
    TreeSet<Stage1Cube> solutionSet;

    public TreeSet<Stage1Cube> getSolutionSet() {
        return solutionSet;
    }
    public void add(Stage1Cube cube){
        solutionSet.add(cube);
    }
    public Stage1Set(){
        solutionSet = new TreeSet<>(new Stage1Compare());
    }
    public Stage1Set(Collection<Stage1Cube> set){
        solutionSet = new TreeSet<>(new Stage1Compare());
        solutionSet.addAll(set);
    }
    public void addAll(Stage1Set set){
        solutionSet.addAll(set.getSolutionSet());
    }
    public void remove(Stage1Cube cube){
        solutionSet.remove(cube);
    }
    public Stage1Cube getBest(){
        return solutionSet.first();
    }
    @Override
    public String toString(){
        return solutionSet.toString();
    }
}
