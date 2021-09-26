/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlr.tech.app;

import com.vlr.tech.paint.Painter;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Vignesh
 */
public class ConsolePainter {

    static String command;
    static Painter painter;

    static int state = 0;

    static final String CANVAS = "c";
    static final String LINE = "l";
    static final String RECTANGLE = "r";
    static final String BUCKETFILL = "b";
    static final String QUIT = "q";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        printHelp();
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter Command: ");

            command = input.nextLine().toLowerCase();

            state = validateInputCommand(command);

            String p[] = command.split(" ");
            String cmdType = command.substring(0, 1);

            switch (cmdType) {
                case CANVAS:
                    painter = new Painter(toInt(p[1]), toInt(p[2]));
                    painter.createCanvas();
                    state = painter.getsRate();
                    break;
                case LINE:
                    painter.drawInLine(toInt(p[1]), toInt(p[2]), toInt(p[3]), toInt(p[4]));
                    state = painter.getsRate();
                    break;
                case RECTANGLE:
                    painter.drawRectangle(toInt(p[1]), toInt(p[2]), toInt(p[3]), toInt(p[4]));
                    state = painter.getsRate();
                    break;
                case BUCKETFILL:
                    painter.fillColor(toInt(p[1]), toInt(p[2]), p[3].charAt(0));
                    painter.printCanvas();
                    state = painter.getsRate();
                    break;
                case QUIT:
                    state = 0;
                    break;
                default:
                    break;
            }

            ExitCode exitCode = ExitCode.getExitCodeResponse(state);
            System.out.println(state + "->" + exitCode.getMessage());
            if (state == 0) {
                System.exit(state);
            }
        }
    }

    private static int toInt(String p) {
        return Integer.parseInt(p);
    }

    private static int validateInputCommand(String cmd) {

        Pattern np = Pattern.compile("^[cC|lL|rR|qQ](\\w*)+(?:\\s+[0-9]+)*\\s\\d*$");
        Pattern npBucketFill = Pattern.compile("^[bB](\\w*)+(?:\\s+[a-zA-Z0-9]+)*\\s*$");

        String fc = cmd.substring(0, 1);

        if (cmd.equalsIgnoreCase(QUIT)) {
            return 0;
        }

        if (fc.matches("[a-zA-Z]+")) {
            if (fc.equalsIgnoreCase(BUCKETFILL)) {
                return npBucketFill.matcher(cmd).matches() ? 1 : 501;
            }
            return np.matcher(cmd).matches() ? 1 : 501;
        } else if (!fc.matches("[a-zA-Z]+")) {
            return 501;
        }

        String[] params = cmd.trim().split(" ");
        switch (fc) {
            case CANVAS:
                if (params.length != 3) {
                    return 502;
                }
                break;
            case LINE:
            case RECTANGLE:
                if (params.length != 5) {
                    return 502;
                }
                break;
            case BUCKETFILL:
                if (params.length != 4) {
                    return 502;
                }
                break;
            default:
                break;
        }
        return 1;
    }

    private boolean isValidNumericInput(String strNum) {
        //positive integers exclude zeros
        Pattern np = Pattern.compile("^[1-9]\\d*$");
        if (strNum == null) {
            return false;
        }
        return np.matcher(strNum).matches();
    }

    private static void printHelp() {
        String help = "-----------------------------------------------------------------------------------------------\n"
                + " Console Painter\n"
                + "-----------------------------------------------------------------------------------------------\n"
                + " 1. Create a new canvas\n"
                + " 2. Start drawing on the canvas by issuing various commands\n"
                + " 3. Quit\n"
                + "-----------------------------------------------------------------------------------------------\n"
                + " Command 	   Description\n"
                + "-----------------------------------------------------------------------------------------------\n"
                + " C w h          Should create a new canvas of width w and height h.\n"
                + "-----------------------------------------------------------------------------------------------\n"
                + " L x1 y1 x2 y2  Should create a new line from (x1,y1) to (x2,y2). Currently only\n"
                + "                horizontal or vertical lines are supported. Horizontal and vertical lines\n"
                + "                will be drawn using the 'x' character.\n"
                + "-----------------------------------------------------------------------------------------------\n"
                + " R x1 y1 x2 y2  Should create a new rectangle, whose upper left corner is (x1,y1) and\n"
                + "                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn\n"
                + "                using the 'x' character.\n"
                + "-----------------------------------------------------------------------------------------------\n"
                + " B x y c        Should fill the entire area connected to (x,y) with \"colour\" c. The\n"
                + "                behavior of this is the same as that of the \"bucket fill\" tool in paint\n"
                + "                programs.\n"
                + "-----------------------------------------------------------------------------------------------\n"
                + " Q              Should quit the program.\n"
                + "-----------------------------------------------------------------------------------------------\n";
        System.out.println(help);
    }

}
