package com.matoe.rubikscube;
import java.util.*;

public class RubiksCube {
    private final PCorner[] corners; // array of 8 corner pieces, ordered clockwise from top, then clockwise from bottom.
    public PCorner[] getCorners() {
        return corners;
    }
    public PCorner getCorner(int ind){
        return corners[ind];
    }

    private final PEdge[] edges; // array of 12 edge pieces, ordered clockwise-relative. see reference cube.
    public PEdge[] getEdges() {
        return edges;
    }
    public PEdge getEdge(int ind){
        return edges[ind];
    }

    private final SColor[] centers; // array of 6 center pieces, ordered top, e-clockwise, then bottom. see reference
    public SColor[] getCenters() {
        return centers;
    }
    public SColor getCenter(int ind){
        return centers[ind];
    }
    public void setCenter(int ind, SColor c){
        centers[ind] = c;
    }
    public SColor getColor(char key, int piece, int ind){
        // key is m for center, c for corner, e for edge
        switch(key){
            case 'm':
                return getCenter(ind);
            case 'c':
                return getCorner(piece).getColor(ind);
            case 'e':
                return getEdge(piece).getColor(ind);
            default:
                throw new RuntimeException("Incorrect getColor() Key");
        }
    }
    public void setColor(SColor c, char key, int piece, int ind){
        switch(key){
            case 'm':
                setCenter(ind, c);
                break;
            case 'c':
                getCorner(piece).setColor(ind, c);
                break;
            case 'e':
                getEdge(piece).setColor(ind, c);
                break;
            default:
                throw new RuntimeException("Incorrect setColor() Key");
        }
    }
    public RubiksCube(){
        //Initializes a cube in a solved state (White top, Green front)

        //initializing center pieces
        centers = new SColor[6];
        centers[0] = SColor.WHITE;
        SColor[] order = SColor.getYellowOrder();
        System.arraycopy(order, 0, centers, 1, 4);
        centers[5] = SColor.YELLOW;

        //initializing edge pieces
        edges = new PEdge[12];
        for(int i = 0; i<4; i++){
            edges[i+8] = new PEdge(SColor.YELLOW, order[i]);
        }
        order = SColor.getWhiteOrder();
        for(int i = 0; i<4; i++){
            edges[i] = new PEdge(SColor.WHITE, order[(i+2)%4]);
        }
        edges[4] = new PEdge(SColor.GREEN, SColor.ORANGE);
        edges[5] = new PEdge(SColor.GREEN, SColor.RED);
        edges[6] = new PEdge(SColor.BLUE, SColor.RED);
        edges[7] = new PEdge(SColor.BLUE, SColor.ORANGE);

        //initializing corner pieces
        corners = new PCorner[8];
        for(int i = 0; i<4; i++){
            corners[i] = new PCorner(SColor.WHITE, order[(i+1)%4], order[(i+2)%4]);
        }
        order = SColor.getYellowOrder();
        for(int i = 0; i<4; i++){
            corners[i+4] = new PCorner(SColor.YELLOW, order[(i+3)%4], order[i]);
        }
    }
    public Stage1Cube getFirstReduction(){
        byte[] cornerRot = new byte[8];
        for(int i = 0; i<8; i++){
            cornerRot[i] = (byte) getCorner(i).getRot();
        }

        BitSet edgeRot = new BitSet(12);

        BitSet edgeEq = new BitSet(12);

        for(int i = 0; i<12; i++){
            edgeRot.set(i,getEdge(i).isFlipped());
            if(getEdge(i).isEQEdge() == 1) {
                edgeEq.flip(i);
            }
        }

        return new Stage1Cube(cornerRot, edgeRot, edgeEq);
    }
    public Stage2Cube getSecondReduction(){
        byte[] cornerPerm = new byte[8];
        for(int i = 0; i<8; i++){
            cornerPerm[i] = (byte) (i - getCorner(i).getTarget());
        }
        byte[] edgePerm = new byte[8];
        int targetTemp;
        for(int i = 0; i<4; i++){
            targetTemp = getEdge(i).getTarget();
            if(targetTemp<4) {
                edgePerm[i] = (byte) (i - targetTemp);
            }else{
                edgePerm[i] = (byte)(i+4-targetTemp);
            }
        }
        for(int i = 4; i<8; i++){
            targetTemp = getEdge(i+4).getTarget();
            if(targetTemp<4) {
                edgePerm[i] = (byte) (i - targetTemp);
            }else{
                edgePerm[i] = (byte) (i+4-targetTemp);
            }
        }
        byte[] eqPerm = new byte[4];
        for(int i = 0; i<4; i++){
            eqPerm[i] = (byte) (i - getEdge(i+4).getTarget() +4);
        }
        return new Stage2Cube(cornerPerm, edgePerm, eqPerm);
    }
    public void setTo(RubiksCube other){
        //makes this cube a clone of the one submitted.
        for(int i = 0; i<6; i++){
            this.setColor(other.getColor('m', i, i), 'm', i, i);
        }
        for(int i = 0; i<12; i++){
            for(int j = 0; j<2; j++) {
                this.setColor(other.getColor('e', i, j), 'e', i, j);
            }
        }
        for(int i = 0; i<8; i++){
            for(int j = 0; j<3; j++){
                this.setColor(other.getColor('c', i, j), 'c', i, j);
            }
        }
    }
    public void setToColor(SColor c){
        //makes this cube have all sides of color c
        for(int i = 0; i<6; i++){
            this.setColor(c, 'm', i, i);
        }
        for(int i = 0; i<12; i++){
            for(int j = 0; j<2; j++) {
                this.setColor(c, 'e', i, j);
            }
        }
        for(int i = 0; i<8; i++){
            for(int j = 0; j<3; j++){
                this.setColor(c, 'c', i, j);
            }
        }

    }
    public void move(Move move){
        switch(move){
            case U:
                MoveHelper.moveU(this);
                break;
            case UPRIME:
                MoveHelper.moveUPrime(this);
                break;
            case U2:
                MoveHelper.moveU2(this);
                break;
            case D:
                MoveHelper.moveD(this);
                break;
            case DPRIME:
                MoveHelper.moveDPrime(this);
                break;
            case D2:
                MoveHelper.moveD2(this);
                break;
            case F:
                MoveHelper.moveF(this);
                break;
            case FPRIME:
                MoveHelper.moveFPrime(this);
                break;
            case F2:
                MoveHelper.moveF2(this);
                break;
            case B:
                MoveHelper.moveB(this);
                break;
            case BPRIME:
                MoveHelper.moveBPrime(this);
                break;
            case B2:
                MoveHelper.moveB2(this);
                break;
            case R:
                MoveHelper.moveR(this);
                break;
            case RPRIME:
                MoveHelper.moveRPrime(this);
                break;
            case R2:
                MoveHelper.moveR2(this);
                break;
            case L:
                MoveHelper.moveL(this);
                break;
            case LPRIME:
                MoveHelper.moveLPrime(this);
                break;
            case L2:
                MoveHelper.moveL2(this);
                break;
            case M:
                MoveHelper.moveM(this);
                break;
            case MPRIME:
                MoveHelper.moveMPrime(this);
                break;
            case M2:
                MoveHelper.moveM2(this);
                break;
            case S:
                MoveHelper.moveS(this);
                break;
            case SPRIME:
                MoveHelper.moveSPrime(this);
                break;
            case S2:
                MoveHelper.moveS2(this);
                break;
            case E:
                MoveHelper.moveE(this);
                break;
            case EPRIME:
                MoveHelper.moveEPrime(this);
                break;
            case E2:
                MoveHelper.moveE2(this);
                break;
            case r:
                MoveHelper.mover(this);
                break;
            case rPRIME:
                MoveHelper.moverPrime(this);
                break;
            case r2:
                MoveHelper.mover2(this);
                break;
            case l:
                MoveHelper.movel(this);
                break;
            case lPRIME:
                MoveHelper.movelPrime(this);
                break;
            case l2:
                MoveHelper.movel2(this);
                break;
            case u:
                MoveHelper.moveu(this);
                break;
            case uPRIME:
                MoveHelper.moveuPrime(this);
                break;
            case u2:
                MoveHelper.moveu2(this);
                break;
            case d:
                MoveHelper.moved(this);
                break;
            case dPRIME:
                MoveHelper.movedPrime(this);
                break;
            case d2:
                MoveHelper.moved2(this);
                break;
            case f:
                MoveHelper.movef(this);
                break;
            case fPRIME:
                MoveHelper.movefPrime(this);
                break;
            case f2:
                MoveHelper.movef2(this);
                break;
            case b:
                MoveHelper.moveb(this);
                break;
            case bPRIME:
                MoveHelper.movebPrime(this);
                break;
            case b2:
                MoveHelper.moveb2(this);
                break;
            case x:
                MoveHelper.moveX(this);
                break;
            case xPRIME:
                MoveHelper.moveXPrime(this);
                break;
            case x2:
                MoveHelper.moveX2(this);
                break;
            case y:
                MoveHelper.moveY(this);
                break;
            case yPRIME:
                MoveHelper.moveYPrime(this);
                break;
            case y2:
                MoveHelper.moveY2(this);
                break;
            case z:
                MoveHelper.moveZ(this);
                break;
            case zPRIME:
                MoveHelper.moveZPrime(this);
                break;
            case z2:
                MoveHelper.moveZ2(this);
                break;
            default:
                throw new MoveNotFoundException();
        }
    } // colossal switch case

