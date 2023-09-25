package com.matoe.rubikscube;
import java.util.Objects;

public class PCorner {
    private final SColor[] colors; //Colors in corner piece, ordered clockwise
    public SColor getColor(int ind){
        return colors[ind];
    }
    public void setColor(int ind, SColor c){
        colors[ind] = c;
        calculateRot();
    }
    private int rot; //represents how many clockwise rotations the white/yellow piece has made
    public int getRot() {
        return rot;
    }
    public void rotate(int r){
        //changes rot by r%3, and "rotates" array accordingly. positive numbers are clockwise rotations, negative are counterclockwise.
        r = ((r % 3)+3)%3;
        rot = (rot+r)%3;
        SColor temp = colors[0];
        colors[0] = colors[(2*r)%3];
        colors[(2*r)%3] = colors[r];
        colors[r] = temp;
    }
    public PCorner(SColor cTop, SColor c2, SColor c3){
        colors = new SColor[]{cTop, c2, c3};
        for(int i = 0; i<3; i++){
            if(colors[i].index%3 == 0){
                rot = i;
                break;
            }
        }
    }
    public void calculateRot(){
        //only meant to be run on valid edges
        for(int i = 0; i<3; i++){
            if(getColor(i).index%3 == 0){
                rot = i;
                break;
            }
        }
    }
    boolean isValid(){
        //test 1: no colors are opposites
        int ind = 0;
        boolean isWhite = false;

        for(int i = 0; i<3; i++){
            if(colors[i].isOpposite(colors[(i+1)%3])){
                return false;
            }
            if(colors[i].index%3 == 0){
                isWhite = colors[i] == SColor.WHITE;
                ind = (i+1)%3;
            }
        }
        //test 2: makes sure color scheme is correct (W>R>G is valid, but W>G>R is not)
        SColor[] order;
        if(isWhite){
            order = SColor.getWhiteOrder();
        }else{
            order = SColor.getYellowOrder();
        }

        for(int i = 0; i<4; i++){
            if(colors[ind] == order[i] && colors[(ind+1)%3] == order[(i+1)%4]){
                return true;
            }
        }
        return false;
    }
    public int getTarget(){
        if(!isValid()){
            throw new PieceNotValidException();
        }
        switch(getColor(rot)){
            case WHITE:
                switch(getColor((rot+1)%3)){
                    case ORANGE:
                        return 0;
                    case BLUE:
                        return 1;
                    case RED:
                        return 2;
                    case GREEN:
                        return 3;
                }
                break;
            case YELLOW:
                switch(getColor((rot+1)%3)){
                    case ORANGE:
                        return 4;
                    case GREEN:
                        return 5;
                    case RED:
                        return 6;
                    case BLUE:
                        return 7;
                }

                break;
        }
        return -1;
    }
    @Override
    public String toString(){
        return "(" + colors[0] + ", " + colors[1] + ", " + colors[2] + " | Rotation: " + rot + " | Valid: "+ isValid() +")";
    }
    @Override
    public boolean equals(Object other){
        //gets the target index within RubiksCube.corners that a valid corner should be in for a solved case
        //assuming white top green front
        if(other instanceof PCorner){
            boolean equal = true;
            for(int i = 0; i<3; i++){
                equal = equal && getColor((getRot()+i)%3) == ((PCorner)other).getColor((((PCorner)other).getRot()+i)%3);
            }
            return equal;
        }
        return false;
    }
    @Override
    public int hashCode(){
        return Objects.hash(getColor((getRot())%3),getColor((getRot()+1)%3), getColor((getRot()+2)%3));
    }
}
