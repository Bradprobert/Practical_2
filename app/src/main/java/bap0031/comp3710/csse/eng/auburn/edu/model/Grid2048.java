package bap0031.comp3710.csse.eng.auburn.edu.model;

import java.util.Random;

/**
 * Created by bradley on 4/11/17
 */

public class Grid2048 {

    private Tile[][] grid;
    private int numActiveCells;

    public Grid2048() {
        initializeGrid();
    }

    public Grid2048(Tile[][] grid, int numActiveCells) {
        this.grid = grid;
        this.numActiveCells = numActiveCells;
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

            while (grid[row][column].getValue() != 0) {
                row = random.nextInt(4);
                column = random.nextInt(4);
            }
            grid[row][column].setValue(startVal);
            grid[row][column].setActive(true);
        }
        numActiveCells = 2;
    }

    public void shiftLeft() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int before = 0;
                while (i - before >= 0 && !grid[i - before][j].isActive()) {
                    grid[i - before][j] = grid[i - before + 1][j];
                    before++;
                }
            }
        }
    }
}