    public void doAlgorithm(Collection<Move> moves){
        for(Move m: moves){
            this.move(m);
        }
    }
    public static ArrayList<Move> getInverse(ArrayList<Move> alg){
        //only for RLUDFB
        ArrayList<Move> inverse = new ArrayList<>();
        for(int i = alg.size()-1; i>=0; i--){
            inverse.add(Move.getInverse(alg.get(i)));
        }
        return inverse;
    }
    public boolean isPiecesValid(){
        //check that all pieces are valid
        for(PEdge e: getEdges()){
            if(!e.isValid()){
                return false;
            }
        }
        for(PCorner c: getCorners()){
            if(!c.isValid()){
                return false;
            }
        }

        //check that there are no duplicate pieces
        HashSet<PCorner> seenC = new HashSet<>(Arrays.asList(getCorners()));
        if(seenC.size() != 8){
            return false;
        }
        HashSet<PEdge> seenE = new HashSet<>(Arrays.asList(getEdges()));
        if(seenE.size() != 12){
            return false;
        }
        HashSet<SColor> seenM = new HashSet<>(Arrays.asList(getCenters()));
        if(seenM.size() != 6){
            return false;
        }

        if(getCenter(0).index%3 != getCenter(5).index%3){
            return false;
        }
        if(getCenter(1).index%3 != getCenter(3).index%3){
            return false;
        }
        if(getCenter(2).index%3 != getCenter(4).index%3){
            return false;
        }

        return !MoveHelper.checkCenterParity(getCenters());
    }
    public boolean isSolved(){
        if(!isPiecesValid()){
            return false;
        }

        //use algorithm to rotate a copy of the cube to white top green front
        LinkedList<Move> inverse = MoveHelper.orientCube(this);
        //check that sum of all corner rotations is 0
        int sum = 0;
        for(int i = 0; i<8; i++){
            sum+= getCorner(i).getRot();
            if(sum>0 || getCorner(i).getTarget()!=i){
                doAlgorithm(inverse);
                return false;
            }
        }

        //check that sum of all edge flips is 0
        for(int i = 0; i<12; i++){
            sum+= getEdge(i).getFlippedNum();
            if(sum>0 || getEdge(i).getTarget()!= i){
                doAlgorithm(inverse);
                return false;
            }
        }
        //check all corner and edge targets to see if indices are correct
        doAlgorithm(inverse);
        return true;
    }
    public boolean isValid(){
        /* checks if the current cube state is solvable */
        if(!isPiecesValid()){
            return false;
        }
        //check sum of all corner rotation is divisible by 3
        int sum = 0;
        for(PCorner c: getCorners()){
            sum += c.getRot();
        }
        if(!(sum%3==0)){
            return false;
        }
        
        //check sum of all flips is divisible by 2
        sum = 0;
        for(PEdge e: getEdges()){
            sum+= e.getFlippedNum();
        }
        if(!(sum%2==0)){
            return false;
        }

        //check number of swaps necessary is divisible by 2 using targets (including centers)
        sum = 0;
        int currentInd;
        int ind;
        ArrayList<Integer> seen = new ArrayList<>();
        for(ind = 0; ind<7; ind++){
            if(!seen.contains(ind)){
                seen.add(ind);
                currentInd = getCorner(ind).getTarget();
                while(currentInd!=ind){
                    seen.add(currentInd);
                    sum++;
                    currentInd = getCorner(currentInd).getTarget();
                }
            }
        }
        seen.clear();
        for(ind = 0; ind<11; ind++){
            if(!seen.contains(ind)){
                seen.add(ind);
                currentInd = getEdge(ind).getTarget();
                while(currentInd!=ind){
                    seen.add(currentInd);
                    sum++;
                    currentInd = getEdge(currentInd).getTarget();
                }
            }
        }
        seen.clear();
        for(ind = 0; ind<5; ind++){
            if(!seen.contains(ind)){
                seen.add(ind);
                currentInd = getCenter(ind).target;
                while(currentInd!=ind){
                    seen.add(currentInd);
                    sum++;
                    currentInd = getCenter(currentInd).target;
                }
            }
        }
        return sum % 2 == 0;
    }
    @Override
    public String toString(){
        //returns the cube in a papercraft-like shape (each side flat, using their .abbr attribute)
        StringBuilder str = new StringBuilder("   ");
        str.append(getCorner(0).getColor(0).abbr);
        str.append(getEdge(0).getColor(0).abbr);
        str.append(getCorner(1).getColor(0).abbr);
        str.append("\n   ");
        str.append(getEdge(3).getColor(0).abbr);
        str.append(getCenter(0).abbr);
        str.append(getEdge(1).getColor(0).abbr);
        str.append("\n   ");
        str.append(getCorner(3).getColor(0).abbr);
        str.append(getEdge(2).getColor(0).abbr);
        str.append(getCorner(2).getColor(0).abbr).append("\n");
        for(int i = 0; i<4; i++) {
            str.append(getColor('c', (4 - i) % 4, 1).abbr).append(getColor('e', 3 - i, 1).abbr).append(getColor('c', 3 - i, 2).abbr);
        }
        str.append('\n');
        for(int i = 0; i<4; i++) {
            str.append(getColor('e', (3 + i) % 4 + 4, (i + 1) % 2).abbr).append(getCenter((3 + i) % 4 + 1).abbr).append(getColor('e', i + 4, (i + 1) % 2).abbr);
        }
        str.append('\n');
        for(int i = 0; i<4; i++) {
            str.append(getColor('c', (i + 3) % 4 + 4, 2).abbr).append(getColor('e', (3 + i) % 4 + 8, 1).abbr).append(getColor('c', 4 + i, 1).abbr);
        }
        str.append("\n   ");
        str.append(getCorner(4).getColor(0).abbr);
        str.append(getEdge(8).getColor(0).abbr);
        str.append(getCorner(5).getColor(0).abbr);
        str.append("\n   ");
        str.append(getEdge(11).getColor(0).abbr);
        str.append(getCenter(5).abbr);
        str.append(getEdge(9).getColor(0).abbr);
        str.append("\n   ");
        str.append(getCorner(7).getColor(0).abbr);
        str.append(getEdge(10).getColor(0).abbr);
        str.append(getCorner(6).getColor(0).abbr).append("\n");

        StringBuilder str2 = new StringBuilder(" ");
        for(int i = 0; i<str.length(); i++){
            str2.append(str.charAt(i));
            str2.append(' ');
        }
        return str2.toString();
    }

}
