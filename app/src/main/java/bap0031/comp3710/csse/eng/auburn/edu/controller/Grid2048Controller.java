package bap0031.comp3710.csse.eng.auburn.edu.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
    private TextView scoreTv;

    public Grid2048Controller(GridLayout gridLayout, TextView scoreTv, Context context) {
        this.gridLayout = gridLayout;
        this.scoreTv = scoreTv;
        this.context = context;
    }

    public void refreshScore() {
        scoreTv.setText("Score: " + grid.getTotalScore());
    }

    public void refreshGridLayout() {
        List<Tile> tiles = grid.toList();
        gridLayout.removeAllViews();

        for (int i = 0; i < tiles.size(); i++) {
            Tile t = tiles.get(i);
            GridLayout.LayoutParams lp = new GridLayout.LayoutParams(GridLayout.spec(t.getRow()), GridLayout.spec(t.getColoumn()));
            setupLayoutParams(lp);
            TileView tv = new TileView(context);
            if (tiles.get(i).isActive()) {
                tv.setText(t.getValue() + "");
                GradientDrawable drawable = (GradientDrawable) context.getDrawable(R.drawable.layout_tile_background);
                chooseColor(drawable, t.getValue());

                tv.setBackground(drawable);
                gridLayout.addView(tv, lp);
            } else {
                tv.setVisibility(View.INVISIBLE);
                gridLayout.addView(tv, lp);
            }
        }
        gridLayout.invalidate();
    }

    private void setupLayoutParams(GridLayout.LayoutParams lp) {
        lp.height = context.getResources().getDimensionPixelSize(R.dimen.tile_height);
        lp.width = context.getResources().getDimensionPixelSize(R.dimen.tile_width);
        lp.bottomMargin = context.getResources().getDimensionPixelSize(R.dimen.tile_margin);
        lp.rightMargin = context.getResources().getDimensionPixelSize(R.dimen.tile_margin);
        lp.topMargin = context.getResources().getDimensionPixelSize(R.dimen.tile_margin);
        lp.leftMargin = context.getResources().getDimensionPixelSize(R.dimen.tile_margin);
    }

    private void chooseColor(Drawable drawable, int value) {
        Resources mResources = context.getResources();
        switch (value) {
            case 2:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum2), PorterDuff.Mode.OVERLAY);
                break;
            case 4:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum4), PorterDuff.Mode.OVERLAY);
                break;
            case 8:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum8), PorterDuff.Mode.OVERLAY);
                break;
            case 16:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum16), PorterDuff.Mode.OVERLAY);
                break;
            case 32:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum32), PorterDuff.Mode.OVERLAY);
                break;
            case 64:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum64), PorterDuff.Mode.OVERLAY);
                break;
            case 128:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum128), PorterDuff.Mode.OVERLAY);
                break;
            case 256:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum256), PorterDuff.Mode.OVERLAY);
                break;
            case 512:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum512), PorterDuff.Mode.OVERLAY);
                break;
            case 1024:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum1024), PorterDuff.Mode.OVERLAY);
                break;
            case 2048:
                drawable.setColorFilter(mResources.getColor(R.color.colorTileNum2048), PorterDuff.Mode.OVERLAY);
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
        grid.combineTilesUp();
        grid.addNewNumber();
    }

    public void shiftDown() {
        grid.downShift();
        grid.combineTilesDown();
        grid.addNewNumber();
    }

    public void shiftRight() {
        grid.rightShift();
        grid.combineTilesRight();
        grid.addNewNumber();
    }

    public void saveState() {
        SharedPreferences sp = context.getSharedPreferences("gameState", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(grid.toList());
        editor.putString("list", json);
        editor.putInt("totalScore", grid.getTotalScore());
        editor.commit();
    }

    public void resumeState() {
        SharedPreferences sp = context.getSharedPreferences("gameState", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("list", "");

        List<Tile> list = gson.fromJson(json, new TypeToken<List<Tile>>() {
        }.getType());
        int score = sp.getInt("totalScore", 0);
        if (list == null) {
            grid = new GameGrid();
        } else {
            grid = new GameGrid(list, score);

        }
    }

    public void reset() {
        grid = new GameGrid();
        refreshGridLayout();
        refreshScore();
    }
}
