package com.matoe.rubikscube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Objects;

public class Stage1Cube{
    private byte[] cornerRot;
    public byte[] getCornerRot() {
        return cornerRot;
    }
    private BitSet edgeOri;
    public BitSet getEdgeOri() {
        return edgeOri;
    }

    private BitSet eqEdgePerm;
    public BitSet getEqEdgePerm() {
        return eqEdgePerm;
    }

    private ArrayList<Move> moves;
    public ArrayList<Move> getMoves() {
        return moves;
    }
    private byte heuristic;
    public void updateHeuristic(){
        int cRating = 0;
        for(int i = 0; i<8; i++){
            cRating += cornerRot[i];
        }
        int eRating = edgeOri.cardinality();

        int qRating = 0;
        for(int i = 4; i<8; i++){
            if(!eqEdgePerm.get(i)){
                qRating++;
            }
        }
        heuristic = (byte) Math.ceil(.3f*(cRating + eRating + qRating));
    }

    public int getRating(){
        if(isSolved()){
            return 0;
        }if(SolveMain.precomputed1.containsKey(hashCode())){
            return SolveMain.precomputed1.get(hashCode());
        }
        return heuristic + getMoves().size();
    }

    public boolean isSolved(){
        return heuristic == 0;
    }
    public ArrayList<Stage1Cube> expandToList(){
        ArrayList<Stage1Cube> ret = new ArrayList<>();
        if(getRating()<16) {
            boolean excludeAxis = false;
            int lastFamily = -1;

            if(moves.size()>1) {
                lastFamily = moves.get(moves.size() - 1).family;
                excludeAxis = moves.get(moves.size() - 2).family / 2 == moves.get(moves.size() - 1).family / 2;
            }
            else if(moves.size()>0) {
                lastFamily = moves.get(0).family;
            }


            for (Move m : Move.expansionMoves) {
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
    public Stage1Set expand(){
        Stage1Set ret = new Stage1Set();
        if(getRating()<12) {
            boolean excludeAxis = false;
            int lastFamily = -1;

            if(moves.size()>1) {
                lastFamily = moves.get(moves.size() - 1).family;
                excludeAxis = moves.get(moves.size() - 2).family / 2 == moves.get(moves.size() - 1).family / 2;
            }
            else if(moves.size()>0) {
                lastFamily = moves.get(0).family;
            }

            for (Move m : Move.expansionMoves) {
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
    public Stage1Cube move(Move m){
        Stage1Cube ret = null;
        switch(m){
            case R:
                ret = MoveHelper.moveR(this);
                break;
            case RPRIME:
                ret = MoveHelper.moveRp(this);
                break;
            case R2:
                ret = MoveHelper.moveR2(this);
                break;
            case L:
                ret = MoveHelper.moveL(this);
                break;
            case LPRIME:
                ret = MoveHelper.moveLp(this);
                break;
            case L2:
                ret = MoveHelper.moveL2(this);
                break;
            case F:
                ret = MoveHelper.moveF(this);
                break;
            case FPRIME:
                ret = MoveHelper.moveFp(this);
                break;
            case F2:
                ret = MoveHelper.moveF2(this);
                break;
            case B:
                ret = MoveHelper.moveB(this);
                break;
            case BPRIME:
                ret = MoveHelper.moveBp(this);
                break;
            case B2:
                ret = MoveHelper.moveB2(this);
                break;
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
        }
        ret.moves.add(m);
        return ret;
    }
    @Override
    public int hashCode(){
        return Objects.hash(Arrays.hashCode(cornerRot), edgeOri, eqEdgePerm);
    }

    Stage1Cube(byte[] cR, BitSet eO, BitSet eEP, ArrayList<Move> list){
        cornerRot = cR;
        edgeOri = eO;
        eqEdgePerm = eEP;
        moves = new ArrayList<>(list);
        updateHeuristic();
    }
    Stage1Cube(byte[] cR, BitSet eO, BitSet eEP){
        cornerRot = cR;
        edgeOri = eO;
        eqEdgePerm = eEP;
        moves = new ArrayList<>();
        updateHeuristic();
    }
    Stage1Cube(){
        //creates a solved cube
        cornerRot = new byte[]{0,0,0,0,0,0,0,0};
        edgeOri = new BitSet(12);
        eqEdgePerm = new BitSet(12);
        eqEdgePerm.set(4,8);
        moves = new ArrayList<>();
    }
    @Override
    public String toString(){
        String ret = "(";
        for(byte b: getCornerRot()) {
            ret += b;
        }
        ret = ret + ", " + edgeOri + ", " + eqEdgePerm + ", " + moves + ")";
        return ret;
    }

    public boolean equals(Object other){
        if(other instanceof Stage1Cube){
            if(!((Stage1Cube)other).getEqEdgePerm().equals(getEqEdgePerm())){
                return false;
            }if(!((Stage1Cube)other).getEdgeOri().equals(getEdgeOri())){
                return false;
            }
            for(int i = 0; i<8; i++){
                if(((Stage1Cube)other).getCornerRot()[i] != getCornerRot()[i]){
                    return false;
                }
            }
            return true;
        }return false;
    }
    /*public boolean isSymmetricTo(Stage1Cube other){
        if(isD02SymmetricTo(other)){
            return true;
        }
        if(isD13SymmetricTo(other)){
            return true;
        }
        if(isMSymmetricTo(other)){
            return true;
        }
        if(isESymmetricTo(other)){
            return true;
        }
        if(isSSymmetricTo(other)){
            return true;
        }
        return false;
    }
    public static byte[] mCornerSymmetry = {1,0,3,2,5,4,7,6};
    public static byte[] mEdgeSymmetry = {0,3,2,1,5,4,7,6,8,11,10,9};
    public boolean isMSymmetricTo(Stage1Cube other){
        for(int i = 0; i<8; i++){
            if((2*getCornerRot()[i])%3 != other.getCornerRot()[mCornerSymmetry[i]]){
                return false;
            }
        }
        for(int i = 0; i<12; i++){
            if(getEqEdgePerm().get(i) != other.getEqEdgePerm().get(mEdgeSymmetry[i])){
                return false;
            }
            if(getEdgeOri().get(i) != other.getEdgeOri().get(mEdgeSymmetry[i])){
                return false;
            }
        }
        return true;
    }

//    public static byte[] eCornerSymmetry = {}; ecorner symmetry is simply the original order backwards,
//    faster to use 7-i than locate this array
    public static byte[] eEdgeSymmetry = {10, 9, 8, 11, 4, 5, 6, 7, 2, 1, 0, 3};
    public boolean isESymmetricTo(Stage1Cube other){
        for(int i = 0; i<8; i++){
            if((2*getCornerRot()[i])%3 != other.getCornerRot()[7-i]){
                return false;
            }
        }
        for(int i = 0; i<12; i++){
            if(getEqEdgePerm().get(i) != other.getEqEdgePerm().get(eEdgeSymmetry[i])){
                return false;
            }
            if(getEdgeOri().get(i) != other.getEdgeOri().get(eEdgeSymmetry[i])){
                return false;
            }
        }
        return true;
    }

    public static byte[] sCornerSymmetry = {3,2,1,0,7,6,5,4};
    public static byte[] sEdgeSymmetry = {2,1,0,3,7,6,5,4,10,9,8,11};
    public boolean isSSymmetricTo(Stage1Cube other){
        for(int i = 0; i<8; i++){
            if((2*getCornerRot()[i])%3 != other.getCornerRot()[sCornerSymmetry[i]]){
                return false;
            }
        }
        for(int i = 0; i<12; i++){
            if(getEqEdgePerm().get(i) != other.getEqEdgePerm().get(sEdgeSymmetry[i])){
                return false;
            }
            if(getEdgeOri().get(i) != other.getEdgeOri().get(sEdgeSymmetry[i])){
                return false;
            }
        }
        return true;
    }

    public static byte[] d02CornerSymmetry = {0,3,2,1,6,5,4,7};
    public static byte[] d02EdgeSymmetry = {3,2,1,0,6,5,4,7,9,8,11,10};
    public boolean isD02SymmetricTo(Stage1Cube other){
        for(int i = 0; i<8; i++){
            if((2*getCornerRot()[i])%3 != other.getCornerRot()[d02CornerSymmetry[i]]){
                return false;
            }
        }
        int i;
        for(i = 0; i<4; i++){
            if(getEqEdgePerm().get(i) != other.getEqEdgePerm().get(d02EdgeSymmetry[i])){
                return false;
            }
            if(getEdgeOri().get(i) != other.getEdgeOri().get(d02EdgeSymmetry[i])){
                return false;
            }
        }
        for(i = i; i<8; i++){
            if(getEqEdgePerm().get(i) != other.getEqEdgePerm().get(d02EdgeSymmetry[i])){
                return false;
            }
            if(getEdgeOri().get(i) == other.getEdgeOri().get(d02EdgeSymmetry[i])){
                return false;
            }
        }
        for(i = i; i<12; i++){
            if(getEqEdgePerm().get(i) != other.getEqEdgePerm().get(d02EdgeSymmetry[i])){
                return false;
            }
            if(getEdgeOri().get(i) != other.getEdgeOri().get(d02EdgeSymmetry[i])){
                return false;
            }
        }
        return true;
    }
    public static byte[] d13CornerSymmetry = {2,1,0,3,4,7,6,5};
    public static byte[] d13EdgeSymmetry = {1,0,3,2,4,7,6,5,11,10,9,8};
    public boolean isD13SymmetricTo(Stage1Cube other){
        for(int i = 0; i<8; i++){
            if((2*getCornerRot()[i])%3 != other.getCornerRot()[d13CornerSymmetry[i]]){
                return false;
            }
        }
        int i;
        for(i = 0; i<4; i++){
            if(getEqEdgePerm().get(i) != other.getEqEdgePerm().get(d13EdgeSymmetry[i])){
                return false;
            }
            if(getEdgeOri().get(i) != other.getEdgeOri().get(d13EdgeSymmetry[i])){
                return false;
            }
        }
        for(i = i; i<8; i++){
            if(getEqEdgePerm().get(i) != other.getEqEdgePerm().get(d13EdgeSymmetry[i])){
                return false;
            }
            if(getEdgeOri().get(i) == other.getEdgeOri().get(d13EdgeSymmetry[i])){
                return false;
            }
        }
        for(i = i; i<12; i++){
            if(getEqEdgePerm().get(i) != other.getEqEdgePerm().get(d13EdgeSymmetry[i])){
                return false;
            }
            if(getEdgeOri().get(i) != other.getEdgeOri().get(d13EdgeSymmetry[i])){
                return false;
            }
        }
        return true;
    }*/ //Symmetries


}
