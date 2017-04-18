package bap0031.comp3710.csse.eng.auburn.edu.controller;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.widget.GridLayout;

import java.util.List;

import bap0031.comp3710.csse.eng.auburn.edu.R;
import bap0031.comp3710.csse.eng.auburn.edu.model.GameGrid;
import bap0031.comp3710.csse.eng.auburn.edu.model.Tile;
import bap0031.comp3710.csse.eng.auburn.edu.view.TileView;

/**
 * Created by bradley on 4/15/17
 */

public class Grid2048Controller {


    private Context context;
    private GameGrid grid;
    private GridLayout gridLayout;

    public Grid2048Controller(GridLayout gridLayout, Context context) {
        this.gridLayout = gridLayout;
        this.context = context;
        grid = new GameGrid();
    }

    public GridLayout refreshGridLayout() {
        List<Tile> tiles = grid.toList();
        for (int i = 0; i < tiles.size(); i++) {
            if (tiles.get(i).isActive()) {
                Tile t = tiles.get(i);
                GridLayout.LayoutParams lp = new GridLayout.LayoutParams(GridLayout.spec(t.getRow()), GridLayout.spec(t.getColoumn()));
                TileView tv = new TileView(context);
                tv.setText(t.getValue() + "");
                GradientDrawable drawable = (GradientDrawable) context.getDrawable(R.drawable.layout_tile_background);
                chooseColor(drawable, t.getValue());
                tv.setBackground(drawable);
                gridLayout.addView(tv, lp);
            } else {
                try {
                    gridLayout.removeViewAt(i);
                } catch (NullPointerException e) {
                }
            }
        }
        gridLayout.invalidate();
        return gridLayout;
    }

    private void chooseColor(Drawable drawable, int value) {
        Resources mResources = context.getResources();
        switch (value) {
            case 2:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum2, context.getTheme()), PorterDuff.Mode.OVERLAY);
                break;
            case 4:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum4, context.getTheme()), PorterDuff.Mode.OVERLAY);
                break;
            case 8:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum8, context.getTheme()), PorterDuff.Mode.OVERLAY);
                break;
            case 16:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum16, context.getTheme()), PorterDuff.Mode.OVERLAY);
                break;
            case 32:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum32, context.getTheme()), PorterDuff.Mode.OVERLAY);
                break;
            case 64:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum64, context.getTheme()), PorterDuff.Mode.OVERLAY);
                break;
            case 128:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum128, context.getTheme()), PorterDuff.Mode.OVERLAY);
                break;
            case 256:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum256, context.getTheme()), PorterDuff.Mode.OVERLAY);
                break;
            case 512:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum512, context.getTheme()), PorterDuff.Mode.OVERLAY);
                break;
            case 1024:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum1024, context.getTheme()), PorterDuff.Mode.OVERLAY);
                break;
            case 2048:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum2048, context.getTheme()), PorterDuff.Mode.OVERLAY);
                break;
        }
    }

    public void shiftLeft() {
        grid.leftShift();
        grid.combineTilesLeft();
        grid.addNewNumber();
    }

    public void shiftUp() {
        grid.upShift();
        grid.addNewNumber();
    }

    public void shiftDown() {
        grid.downShift();
        grid.addNewNumber();
    }

    public void shiftRight() {
        grid.rightShift();
        grid.addNewNumber();
    }

}
