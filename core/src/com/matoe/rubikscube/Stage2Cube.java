package com.matoe.rubikscube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Objects;

public class Stage2Cube {
    private byte[] cornerPerm;
    public byte[] getCornerPerm() {
        return cornerPerm;
    }
    public void setCornerPerm(byte[] cornerPerm) {
        this.cornerPerm = cornerPerm;
    }

    private byte[] edgePerm;
    public byte[] getEdgePerm() {
        return edgePerm;
    }
    public void setEdgePerm(byte[] edgePerm) {
        this.edgePerm = edgePerm;
    }

    private byte[] eQPerm;
    public byte[] geteQPerm() {
        return eQPerm;
    }
    public void seteQPerm(byte[] eQPerm) {
        this.eQPerm = eQPerm;
    }
    private ArrayList<Move> moves;
    public ArrayList<Move> getMoves() {
        return moves;
    }
    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }
    byte heuristic;
    public void updateHeuristic(){
        heuristic = 0;
        for(byte b: cornerPerm){
            heuristic += Math.abs(b);
        }
        for(byte b: eQPerm){
            heuristic += Math.abs(b);
        }
        for(byte b:edgePerm){
            heuristic += Math.abs(b);
        }
        heuristic = (byte)Math.ceil(heuristic/4d);
    }
    public int getRating(){
        if(isSolved()){
            return 0;
        }if(SolveMain.precomputed2.containsKey(hashCode())){
            return SolveMain.precomputed2.get(hashCode());
        }
        return heuristic + getMoves().size();
    }

    public boolean isSolved(){
        return heuristic == 0;
    }
    public Stage2Set expand(){
        Stage2Set ret = new Stage2Set();
        boolean excludeAxis = false;
        int lastFamily = -1;
        if(moves.size()>1) {
            lastFamily = moves.get(moves.size() - 1).family;
            excludeAxis = moves.get(moves.size() - 2).family / 2 == moves.get(moves.size() - 1).family / 2;
            }
        else if(moves.size()>0) {
            lastFamily = moves.get(0).family;
        }
        for (Move m : Move.stage2Moves) {
            if ((moves.size() > 0) && (lastFamily == m.family)) {
            } else if (moves.size() > 1 && excludeAxis
                && lastFamily/2 == m.family / 2) {
            } else {
                Stage2Cube c = move(m);
                if(c.getRating()<20) {
                    ret.add(c);
                }
            }
        }

        return ret;
    }
    public ArrayList<Stage2Cube> expandToList(){
        ArrayList<Stage2Cube> ret = new ArrayList<>();
        if(getRating()<20) {
            boolean excludeAxis = false;
            int lastFamily = -1;

            if(moves.size()>1) {
                lastFamily = moves.get(moves.size() - 1).family;
                excludeAxis = moves.get(moves.size() - 2).family / 2 == moves.get(moves.size() - 1).family / 2;
            }
            else if(moves.size()>0) {
                lastFamily = moves.get(0).family;
            }

            for (Move m : Move.stage2Moves) {
                if ((moves.size() > 0) && (lastFamily == m.family)) {
                } else if (moves.size() > 1 && excludeAxis
                        && lastFamily/2 == m.family / 2) {
                } else {
                    ret.add(this.move(m));
                }
            }
        }
        return ret;
    }
    public Stage2Cube move(Move m){
        Stage2Cube ret = null;
        switch(m){
            case U:
                ret = MoveHelper.moveU(this);
                break;
            case UPRIME:
                ret = MoveHelper.moveUp(this);
                break;
            case U2:
                ret = MoveHelper.moveU2(this);
                break;
            case D:
                ret = MoveHelper.moveD(this);
                break;
            case DPRIME:
                ret = MoveHelper.moveDp(this);
                break;
            case D2:
                ret = MoveHelper.moveD2(this);
                break;
            case B2:
                ret = MoveHelper.moveB2(this);
                break;
            case F2:
                ret = MoveHelper.moveF2(this);
                break;
            case L2:
                ret = MoveHelper.moveL2(this);
                break;
            case R2:
                ret = MoveHelper.moveR2(this);
                break;
        }
        ret.moves.addAll(moves);
        ret.moves.add(m);
        return ret;
    }

    @Override
    public int hashCode(){
    return Objects.hash(Arrays.hashCode(edgePerm), Arrays.hashCode(eQPerm), Arrays.hashCode(cornerPerm));
    }

    Stage2Cube(){
        //creates a solved cube
        cornerPerm = new byte[]{0,0,0,0,0,0,0,0};
        edgePerm = new byte[]{0,0,0,0,0,0,0,0};
        eQPerm = new byte[]{0,0,0,0};
        heuristic = 0;
        moves = new ArrayList<>();
    }
    Stage2Cube(byte[] cP, byte[] eP, byte[] qP){
        cornerPerm = cP;
        edgePerm = eP;
        eQPerm = qP;
        moves = new ArrayList<>();
        updateHeuristic();
    }
    @Override
    public String toString(){
        String ret = "(";
        int i = 0;
        for(byte b:getCornerPerm()){
            ret += i-b;
            i++;
        }
        ret += ", ";
        i = 0;
        for(byte b:getEdgePerm()){
            ret += i-b;
            i++;
        }
        ret += ", ";
        i = 0;
        for(byte b:geteQPerm()){
            ret += i-b;
            i++;
        }
        ret = ret + ", " + moves +  ")";
        return ret;
    }
    public boolean equals(Object other){
        if(other instanceof Stage2Cube){
            for(int i = 0; i<8; i++){
                if(getCornerPerm()[i] != ((Stage2Cube)other).getCornerPerm()[i]){
                    return false;
                }
                if(getEdgePerm()[i] != ((Stage2Cube)other).getEdgePerm()[i]){
                    return false;
                }
            }
            for(int i = 0; i<4; i++){
                if(geteQPerm()[i] != ((Stage2Cube)other).geteQPerm()[i]){
                    return false;
                }
                return true;
            }
        }return false;
    }
}
