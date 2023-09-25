package com.matoe.rubikscube;

import java.util.Objects;

public class PEdge {
    private final SColor[] colors; // array of 2 colors (ordered based on "front" and "side")
    public SColor getColor(int ind){
        return colors[ind];
    }
    public void setColor(int ind, SColor c){
        colors[ind] = c;
        calculateFlip();
    }
    public int isEQEdge(){
        if(getColor(0).index%3 == 0 || getColor(1).index%3 == 0){
            return 0;
        }return 1;
    }
    private boolean flipped; // represents if an edge is a "good edge" by roux metric
    public boolean isFlipped() {
        return flipped;
    }
    public int getFlippedNum(){
        if(flipped){
            return 1;
        }return 0;
    }
    public void flip(){
        //swaps order of colors and swaps boolean flipped
        flipped = !flipped;
        SColor temp = colors [0];
        colors[0] = colors[1];
        colors[1] = temp;
    }
    public PEdge(SColor topFront, SColor frontSide){
        colors = new SColor[]{topFront, frontSide};
        flipped = (topFront.index%3 > frontSide.index%3);
    }
    public void calculateFlip(){
        //only to be run on valid edges
        flipped = getColor(0).index%3> getColor(1).index%3;
    }
    @Override
    public String toString(){
        return "("+ colors[0] + ", " + colors[1] + " | Flipped: " + flipped + " | Valid: "+ isValid() +")";
    }
    public boolean isValid(){
        // returns true if edge colors are not opposite
        return !colors[0].isOpposite(colors[1]);
    }
    @Override
    public boolean equals(Object other){
        //does not take into account the flipped status of the edge
        if(other instanceof PEdge){
            return (getColor(0) == ((PEdge)other).getColor(0) && getColor(1) == ((PEdge)other).getColor(1)) || (getColor(0) == ((PEdge)other).getColor(1) && getColor(1) == ((PEdge)other).getColor(0));
        }
        return false;
    }
    @Override
    public int hashCode(){
        return Objects.hash(getColor((getFlippedNum())%2),getColor((getFlippedNum()+1)%2));
    }

    public int getTarget(){
        //gets the target index within RubiksCube.edges that a valid edge should be in for a solved case
        //assuming white top green front
        if(!isValid()){
            throw new PieceNotValidException();
        }
        switch(getColor(getFlippedNum())){
            case WHITE:
                switch(getColor((getFlippedNum()+1)%2)){
                    case BLUE:
                        return 0;
                    case RED:
                        return 1;
                    case GREEN:
                        return 2;
                    case ORANGE:
                        return 3;
                }
                break;
            case YELLOW:
                switch(getColor((getFlippedNum()+1)%2)){
                    case BLUE:
                        return 10;
                    case RED:
                        return 9;
                    case GREEN:
                        return 8;
                    case ORANGE:
                        return 11;
                }
                break;
            case BLUE:
                switch(getColor((getFlippedNum()+1)%2)){
                    case RED:
                        return 6;
                    case ORANGE:
                        return 7;
                }
                break;
            case GREEN:
                switch(getColor((getFlippedNum()+1)%2)){
                    case RED:
                        return 5;
                    case ORANGE:
                        return 4;
                }
                break;
        }
        return -1;
    }

}
