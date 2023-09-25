package com.matoe.rubikscube;

import java.util.ArrayList;

public class NaiveCubePath {
    public RubiksCube cube;
    public ArrayList<Move> path;

     NaiveCubePath(RubiksCube cube){
        this.cube = cube;
        this.path = new ArrayList<>();
    }
    NaiveCubePath(NaiveCubePath path, Move m){
        this.cube = new RubiksCube();
        this.cube.setTo(path.cube);
        this.cube.move(m);
        this.path = new ArrayList<>(path.path);
        this.path.add(m);
    }
    public String toString(){
         return path.toString();
    }
}
