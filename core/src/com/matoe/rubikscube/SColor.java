package com.matoe.rubikscube;

public enum SColor {
    WHITE(0, 'W', 0),
    GREEN(1, 'G', 1),
    RED(2, 'R', 2),
    YELLOW(3, 'Y', 5),
    BLUE(4, 'B', 3),
    ORANGE(5, 'O', 4);
    final int index;
    final char abbr;
    final int target;
    SColor(int index, char abbr, int centerTarget){
        this.index = index;
        this.abbr = abbr;
        this.target = centerTarget;
    }
    boolean isOpposite(SColor other){
        return this.index%3 == other.index%3;
    }
    static SColor[] getWhiteOrder(){
        return new SColor[]{GREEN, ORANGE, BLUE, RED};
    }
    static SColor[] getYellowOrder(){
        return new SColor[]{GREEN, RED, BLUE, ORANGE};
    }

}

