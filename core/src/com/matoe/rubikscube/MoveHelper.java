package com.matoe.rubikscube;

import java.util.Arrays;
import java.util.BitSet;
import java.util.LinkedList;


public class MoveHelper {

    public static void byte2SwapInd(byte[] toSwap, int ind1, int ind2){
        if(ind1 == ind2){
            throw new RuntimeException("Equal b3Swap entries!");
        }
        byte target1 = (byte) (ind1 - toSwap[ind1]);
        byte target2 = (byte) (ind2 - toSwap[ind2]);

        toSwap[ind2] = (byte) (ind2 - target1);
        toSwap[ind1] = (byte) (ind1 - target2);
    }
    public static void byteSwapInd(byte[] toSwap, int ind1, int ind2){
        if(toSwap[ind1] == toSwap[ind2]){
            return;
        }
        byte temp = toSwap[ind1];
        toSwap[ind1] = toSwap[ind2];
        toSwap[ind2] = temp;
    }
    public static void byteRotInd(byte[] toRotate, int ind, int amount){
        toRotate[ind] = (byte)((toRotate[ind] + amount)%3);
    }
    public static void bitFlipInd(BitSet toFlip, int ind){
        toFlip.flip(ind);
    }
    public static void bitSwapInd(BitSet toSwap, int ind1, int ind2){
        if(toSwap.get(ind1) != toSwap.get(ind2)){
            toSwap.flip(ind1);
            toSwap.flip(ind2);
        }
    }


    public static Stage2Cube moveU(Stage2Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerPerm(), 8);
        byte[] eO = Arrays.copyOf(cube.getEdgePerm(), 8);
        byte[] eQPer = Arrays.copyOf(cube.geteQPerm(),4);

        int[] edgeCycle = {3,2,1,0};
        int[] cornerCycle = {3,2,1,0};

