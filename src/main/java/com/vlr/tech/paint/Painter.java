/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlr.tech.paint;

/**
 *
 * @author Vignesh
 */
public final class Painter {

    protected int width;
    protected int height;
    protected char[][] canvas;

    protected final char vert = '-';
    protected final char horz = '|';
    protected final char inline = 'X';

    protected int sRate = 1;

    public Painter(int width, int height) {
        setWidth(width);
        setHeight(height + 2);
        this.canvas = new char[getHeight()][getWidth()];
    }

    public void createCanvas() {
        try {
            for (int x = 0; x < height; x++) {
                for (int y = 0; y < width; y++) {
                    if (x == 0 || x == height - 1) {
                        canvas[x][y] = getVert();
                    } else if (x <= height - 2 && (y == 0 || y == width - 1)) {
                        canvas[x][y] = getHorz();
                    }
                }
            }
            printCanvas();
        } catch (Exception ex) {
            setsRate(503);
        }
    }

    public void drawInLine(int x1, int y1, int x2, int y2) {
        try {
            if (validateCanvas()) {
                if (validate(x1, y1, x2, y2)) {
                    for (int x = 0; x < height; x++) {
                        for (int y = 0; y < width; y++) {
                            if ((x >= y1 && y >= x1) && (x <= y2 && y <= x2)) {
                                canvas[x][y] = inline;
                            }
                        }
                    }
                    printCanvas();
                } else {
                    setsRate(508);
                }
            } else {
                setsRate(507);
            }

        } catch (Exception ex) {
            setsRate(504);
        }
    }

    public void drawRectangle(int rx1, int ry1, int rx2, int ry2) {
        try {
            if (validateCanvas()) {
                if (validate(rx1, ry1, rx2, ry2)) {
                    for (int i = ry1; i <= ry2; i++) {
                        for (int j = rx1; j <= rx2; j++) {
                            if (i == ry1 || i == ry2 || j == rx1 || j == rx2) {
                                canvas[i][j] = inline;
                            }
                        }
                    }
                    printCanvas();
                } else {
                    setsRate(508);
                }
            } else {
                setsRate(507);
            }
        } catch (Exception ex) {
            setsRate(505);
        }
    }

    public void fillColor(int x, int y, char ch) {
        try {
            if (validateCanvas()) {
                if (canvas[y][x] != 0) {
                    return;
                }

                if (x > 0 || x < height || y > 0 || y < width) {
                    if (canvas[y][x] == 0) {
                        canvas[y][x] = ch;
                    }
                    fillColor(x + 1, y, ch);
                    fillColor(x - 1, y, ch);
                    fillColor(x, y - 1, ch);
                    fillColor(x, y + 1, ch);
                }
            } else {
                setsRate(507);
            }
        } catch (Exception ex) {
            setsRate(506);
        }

    }

    public void printCanvas() {
        for (int m = 0; m < height; m++) {
            for (int n = 0; n < width; n++) {
                System.out.print(canvas[m][n] == 0 ? ' ' : canvas[m][n]);
            }
            System.out.println();
        }
    }

    boolean validateCanvas() {
        return canvas.length != 0;
    }

    boolean validate(int x1, int y1, int x2, int y2) {
        return x1 < width && y1 < height && x2 < width && y2 < height;
    }

    boolean validateRectInput(int x1, int y1, int x2, int y2) {
        return x1 < width && y1 < height && x2 < width && y2 < height;//&& x1 <= x2 && y1 <= y2;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public char[][] getCanvas() {
        return canvas;
    }

    public void setCanvas(char[][] canvas) {
        this.canvas = canvas;
    }

    public char getVert() {
        return vert;
    }

    public char getHorz() {
        return horz;
    }

    public int getsRate() {
        return sRate;
    }

    public void setsRate(int sRate) {
        this.sRate = sRate;
    }
}
