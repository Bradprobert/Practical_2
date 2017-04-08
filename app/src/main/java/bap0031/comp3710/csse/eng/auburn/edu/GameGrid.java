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

            int startValue;
            if (random.nextInt(100) < 75)
                startValue = 2;
            else
                startValue = 4;

            grid[row][column] = startValue;
        }
    }

    public int[][] getGrid() {
        return grid;
    }
}
