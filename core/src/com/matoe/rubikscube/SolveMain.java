package com.matoe.rubikscube;
import java.util.*;

public class SolveMain {
    public static ArrayList<Move> spliceSolutions(ArrayList<Move> solution1, ArrayList<Move> solution2){
        if(solution1.size() == 0) {
            return solution2;
        }
        if(solution2.size() == 0){
            return solution1;
        }
        if(solution1.get(solution1.size()-1).family == solution2.get(0).family){
            solution2.remove(0);
            solution1.set(solution1.size()-1, Move.getInverse(solution1.get(solution1.size()-1)));
        }
        solution1.addAll(solution2);
        return solution1;
    }

    //naive solving algorithm that does a basic breadth first search to a RubiksCube object
    public static ArrayList<Move> naiveSolve(RubiksCube cube){
        //uses basic depth first search

        TreeSet<NaiveCubePath> solutions = new TreeSet<>(new NaiveCompare());
        ArrayList<NaiveCubePath> expansion = new ArrayList<>();
        RubiksCube first = new RubiksCube();
        first.setTo(cube);
        solutions.add(new NaiveCubePath(cube));
        while(!solutions.first().cube.isSolved()){
            expansion.clear();
            for(Move m: Move.expansionMoves){
                expansion.add(new NaiveCubePath(solutions.first(), m));
            }
            solutions.remove(solutions.first());
            solutions.addAll(expansion);
        }
        return solutions.first().path;
    }

    //2 stage algorithm using kociemba's algorithm without precomputation
    public static ArrayList<Move> stage1Solve(RubiksCube cube){
        long time = System.currentTimeMillis();

        Stage1Cube stage1 = cube.getFirstReduction();
        Stage1Set solutions1 = new Stage1Set();
        solutions1.add(stage1);
        Stage1Set expansion1;
        while(!solutions1.getBest().isSolved()){
            expansion1 = solutions1.getBest().expand();
            solutions1.remove(solutions1.getBest());
            solutions1.addAll(expansion1);
        }

        return solutions1.getBest().getMoves();
    }
    public static ArrayList<Move> stage2Solve(RubiksCube cube){
        long time = System.currentTimeMillis();

        Stage2Cube stage2 = cube.getSecondReduction();
        Stage2Set solutions2 = new Stage2Set();
        solutions2.add(stage2);
        Stage2Set expansion2;

        while(!solutions2.getBest().isSolved()){
            expansion2 = solutions2.getBest().expand();
            solutions2.remove(solutions2.getBest());
            solutions2.addAll(expansion2);
        }
        System.out.println(System.currentTimeMillis()-time);

        return solutions2.getBest().getMoves();
    }
    public static ArrayList<Move> solveCube(RubiksCube cube){

        MoveHelper.orientCube(cube);

        ArrayList<Move> solution1 = stage1Solve(cube);

        RubiksCube clone = new RubiksCube();
        clone.setTo(cube);
        clone.doAlgorithm(solution1);

        ArrayList<Move> solution2 = stage2Solve(clone);

        return spliceSolutions(solution1, solution2);
    }

    //2 stage algorithm using kociemba's algorithm and breadth first search precomputation
    public static HashMap<Integer, Integer> precomputed1 = new HashMap<>();
    public static void computeTableP1(){
        Stage1Cube solved = new Stage1Cube();

        HashSet<Stage1Cube> holder = new HashSet<>();
        holder.add(solved);
        HashSet<Stage1Cube> temp = new HashSet<>();
        int expansionlevel = 5;
        for(int i = 0; i<expansionlevel; i++){
            for(Stage1Cube c:holder){
                temp.addAll(c.expandToList());
            }
            holder.addAll(temp);
        }

        for(Stage1Cube c: holder){
            precomputed1.put(c.hashCode(), c.getMoves().size());
        }
    }
    public static ArrayList<Move> stage1SolvePreCompute(RubiksCube cube){
        if(precomputed1.isEmpty()) {
            computeTableP1();
        }

        Stage1Cube stage1 = cube.getFirstReduction();
        Stage1Set solutions1 = new Stage1Set();
        solutions1.add(stage1);
        Stage1Set expansion1;
        while(!solutions1.getBest().isSolved()){
            expansion1 = solutions1.getBest().expand();
            solutions1.remove(solutions1.getBest());
            solutions1.addAll(expansion1);
        }

        ArrayList<Move> ret = solutions1.getBest().getMoves();
        solutions1 = null;
        return ret;
    }
    public static HashMap<Integer,Integer> precomputed2 = new HashMap<>();
    public static void computeTableP2(){
        Stage2Cube solved = new Stage2Cube();

        HashSet<Stage2Cube> holder = new HashSet<>();
        holder.add(solved);
        HashSet<Stage2Cube> temp = new HashSet<>();
        int expansionlevel = 7;
        for(int i = 0; i<expansionlevel; i++){
            for(Stage2Cube c:holder){
                temp.addAll(c.expandToList());
            }
            holder.addAll(temp);
        }

        for(Stage2Cube c: holder){
            precomputed2.put(c.hashCode(), c.getMoves().size());
        }
    }
    public static ArrayList<Move> stage2SolvePreCompute(RubiksCube cube){

        if(precomputed2.isEmpty()){
            computeTableP2();
        }

        Stage2Cube stage2 = cube.getSecondReduction();
        Stage2Set solutions2 = new Stage2Set();
        solutions2.add(stage2);
        Stage2Set expansion2;

        while(!solutions2.getBest().isSolved()){
            expansion2 = solutions2.getBest().expand();
            solutions2.remove(solutions2.getBest());
            solutions2.addAll(expansion2);
        }

        ArrayList<Move> ret = solutions2.getBest().getMoves();
        solutions2 = null;
        return ret;
    }
    public static ArrayList<Move> solveCubePreCompute(RubiksCube cube){
        MoveHelper.orientCube(cube);

        ArrayList<Move> solution1 = stage1SolvePreCompute(cube);

        RubiksCube clone = new RubiksCube();
        clone.setTo(cube);
        clone.doAlgorithm(solution1);

        ArrayList<Move> solution2 = stage2SolvePreCompute(clone);

        return spliceSolutions(solution1, solution2);
    }
}
