package com.matoe.rubikscube;

import java.util.ArrayList;

public class UIText {
    public static String moveControls =
            "To move the cube, use\nthe following buttons:\n" +
                    "\n'Q' - F move" +
                    "\n'W' - R move" +
                    "\n'E' - U move" +
                    "\n'A' - B move" +
                    "\n'S' - L move" +
                    "\n'D' - D move" +
                    "\n'Z' - S slice move" +
                    "\n'X' - M slice move" +
                    "\n'C' - E slice move";
    public static String primeControls =
            "To move the cube, use\nthe following buttons:\n" +
                    "\n'Q' - F' move" +
                    "\n'W' - R' move" +
                    "\n'E' - U' move" +
                    "\n'A' - B' move" +
                    "\n'S' - L' move" +
                    "\n'D' - D' move" +
                    "\n'Z' - S' slice move" +
                    "\n'X' - M' slice move" +
                    "\n'C' - E' slice move";
    public static String doubleControls =
            "To move the cube, use\nthe following buttons:\n" +
                    "\n'Q' - F2 move" +
                    "\n'W' - R2 move" +
                    "\n'E' - U2 move" +
                    "\n'A' - B2 move" +
                    "\n'S' - L2 move" +
                    "\n'D' - D2 move" +
                    "\n'Z' - S2 slice move" +
                    "\n'X' - M2 slice move" +
                    "\n'C' - E2 slice move";
    public static String moveModes =
            " Use the arrow keys and < > to rotate the cube" +
                    "\n Hold 'SHIFT' to  use counter-clockwise (prime) moves" +
                    "\n Hold 'ENTER' or 'CAPS LOCK' to use double moves";
    public static String moveStates =
            "Press 'F' to enter Freecam Mode\n" +
                    "Press 'P' to set the scramble\n" +
                    "Press 'SPACE' to generate a\nsolution to the current scramble";

    public static String moveStatesHTML =
            "Press 'F' to enter Freecam Mode\n" +
                    "Press 'P' to set the scramble\n" +
                    "To use solving features,\n" +
                    "download the full version.";
    public static String freecamControls =
            "FREECAM MODE:" +
                    "\nUse the mouse to view the cube\nfrom different angles\n" +
                    "Press 'SPACE' to reorient the cube";
    public static String freecamStates =
            "Press 'ESC' to reorient the\ncamera and exit Freecam Mode";

    public static String settingControls =
            "SETTING MODE:" +
                    "\nUse the mouse to view the cube from different angles\n" +
                    "Select a color by pressing the corresponding key,\n" +
                    "then click on a sticker to change the color.\n" +
                    "Press 'SPACE' to reorient the cube\n" +
                    "\nColors:" +
                    "\n'1' - Red" +
                    "\n'2' - Orange" +
                    "\n'3' - Yellow" +
                    "\n'4' - Green" +
                    "\n'5' - Blue" +
                    "\n'6' - White";
    public static String settingCubes =
            "Press 'S' to reset the cube\nPress 'X' to fill the cube with the current color";
    public static String settingStates =
            "Press 'ESC' to exit setting mode";

    public static String getSettingMode(SColor s){
        return "Current color: "+ s +
                "\nPress 'F' to move the camera\n" + settingCubes;
    }

    public static String solvingPrompt =
            "Do you want to generate a solution to this cube? (Y/N)";
    public static String solvingProcess =
            "Please Wait: solving the puzzle...";

    public static String getSolution(ArrayList<Move> solution){
        return "Solution:\n" +
                parseSolution(solution) +
                "\n Remember to hold the cube as shown below before solving. (Green center piece at front)" +
                "\nPress 'SPACE' to solve the cube and return to moving, or\npress 'ESC' to keep the cube scrambled.";
    }
    public static String parseSolution(ArrayList<Move> solution){
        StringBuilder builder = new StringBuilder();
        for(Move m: solution){
            switch(m){
                case UPRIME:
                    builder.append("U', ");
                    break;
                case RPRIME:
                    builder.append("R', ");
                    break;
                case DPRIME:
                    builder.append("D', ");
                    break;
                case FPRIME:
                    builder.append("F', ");
                    break;
                case BPRIME:
                    builder.append("B', ");
                    break;
                case LPRIME:
                    builder.append("L', ");
                    break;
                case U2:
                    builder.append("U2, ");
                    break;
                case R2:
                    builder.append("R2, ");
                    break;
                case D2:
                    builder.append("D2, ");
                    break;
                case F2:
                    builder.append("F2, ");
                    break;
                case B2:
                    builder.append("B2, ");
                    break;
                case L2:
                    builder.append("L2, ");
                    break;
                default:
                    builder.append(m);
                    builder.append(", ");
                    break;
            }
        }
        int len = builder.length();
        if(len!=0){
            builder.delete(len-2, len-1);
        }
        return builder.toString();
    }

    public static String invalid =
            "Scramble invalid or unsolvable! Please try setting the scramble again." +
                    "\nPress 'SPACE' to return.";
    public static String presolved =
            "This cube is already solved!" +
                    "\nPress 'SPACE' to return.";



}

