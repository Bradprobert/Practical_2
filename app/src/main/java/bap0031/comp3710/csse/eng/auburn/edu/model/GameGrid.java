package bap0031.comp3710.csse.eng.auburn.edu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bradley on 4/7/17
 */

public class GameGrid {

    private int[][] grid;
    private boolean[][] activeCells;
    private int numActiveCells;
    private int totalScore;

    public GameGrid() {
        grid = new int[4][4];
        activeCells = new boolean[4][4];
        numActiveCells = 0;
        totalScore = 0;
        initializeGrid();
    }

    public GameGrid(List<Tile> list, int totalScore) {
        this.totalScore = totalScore;

        grid = new int[4][4];
        activeCells = new boolean[4][4];
        numActiveCells = 0;
        for (Tile t : list) {
            grid[t.getRow()][t.getColoumn()] = t.getValue();
            activeCells[t.getRow()][t.getColoumn()] = t.isActive();
            numActiveCells++;
        }
    }

    private void initializeGrid() {
        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            int row = random.nextInt(4);
            int column = random.nextInt(4);

            int startVal;
            if (random.nextInt(100) < 75)
                startVal = 2;
            else
                startVal = 4;

            while (grid[row][column] != 0) {
                row = random.nextInt(4);
                column = random.nextInt(4);
            }
            grid[row][column] = startVal;
            activeCells[row][column] = true;
        }
        numActiveCells = 2;
    }

    public void leftShift() {
        for (int i = 0; i < 4; i++) {
            //slide tiles left
            int index = 0;
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] != 0) {
                    grid[i][index] = grid[i][j];
                    grid[i][j] = (j != index ? 0 : grid[i][j]);
                    activeCells[i][index] = true;
                    activeCells[i][j] = (j == index && activeCells[i][j]);
                    index++;
                }
            }
        }
    }

    public void combineTilesLeft() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == grid[i][j + 1]) {
                    grid[i][j] = grid[i][j] * 2;
                    totalScore += grid[i][j];
                    grid[i][j + 1] = 0;
                    activeCells[i][j + 1] = false;
                    numActiveCells--;
                }
            }
        }
        leftShift();
    }

    public void upShift() {
        for (int i = 0; i < 4; i++) {
            int index = 0;
            for (int j = 0; j < 4; j++) {
                if (grid[j][i] != 0) {
                    grid[index][i] = grid[j][i];
                    grid[j][i] = (j != index ? 0 : grid[j][i]);
                    activeCells[index][i] = true;
                    activeCells[j][i] = (j == index && activeCells[j][i]);
                    index++;
                }
            }
        }
    }

    public void combineTilesUp() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[j][i] == grid[j + 1][i]) {
                    grid[j][i] = grid[j][i] * 2;
                    totalScore += grid[j][i];
                    grid[j + 1][i] = 0;
                    activeCells[j + 1][i] = false;
                    numActiveCells--;
                }
            }
        }
        upShift();
    }

    public void rightShift() {
        for (int i = 0; i < 4; i++) {
            int index = 3;
            for (int j = 3; j >= 0; j--) {
                if (grid[i][j] != 0) {
                    grid[i][index] = grid[i][j];
                    grid[i][j] = (j != index ? 0 : grid[i][j]);
                    activeCells[i][index] = true;
                    activeCells[i][j] = (j == index && activeCells[i][j]);
                    index--;
                }
            }
        }
    }

    public void combineTilesRight() {
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                if (grid[i][j] == grid[i][j - 1]) {
                    grid[i][j] = grid[i][j] * 2;
                    totalScore += grid[i][j];
                    grid[i][j - 1] = 0;
                    activeCells[i][j - 1] = false;
                    numActiveCells--;
                }
            }
        }
        rightShift();
    }

    public void downShift() {
        for (int i = 0; i < 4; i++) {
            int index = 3;
            for (int j = 3; j >= 0; j--) {
                if (grid[j][i] != 0) {
                    grid[index][i] = grid[j][i];
                    grid[j][i] = (j != index ? 0 : grid[j][i]);
                    activeCells[index][i] = true;
                    activeCells[j][i] = (j == index && activeCells[j][i]);
                    index--;
                }
            }
        }
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void combineTilesDown() {
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                if (grid[j][i] == grid[j - 1][i]) {
                    grid[j][i] = grid[j][i] * 2;
                    totalScore += grid[j][i];
                    grid[j - 1][i] = 0;
                    activeCells[j - 1][i] = false;
                    numActiveCells--;
                }
            }
        }
        downShift();
    }

    public boolean addNewNumber() {
        if (numActiveCells < 16) {
            Random rand = new Random();
            int row = rand.nextInt(4);
            int column = rand.nextInt(4);
            while (activeCells[row][column]) {
                row = rand.nextInt(4);
                column = rand.nextInt(4);
            }

            int startVal = rand.nextInt(100) < 75 ? 2 : 4;

            grid[row][column] = startVal;
            activeCells[row][column] = true;
            numActiveCells++;

            return true;
        } else
            return false;
    }

    public int[][] getGrid() {
        return grid;
    }

    public List<Tile> toList() {
        List<Tile> list = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                list.add(new Tile(grid[i][j], i, j, activeCells[i][j]));
            }
        }
        return list;
    }
}