        for(int i = 0; i<3; i++){
            byte2SwapInd(cRot, cornerCycle[i], cornerCycle[i+1]);
            byte2SwapInd(eO, edgeCycle[i], edgeCycle[i+1]);
        }
        return new Stage2Cube(cRot, eO, eQPer);
    }
    public static Stage2Cube moveUp(Stage2Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerPerm(), 8);
        byte[] eO = Arrays.copyOf(cube.getEdgePerm(), 8);
        byte[] eQPer = Arrays.copyOf(cube.geteQPerm(),4);

        int[] edgeCycle = {3,2,1,0};
        int[] cornerCycle = {3,2,1,0};

        for(int i = 3; i>0; i--){
            byte2SwapInd(cRot, cornerCycle[i], cornerCycle[i-1]);
            byte2SwapInd(eO, edgeCycle[i], edgeCycle[i-1]);
        }
        return new Stage2Cube(cRot, eO, eQPer);
    }
    public static Stage2Cube moveU2(Stage2Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerPerm(), 8);
        byte[] eO = Arrays.copyOf(cube.getEdgePerm(), 8);
        byte[] eQPer = Arrays.copyOf(cube.geteQPerm(),4);

        int[] edgeCycle = {3,2,1,0};
        int[] cornerCycle = {3,2,1,0};

        for(int i = 0; i<2; i++){
            byte2SwapInd(cRot, cornerCycle[i], cornerCycle[i+2]);
            byte2SwapInd(eO, edgeCycle[i], edgeCycle[i+2]);
        }
        return new Stage2Cube(cRot, eO, eQPer);
    }
    public static Stage2Cube moveD(Stage2Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerPerm(), 8);
        byte[] eO = Arrays.copyOf(cube.getEdgePerm(), 8);
        byte[] eQPer = Arrays.copyOf(cube.geteQPerm(),4);

        int[] edgeCycle = {7,6,5,4};
        int[] cornerCycle = {7,6,5,4};

        for(int i = 0; i<3; i++){
            byte2SwapInd(cRot, cornerCycle[i], cornerCycle[i+1]);
            byte2SwapInd(eO, edgeCycle[i], edgeCycle[i+1]);
        }
        return new Stage2Cube(cRot, eO, eQPer);
    }
    public static Stage2Cube moveDp(Stage2Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerPerm(), 8);
        byte[] eO = Arrays.copyOf(cube.getEdgePerm(), 8);
        byte[] eQPer = Arrays.copyOf(cube.geteQPerm(),4);

        int[] edgeCycle = {7,6,5,4};
        int[] cornerCycle = {7,6,5,4};

        for(int i = 3; i>0; i--){
            byte2SwapInd(cRot, cornerCycle[i], cornerCycle[i-1]);
            byte2SwapInd(eO, edgeCycle[i], edgeCycle[i-1]);
        }
        return new Stage2Cube(cRot, eO, eQPer);
    }
    public static Stage2Cube moveD2(Stage2Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerPerm(), 8);
        byte[] eO = Arrays.copyOf(cube.getEdgePerm(), 8);
        byte[] eQPer = Arrays.copyOf(cube.geteQPerm(),4);

        int[] edgeCycle = {7,6,5,4};
        int[] cornerCycle = {7,6,5,4};

        for(int i = 0; i<2; i++){
            byte2SwapInd(cRot, cornerCycle[i], cornerCycle[i+2]);
            byte2SwapInd(eO, edgeCycle[i], edgeCycle[i+2]);
        }
        return new Stage2Cube(cRot, eO, eQPer);
    }

    public static Stage2Cube moveR2(Stage2Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerPerm(), 8);
        byte[] eO = Arrays.copyOf(cube.getEdgePerm(), 8);
        byte[] eQPer = Arrays.copyOf(cube.geteQPerm(),4);

        int[] cornerCycle = {1,2,5,6};

        for(int i = 0; i<2; i++){
            byte2SwapInd(cRot, cornerCycle[i], cornerCycle[i+2]);
        }
        byte2SwapInd(eO, 1, 5);
        byte2SwapInd(eQPer, 1, 2);

        return new Stage2Cube(cRot, eO, eQPer);
    }
    public static Stage2Cube moveL2(Stage2Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerPerm(), 8);
        byte[] eO = Arrays.copyOf(cube.getEdgePerm(), 8);
        byte[] eQPer = Arrays.copyOf(cube.geteQPerm(),4);

        int[] cornerCycle = {3,0,7,4};

        for(int i = 0; i<2; i++){
            byte2SwapInd(cRot, cornerCycle[i], cornerCycle[i+2]);
        }
        byte2SwapInd(eO, 3, 7);
        byte2SwapInd(eQPer, 0, 3);

        return new Stage2Cube(cRot, eO, eQPer);
    }
    public static Stage2Cube moveF2(Stage2Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerPerm(), 8);
        byte[] eO = Arrays.copyOf(cube.getEdgePerm(), 8);
        byte[] eQPer = Arrays.copyOf(cube.geteQPerm(),4);

        int[] cornerCycle = {2,3,4,5};

        for(int i = 0; i<2; i++){
            byte2SwapInd(cRot, cornerCycle[i], cornerCycle[i+2]);
        }
        byte2SwapInd(eO, 2, 4);
        byte2SwapInd(eQPer, 0, 1);

        return new Stage2Cube(cRot, eO, eQPer);
    }
    public static Stage2Cube moveB2(Stage2Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerPerm(), 8);
        byte[] eO = Arrays.copyOf(cube.getEdgePerm(), 8);
        byte[] eQPer = Arrays.copyOf(cube.geteQPerm(),4);

        int[] cornerCycle = {0,1,6,7};

        for(int i = 0; i<2; i++){
            byte2SwapInd(cRot, cornerCycle[i], cornerCycle[i+2]);
        }
        byte2SwapInd(eO, 0,6);
        byte2SwapInd(eQPer, 2,3);
        return new Stage2Cube(cRot, eO, eQPer);
    }


    public static long asBase(int num, int base){
        //converts base 10 number to base n
        long baseNum = 0;
        for(int i = 0; num>0; i++){
            baseNum+= (num%base)*Math.pow(10,i);
            num/=base;
        }
        return baseNum;
    }

    public static Stage1Cube moveR(Stage1Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerRot(), 8);
        BitSet eO = (BitSet) cube.getEdgeOri().clone();
        BitSet eQPer = (BitSet) cube.getEqEdgePerm().clone();

        int[] edgeCycle = {1,5,9,6};
        int[] cornerCycle = {1,2,5,6};

        for(int i = 0; i<3; i++){
            byteSwapInd(cRot, cornerCycle[i], cornerCycle[i+1]);
            bitSwapInd(eO, edgeCycle[i], edgeCycle[i+1]);
            bitSwapInd(eQPer, edgeCycle[i], edgeCycle[i+1]);
        }
        for(int i = 0; i<4; i++) {
            byteRotInd(cRot, cornerCycle[i], ((i) % 2) + 1);
        }


        return new Stage1Cube(cRot, eO, eQPer, cube.getMoves());
    }
    public static Stage1Cube moveRp(Stage1Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerRot(), 8);
        BitSet eO = (BitSet) cube.getEdgeOri().clone();
        BitSet eQPer = (BitSet) cube.getEqEdgePerm().clone();

        int[] edgeCycle = {1,5,9,6};
        int[] cornerCycle = {1,2,5,6};

        for(int i = 3; i>0; i--){
            byteSwapInd(cRot, cornerCycle[i], cornerCycle[i-1]);
            bitSwapInd(eO, edgeCycle[i], edgeCycle[i-1]);
            bitSwapInd(eQPer, edgeCycle[i], edgeCycle[i-1]);
        }
        for(int i = 0; i<4; i++) {
            byteRotInd(cRot, cornerCycle[i], ((i) % 2) + 1);
        }

        return new Stage1Cube(cRot, eO, eQPer, cube.getMoves());
    }
    public static Stage1Cube moveR2(Stage1Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerRot(), 8);
        BitSet eO = (BitSet) cube.getEdgeOri().clone();
        BitSet eQPer = (BitSet) cube.getEqEdgePerm().clone();

        int[] edgeCycle = {1,5,9,6};
        int[] cornerCycle = {1,2,5,6};

        for(int i = 0; i<2; i++){
            byteSwapInd(cRot, cornerCycle[i], cornerCycle[i+2]);
            bitSwapInd(eO, edgeCycle[i], edgeCycle[i+2]);
            bitSwapInd(eQPer, edgeCycle[i], edgeCycle[i+2]);
        }

        return new Stage1Cube(cRot, eO, eQPer, cube.getMoves());
    }

    public static Stage1Cube moveL(Stage1Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerRot(), 8);
        BitSet eO = (BitSet) cube.getEdgeOri().clone();
        BitSet eQPer = (BitSet) cube.getEqEdgePerm().clone();

        int[] edgeCycle = {3,7,11,4};
        int[] cornerCycle = {3,0,7,4};

        for(int i = 0; i<3; i++){
            byteSwapInd(cRot, cornerCycle[i], cornerCycle[i+1]);
            bitSwapInd(eO, edgeCycle[i], edgeCycle[i+1]);
            bitSwapInd(eQPer, edgeCycle[i], edgeCycle[i+1]);
        }
        for(int i = 0; i<4; i++) {
            byteRotInd(cRot, cornerCycle[i], ((i) % 2) + 1);
        }


        return new Stage1Cube(cRot, eO, eQPer, cube.getMoves());
    }
    public static Stage1Cube moveLp(Stage1Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerRot(), 8);
        BitSet eO = (BitSet) cube.getEdgeOri().clone();
        BitSet eQPer = (BitSet) cube.getEqEdgePerm().clone();

        int[] edgeCycle = {3,7,11,4};
        int[] cornerCycle = {3,0,7,4};

        for(int i = 3; i>0; i--){
            byteSwapInd(cRot, cornerCycle[i], cornerCycle[i-1]);
            bitSwapInd(eO, edgeCycle[i], edgeCycle[i-1]);
            bitSwapInd(eQPer, edgeCycle[i], edgeCycle[i-1]);
        }
        for(int i = 0; i<4; i++) {
            byteRotInd(cRot, cornerCycle[i], ((i) % 2) + 1);
        }
        return new Stage1Cube(cRot, eO, eQPer, cube.getMoves());
    }
    public static Stage1Cube moveL2(Stage1Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerRot(), 8);
        BitSet eO = (BitSet) cube.getEdgeOri().clone();
        BitSet eQPer = (BitSet) cube.getEqEdgePerm().clone();

        int[] edgeCycle = {3,7,11,4};
        int[] cornerCycle = {3,0,7,4};

        for(int i = 0; i<2; i++){
            byteSwapInd(cRot, cornerCycle[i], cornerCycle[i+2]);
            bitSwapInd(eO, edgeCycle[i], edgeCycle[i+2]);
            bitSwapInd(eQPer, edgeCycle[i], edgeCycle[i+2]);
        }
        return new Stage1Cube(cRot, eO, eQPer, cube.getMoves());
    }

    public static Stage1Cube moveF(Stage1Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerRot(), 8);
        BitSet eO = (BitSet) cube.getEdgeOri().clone();
        BitSet eQPer = (BitSet) cube.getEqEdgePerm().clone();

        int[] edgeCycle = {2,4,8,5};
        int[] cornerCycle = {2,3,4,5};

        for(int i = 0; i<3; i++){
            byteSwapInd(cRot, cornerCycle[i], cornerCycle[i+1]);
            bitSwapInd(eO, edgeCycle[i], edgeCycle[i+1]);
            bitSwapInd(eQPer, edgeCycle[i], edgeCycle[i+1]);
        }
        for(int i = 0; i<4; i++) {
            byteRotInd(cRot, cornerCycle[i], ((i) % 2) + 1);
            bitFlipInd(eO, edgeCycle[i]);
        }

        return new Stage1Cube(cRot, eO, eQPer, cube.getMoves());
    }
    public static Stage1Cube moveFp(Stage1Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerRot(), 8);
        BitSet eO = (BitSet) cube.getEdgeOri().clone();
        BitSet eQPer = (BitSet) cube.getEqEdgePerm().clone();

        int[] edgeCycle = {2,4,8,5};
        int[] cornerCycle = {2,3,4,5};

        for(int i = 3; i>0; i--){
            byteSwapInd(cRot, cornerCycle[i], cornerCycle[i-1]);
            bitSwapInd(eO, edgeCycle[i], edgeCycle[i-1]);
            bitSwapInd(eQPer, edgeCycle[i], edgeCycle[i-1]);
        }
        for(int i = 0; i<4; i++) {
            byteRotInd(cRot, cornerCycle[i], ((i) % 2) + 1);
            bitFlipInd(eO, edgeCycle[i]);
        }
        return new Stage1Cube(cRot, eO, eQPer, cube.getMoves());
    }
    public static Stage1Cube moveF2(Stage1Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerRot(), 8);
        BitSet eO = (BitSet) cube.getEdgeOri().clone();
        BitSet eQPer = (BitSet) cube.getEqEdgePerm().clone();

        int[] edgeCycle = {2,4,8,5};
        int[] cornerCycle = {2,3,4,5};

        for(int i = 0; i<2; i++){
            byteSwapInd(cRot, cornerCycle[i], cornerCycle[i+2]);
            bitSwapInd(eO, edgeCycle[i], edgeCycle[i+2]);
            bitSwapInd(eQPer, edgeCycle[i], edgeCycle[i+2]);
        }
        return new Stage1Cube(cRot, eO, eQPer, cube.getMoves());
    }

    public static Stage1Cube moveB(Stage1Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerRot(), 8);
        BitSet eO = (BitSet) cube.getEdgeOri().clone();
        BitSet eQPer = (BitSet) cube.getEqEdgePerm().clone();

        int[] edgeCycle = {0,6,10,7};
        int[] cornerCycle = {0,1,6,7};

        for(int i = 0; i<3; i++){
            byteSwapInd(cRot, cornerCycle[i], cornerCycle[i+1]);
            bitSwapInd(eO, edgeCycle[i], edgeCycle[i+1]);
            bitSwapInd(eQPer, edgeCycle[i], edgeCycle[i+1]);
        }
        for(int i = 0; i<4; i++) {
            byteRotInd(cRot, cornerCycle[i], ((i) % 2) + 1);
            bitFlipInd(eO, edgeCycle[i]);
        }

        return new Stage1Cube(cRot, eO, eQPer, cube.getMoves());
    }
    public static Stage1Cube moveBp(Stage1Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerRot(), 8);
        BitSet eO = (BitSet) cube.getEdgeOri().clone();
        BitSet eQPer = (BitSet) cube.getEqEdgePerm().clone();

        int[] edgeCycle = {0,6,10,7};
        int[] cornerCycle = {0,1,6,7};

        for(int i = 3; i>0; i--){
            byteSwapInd(cRot, cornerCycle[i], cornerCycle[i-1]);
            bitSwapInd(eO, edgeCycle[i], edgeCycle[i-1]);
            bitSwapInd(eQPer, edgeCycle[i], edgeCycle[i-1]);
        }
        for(int i = 0; i<4; i++) {
            byteRotInd(cRot, cornerCycle[i], ((i) % 2) + 1);
            bitFlipInd(eO, edgeCycle[i]);
        }
        return new Stage1Cube(cRot, eO, eQPer, cube.getMoves());
    }
    public static Stage1Cube moveB2(Stage1Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerRot(), 8);
        BitSet eO = (BitSet) cube.getEdgeOri().clone();
        BitSet eQPer = (BitSet) cube.getEqEdgePerm().clone();

        int[] edgeCycle = {0,6,10,7};
        int[] cornerCycle = {0,1,6,7};

        for(int i = 0; i<2; i++){
            byteSwapInd(cRot, cornerCycle[i], cornerCycle[i+2]);
            bitSwapInd(eO, edgeCycle[i], edgeCycle[i+2]);
            bitSwapInd(eQPer, edgeCycle[i], edgeCycle[i+2]);
        }
        return new Stage1Cube(cRot, eO, eQPer, cube.getMoves());
    }

    public static Stage1Cube moveU(Stage1Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerRot(), 8);
        BitSet eO = (BitSet) cube.getEdgeOri().clone();
        BitSet eQPer = (BitSet) cube.getEqEdgePerm().clone();

        int[] edgeCycle = {3,2,1,0};
        int[] cornerCycle = {3,2,1,0};

        for(int i = 0; i<3; i++){
            byteSwapInd(cRot, cornerCycle[i], cornerCycle[i+1]);
            bitSwapInd(eO, edgeCycle[i], edgeCycle[i+1]);
            bitSwapInd(eQPer, edgeCycle[i], edgeCycle[i+1]);
        }
        return new Stage1Cube(cRot, eO, eQPer, cube.getMoves());
    }
    public static Stage1Cube moveUp(Stage1Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerRot(), 8);
        BitSet eO = (BitSet) cube.getEdgeOri().clone();
        BitSet eQPer = (BitSet) cube.getEqEdgePerm().clone();

        int[] edgeCycle = {3,2,1,0};
        int[] cornerCycle = {3,2,1,0};

        for(int i = 3; i>0; i--){
            byteSwapInd(cRot, cornerCycle[i], cornerCycle[i-1]);
            bitSwapInd(eO, edgeCycle[i], edgeCycle[i-1]);
            bitSwapInd(eQPer, edgeCycle[i], edgeCycle[i-1]);
        }
        return new Stage1Cube(cRot, eO, eQPer, cube.getMoves());
    }
    public static Stage1Cube moveU2(Stage1Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerRot(), 8);
        BitSet eO = (BitSet) cube.getEdgeOri().clone();
        BitSet eQPer = (BitSet) cube.getEqEdgePerm().clone();

        int[] edgeCycle = {3,2,1,0};
        int[] cornerCycle = {3,2,1,0};

        for(int i = 0; i<2; i++){
            byteSwapInd(cRot, cornerCycle[i], cornerCycle[i+2]);
            bitSwapInd(eO, edgeCycle[i], edgeCycle[i+2]);
            bitSwapInd(eQPer, edgeCycle[i], edgeCycle[i+2]);
        }
        return new Stage1Cube(cRot, eO, eQPer, cube.getMoves());
    }

    public static Stage1Cube moveD(Stage1Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerRot(), 8);
        BitSet eO = (BitSet) cube.getEdgeOri().clone();
        BitSet eQPer = (BitSet) cube.getEqEdgePerm().clone();

        int[] edgeCycle = {11,10,9,8};
        int[] cornerCycle = {7,6,5,4};

        for(int i = 0; i<3; i++){
            byteSwapInd(cRot, cornerCycle[i], cornerCycle[i+1]);
            bitSwapInd(eO, edgeCycle[i], edgeCycle[i+1]);
            bitSwapInd(eQPer, edgeCycle[i], edgeCycle[i+1]);
        }
        return new Stage1Cube(cRot, eO, eQPer, cube.getMoves());
    }
    public static Stage1Cube moveDp(Stage1Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerRot(), 8);
        BitSet eO = (BitSet) cube.getEdgeOri().clone();
        BitSet eQPer = (BitSet) cube.getEqEdgePerm().clone();

        int[] edgeCycle = {11,10,9,8};
        int[] cornerCycle = {7,6,5,4};

        for(int i = 3; i>0; i--){
            byteSwapInd(cRot, cornerCycle[i], cornerCycle[i-1]);
            bitSwapInd(eO, edgeCycle[i], edgeCycle[i-1]);
            bitSwapInd(eQPer, edgeCycle[i], edgeCycle[i-1]);
        }
        return new Stage1Cube(cRot, eO, eQPer, cube.getMoves());
    }
    public static Stage1Cube moveD2(Stage1Cube cube){
        byte[] cRot = Arrays.copyOf(cube.getCornerRot(), 8);
        BitSet eO = (BitSet) cube.getEdgeOri().clone();
        BitSet eQPer = (BitSet) cube.getEqEdgePerm().clone();

        int[] edgeCycle = {11,10,9,8};
        int[] cornerCycle = {7,6,5,4};

        for(int i = 0; i<2; i++){
            byteSwapInd(cRot, cornerCycle[i], cornerCycle[i+2]);
            bitSwapInd(eO, edgeCycle[i], edgeCycle[i+2]);
            bitSwapInd(eQPer, edgeCycle[i], edgeCycle[i+2]);
        }
        return new Stage1Cube(cRot, eO, eQPer, cube.getMoves());
    }

    public static void swapEdges(RubiksCube cube, int ind1, int ind2) {
        PEdge[] edges = cube.getEdges();
        PEdge temp = edges[ind1];
        edges[ind1] = edges[ind2];
        edges[ind2] = temp;
    }

    public static void swapCorners(RubiksCube cube, int ind1, int ind2) {
        PCorner[] corners = cube.getCorners();
        PCorner temp = corners[ind1];
        corners[ind1] = corners[ind2];
        corners[ind2] = temp;
    }

    public static void swapCenters(RubiksCube cube, int ind1, int ind2) {
        SColor[] centers = cube.getCenters();
        SColor temp = centers[ind1];
        centers[ind1] = centers[ind2];
        centers[ind2] = temp;
    }
    public static void swapIndices(SColor[] list, int ind1, int ind2){
        SColor temp = list[ind1];
        list[ind1] = list[ind2];
        list[ind2] = temp;
    }
    public static void listXPRIME(SColor[] list){
        int[] centers = {0, 3, 5, 1};
        for (int i = 0; i < 3; i++) {
            swapIndices(list, centers[i], centers[i + 1]);
        }
    }
    public static void listY(SColor[] list){
        int[] centers = {4, 1, 2, 3};
        for (int i = 0; i < 3; i++) {
            swapIndices(list, centers[i], centers[i + 1]);
        }
    }
    public static void listZ(SColor[] list){
        int[] centers = {0, 4, 5, 2};
        for (int i = 0; i < 3; i++) {
            swapIndices(list, centers[i], centers[i + 1]);
        }
    }

    public static void orientList(SColor[] list){
        while(list[0] != SColor.WHITE && list[0] != SColor.GREEN){
            listZ(list);
        }
        if(list[0] == SColor.GREEN){
            listXPRIME(list);
            while(list[0] != SColor.WHITE){
                listZ(list);
            }
        }else{
            while(list[1] != SColor.GREEN){
                listY(list);
            }
        }
    }

    public static LinkedList<Move> orientCube(RubiksCube cube){
        //returns the inverse algorithm to un-orient the cube
        LinkedList<Move> inverse = new LinkedList<>();
        while(cube.getCenter(0) != SColor.WHITE && cube.getCenter(0) != SColor.GREEN){
            moveZ(cube);
            inverse.addFirst(Move.zPRIME);
        }
        if(cube.getCenter(0) == SColor.GREEN){
            moveXPrime(cube);
            inverse.addFirst(Move.x);
            while(cube.getCenter(0) != SColor.WHITE){
                moveZ(cube);
                inverse.addFirst(Move.zPRIME);
            }
        }else{
            while(cube.getCenter(1) != SColor.GREEN){
                moveY(cube);
                inverse.addFirst(Move.yPRIME);
            }
        }

        return inverse;
    }

    public static boolean checkCenterParity(SColor[] list){
        SColor[] newList = Arrays.copyOf(list, 6);
        orientList(newList);
        return newList[2] != SColor.RED;
    }

    public static void moveU(RubiksCube cube) {
        for (int i = 0; i < 3; i++) {
            swapCorners(cube, 3 - i, (4 - i) % 4);
            swapEdges(cube, 3 - i, (4 - i) % 4);
        }
    }

    public static void moveUPrime(RubiksCube cube) {
        for (int i = 0; i < 3; i++) {
            swapCorners(cube, i, (1 + i) % 4);
            swapEdges(cube, i, (1 + i) % 4);
        }
    }

    public static void moveU2(RubiksCube cube) {
        for (int i = 0; i < 2; i++) {
            swapCorners(cube, i, i + 2);
            swapEdges(cube, i, i + 2);
        }
    }

    public static void moveD(RubiksCube cube) {
        for (int i = 0; i < 3; i++) {
            swapCorners(cube, 7 - i, (4 - i) % 4 + 4);
            swapEdges(cube, 11 - i, (4 - i) % 4 + 8);
        }
    }

    public static void moveDPrime(RubiksCube cube) {
        for (int i = 0; i < 3; i++) {
            swapCorners(cube, i + 4, (1 + i) % 4 + 4);
            swapEdges(cube, i + 8, (1 + i) % 4 + 8);
        }
    }

    public static void moveD2(RubiksCube cube) {
        for (int i = 4; i < 6; i++) {
            swapCorners(cube, i, i + 2);
            swapEdges(cube, i + 4, i + 6);
        }
    }

    public static void moveF(RubiksCube cube) {
        int[] edges = {2, 4, 8, 5};
        for (int i = 0; i < 3; i++) {
            swapCorners(cube, 2 + i, (3 + i));
            swapEdges(cube, edges[i], edges[i + 1]);
        }
        for (int i = 0; i < 4; i++) {
            cube.getCorner(2 + i).rotate(1 + i % 2);
            cube.getEdge(edges[i]).flip();
        }
    }

    public static void moveFPrime(RubiksCube cube) {
        int[] edges = {2, 5, 8, 4};
        for (int i = 0; i < 3; i++) {
            swapCorners(cube, 5 - i, (4 - i));
            swapEdges(cube, edges[i], edges[i + 1]);
        }
        for (int i = 0; i < 4; i++) {
            cube.getCorner(2 + i).rotate(1 + i % 2);
            cube.getEdge(edges[i]).flip();
        }
    }

    public static void moveF2(RubiksCube cube) {
        int[] edges = {2, 5, 8, 4};
        for (int i = 2; i < 4; i++) {
            swapCorners(cube, i, i + 2);
            swapEdges(cube, edges[i - 2], edges[i]);
        }
    }

    public static void moveB(RubiksCube cube) {
        int[] edges = {0, 6, 10, 7};
        int[] corners = {0, 1, 6, 7};
        for (int i = 0; i < 3; i++) {
            swapCorners(cube, corners[i], corners[i + 1]);
            swapEdges(cube, edges[i], edges[i + 1]);
        }
        for (int i = 0; i < 4; i++) {
            cube.getCorner(corners[i]).rotate(1 + i % 2);
            cube.getEdge(edges[i]).flip();
        }
    }

    public static void moveBPrime(RubiksCube cube) {
        int[] edges = {0, 7, 10, 6};
        int[] corners = {0, 7, 6, 1};
        for (int i = 0; i < 3; i++) {
            swapCorners(cube, corners[i], corners[i + 1]);
            swapEdges(cube, edges[i], edges[i + 1]);
        }
        for (int i = 0; i < 4; i++) {
            cube.getCorner(corners[i]).rotate(1 + i % 2);
            cube.getEdge(edges[i]).flip();
        }
    }

    public static void moveB2(RubiksCube cube) {
        int[] edges = {0, 7, 10, 6};
        int[] corners = {0, 7, 6, 1};
        for (int i = 0; i < 2; i++) {
            swapCorners(cube, corners[i], corners[i + 2]);
            swapEdges(cube, edges[i], edges[i + 2]);
        }
    }

    public static void moveR(RubiksCube cube) {
        int[] edges = {1, 5, 9, 6};
        int[] corners = {1, 2, 5, 6};
        for (int i = 0; i < 3; i++) {
            swapCorners(cube, corners[i], corners[i + 1]);
            swapEdges(cube, edges[i], edges[i + 1]);
        }
        for (int i = 0; i < 4; i++) {
            cube.getCorner(corners[i]).rotate(1 + i % 2);
        }
    }

    public static void moveRPrime(RubiksCube cube) {
        int[] edges = {1, 6, 9, 5};
        int[] corners = {1, 6, 5, 2};
        for (int i = 0; i < 3; i++) {
            swapCorners(cube, corners[i], corners[i + 1]);
            swapEdges(cube, edges[i], edges[i + 1]);
        }
        for (int i = 0; i < 4; i++) {
            cube.getCorner(corners[i]).rotate(1 + i % 2);
        }
    }

    public static void moveR2(RubiksCube cube) {
        int[] edges = {1, 6, 9, 5};
        int[] corners = {1, 6, 5, 2};
        for (int i = 0; i < 2; i++) {
            swapCorners(cube, corners[i], corners[i + 2]);
            swapEdges(cube, edges[i], edges[i + 2]);
        }
    }

    public static void moveL(RubiksCube cube) {
        int[] corners = {3, 0, 7, 4};
        int[] edges = {3, 7, 11, 4};
        for (int i = 0; i < 3; i++) {
            swapCorners(cube, corners[i], corners[i + 1]);
            swapEdges(cube, edges[i], edges[i + 1]);
        }
        for (int i = 0; i < 4; i++) {
            cube.getCorner(corners[i]).rotate(1 + i % 2);
        }
    }

    public static void moveLPrime(RubiksCube cube) {
        int[] corners = {3, 4, 7, 0};
        int[] edges = {3, 4, 11, 7};
        for (int i = 0; i < 3; i++) {
            swapCorners(cube, corners[i], corners[i + 1]);
            swapEdges(cube, edges[i], edges[i + 1]);
        }
        for (int i = 0; i < 4; i++) {
            cube.getCorner(corners[i]).rotate(1 + i % 2);
        }
    }

    public static void moveL2(RubiksCube cube) {
        int[] corners = {3, 0, 7, 4};
        int[] edges = {3, 7, 11, 4};
        for (int i = 0; i < 2; i++) {
            swapCorners(cube, corners[i], corners[i + 2]);
            swapEdges(cube, edges[i], edges[i + 2]);
        }
    }

    public static void moveM(RubiksCube cube) {
        int[] centers = {0, 3, 5, 1};
        int[] edges = {2, 0, 10, 8};
        for (int i = 0; i < 3; i++) {
            swapCenters(cube, centers[i], centers[i + 1]);
            swapEdges(cube, edges[i], edges[i + 1]);
        }
        for (int i = 0; i < 4; i++) {
            cube.getEdge(edges[i]).flip();
        }
    }

    public static void moveMPrime(RubiksCube cube) {
        int[] centers = {0, 1, 5, 3};
        int[] edges = {2, 8, 10, 0};
        for (int i = 0; i < 3; i++) {
            swapCenters(cube, centers[i], centers[i + 1]);
            swapEdges(cube, edges[i], edges[i + 1]);
        }
        for (int i = 0; i < 4; i++) {
            cube.getEdge(edges[i]).flip();
        }
    }

    public static void moveM2(RubiksCube cube) {
        int[] centers = {0, 1, 5, 3};
        int[] edges = {2, 8, 10, 0};
        for (int i = 0; i < 2; i++) {
            swapCenters(cube, centers[i], centers[i + 2]);
            swapEdges(cube, edges[i], edges[i + 2]);
        }
    }

    public static void moveS(RubiksCube cube) {
        int[] centers = {0, 4, 5, 2};
        int[] edges = {1, 3, 11, 9};
        for (int i = 0; i < 3; i++) {
            swapCenters(cube, centers[i], centers[i + 1]);
            swapEdges(cube, edges[i], edges[i + 1]);
        }
        for (int i = 0; i < 4; i++) {
            cube.getEdge(edges[i]).flip();
        }
    }

    public static void moveSPrime(RubiksCube cube) {
        int[] centers = {0, 2, 5, 4};
        int[] edges = {1, 9, 11, 3};
        for (int i = 0; i < 3; i++) {
            swapCenters(cube, centers[i], centers[i + 1]);
            swapEdges(cube, edges[i], edges[i + 1]);
        }
        for (int i = 0; i < 4; i++) {
            cube.getEdge(edges[i]).flip();
        }
    }

    public static void moveS2(RubiksCube cube) {
        int[] centers = {0, 4, 5, 2};
        int[] edges = {1, 3, 11, 9};
        for (int i = 0; i < 2; i++) {
            swapCenters(cube, centers[i], centers[i + 2]);
            swapEdges(cube, edges[i], edges[i + 2]);
        }
    }

    public static void moveE(RubiksCube cube) {
        int[] centers = {4, 3, 2, 1};
        int[] edges = {4, 7, 6, 5};
        for (int i = 0; i < 3; i++) {
            swapCenters(cube, centers[i], centers[i + 1]);
            swapEdges(cube, edges[i], edges[i + 1]);
        }
        for (int i = 0; i < 4; i++) {
            cube.getEdge(edges[i]).flip();
        }
    }

    public static void moveEPrime(RubiksCube cube) {
        int[] centers = {4, 1, 2, 3};
        int[] edges = {4, 5, 6, 7};
        for (int i = 0; i < 3; i++) {
            swapCenters(cube, centers[i], centers[i + 1]);
            swapEdges(cube, edges[i], edges[i + 1]);
        }
        for (int i = 0; i < 4; i++) {
            cube.getEdge(edges[i]).flip();
        }
    }

    public static void moveE2(RubiksCube cube) {
        int[] centers = {4, 3, 2, 1};
        int[] edges = {4, 7, 6, 5};
        for (int i = 0; i < 2; i++) {
            swapCenters(cube, centers[i], centers[i + 2]);
            swapEdges(cube, edges[i], edges[i + 2]);
        }
    }

    public static void mover(RubiksCube cube) {
        moveR(cube);
        moveMPrime(cube);
    }

    public static void moverPrime(RubiksCube cube) {
        moveRPrime(cube);
        moveM(cube);
    }

    public static void mover2(RubiksCube cube) {
        moveR2(cube);
        moveM2(cube);
    }

    public static void movel(RubiksCube cube) {
        moveL(cube);
        moveM(cube);
    }

    public static void movelPrime(RubiksCube cube) {
        moveLPrime(cube);
        moveMPrime(cube);
    }

    public static void movel2(RubiksCube cube) {
        moveL2(cube);
        moveM2(cube);
    }

    public static void moveu(RubiksCube cube) {
        moveU(cube);
        moveEPrime(cube);
    }

    public static void moveuPrime(RubiksCube cube) {
        moveUPrime(cube);
        moveE(cube);
    }

    public static void moveu2(RubiksCube cube) {
        moveU2(cube);
        moveE2(cube);
    }

    public static void moved(RubiksCube cube) {
        moveD(cube);
        moveE(cube);
    }

    public static void movedPrime(RubiksCube cube) {
        moveDPrime(cube);
        moveEPrime(cube);
    }

    public static void moved2(RubiksCube cube) {
        moveD2(cube);
        moveE2(cube);
    }

    public static void moveb(RubiksCube cube) {
        moveB(cube);
        moveSPrime(cube);
    }

    public static void movebPrime(RubiksCube cube) {
        moveBPrime(cube);
        moveS(cube);
    }

    public static void moveb2(RubiksCube cube) {
        moveB2(cube);
        moveS2(cube);
    }

    public static void movef(RubiksCube cube) {
        moveF(cube);
        moveS(cube);
    }

    public static void movefPrime(RubiksCube cube) {
        moveFPrime(cube);
        moveSPrime(cube);
    }

    public static void movef2(RubiksCube cube) {
        moveF2(cube);
        moveS2(cube);
    }

    public static void moveX(RubiksCube cube) {
        mover(cube);
        moveLPrime(cube);
    }

    public static void moveXPrime(RubiksCube cube) {
        moverPrime(cube);
        moveL(cube);
    }

    public static void moveX2(RubiksCube cube) {
        mover2(cube);
        moveL2(cube);
    }

    public static void moveY(RubiksCube cube) {
        moveU(cube);
        movedPrime(cube);
    }

    public static void moveYPrime(RubiksCube cube) {
        moveUPrime(cube);
        moved(cube);
    }

    public static void moveY2(RubiksCube cube) {
        moveU2(cube);
        moved2(cube);
    }

    public static void moveZ(RubiksCube cube) {
        moveF(cube);
        movebPrime(cube);
    }

    public static void moveZPrime(RubiksCube cube) {
        moveFPrime(cube);
        moveb(cube);
    }
    public static void moveZ2(RubiksCube cube) {
        moveF2(cube);
        moveb2(cube);
    }
}
