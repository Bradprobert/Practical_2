package bap0031.comp3710.csse.eng.auburn.edu;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bradley on 4/7/17
 */

public class GameGridTest {
    @Test
    public void grid_initializes_correctly() throws Exception {
        GameGrid gameGrid = new GameGrid();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(gameGrid.getGrid()[i][j] + ", ");
            }
        }
    }
}
