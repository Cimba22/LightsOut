package com.cimba.lightsout;

import com.cimba.lightsout.observer.LightsOutObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LightsOut {
    private final List<LightsOutObserver> observers = new ArrayList<>();

    private final boolean[][] grid;

    public void addObserver(LightsOutObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(LightsOutObserver observer) {
        observers.remove(observer);
    }
    public LightsOut(int size) {
        grid = new boolean[size][size];
        for (boolean[] row : grid) {
            Arrays.fill(row, false);
        }
    }

    public String toString() {
        return getString(grid);
    }

    public static String getString(boolean[][] grid) {
        StringBuilder s = new StringBuilder();
        for (boolean[] booleans : grid) {
            for (boolean aBoolean : booleans) {
                if (aBoolean) {
                    s.append("#");
                } else {
                    s.append(".");
                }
            }
            s.append("\n");
        }
        return s.toString();
    }


    public void randomMoves(int n) {
        Random random = new Random();
        int size = grid.length;

        for (int i = 0; i < n; i++) {
            int randomX = random.nextInt(size);
            int randomY = random.nextInt(size);
            toggle(randomX, randomY);
        }
    }

    public void toggle(int x, int y) {
        toggleCell(x, y);
        toggleCell(x + 1, y);
        toggleCell(x - 1, y);
        toggleCell(x, y + 1);
        toggleCell(x, y - 1);

        notifyObservers();
    }

    private void notifyObservers() {
        for (LightsOutObserver observer : observers) {
            observer.updateLights();
        }
    }

    private void toggleCell(int x, int y) {
        if (isValidCell(x, y)) {
            grid[x][y] = !grid[x][y];
        }
    }

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[x].length;
    }


    public boolean isSolved() {
        for (boolean[] booleans : grid) {
            for (boolean aBoolean : booleans) {
                if (aBoolean) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean getState(int x, int y) {
        return grid[x][y];
    }

    public int getSize() {
        return grid.length;
    }
}
