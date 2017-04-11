package bap0031.comp3710.csse.eng.auburn.edu;

import java.util.Random;

/**
 * Created by bradley on 4/7/17
 */

public class GameGrid {

    private int[][] grid;

    public GameGrid() {
        grid = new int[4][4];
        initializeGrid();
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
        }
    }

    public void leftShift() {
        for (int i = 0; i < 4; i++) {
            int index = 0;
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] != 0) {
                    grid[i][index] = grid[i][j];
                    grid[i][j] = (j != index ? 0 : grid[i][j]);
                    index++;
                }
            }
        }
    }

    public void upShift() {
        for (int i = 0; i < 4; i++) {
            int index = 0;
            for (int j = 0; j < 4; j++) {
                if (grid[j][i] != 0) {
                    grid[index][i] = grid[j][i];
                    grid[j][i] = (j != index ? 0 : grid[j][i]);
                    index++;
                }
            }
        }
    }

    public void rightShift() {
        for (int i = 0; i < 4; i++) {
            int index = 3;
            for (int j = 3; j >= 0; j--) {
                if (grid[i][j] != 0) {
                    grid[i][index] = grid[i][j];
                    grid[i][j] = (j != index ? 0 : grid[i][j]);
                    index--;
                }
            }
        }
    }

    public void downShift() {
        for (int i = 0; i < 4; i++) {
            int index = 3;
            for (int j = 3; j >= 0; j--) {
                if (grid[j][i] != 0) {
                    grid[index][i] = grid[j][i];
                    grid[j][i] = (j != index ? 0 : grid[j][i]);
                    index--;
                }
            }
        }
    }

    public int[][] getGrid() {
        return grid;
    }
}
