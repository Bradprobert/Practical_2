package bap0031.comp3710.csse.eng.auburn.edu.controller;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import java.util.List;

import bap0031.comp3710.csse.eng.auburn.edu.R;
import bap0031.comp3710.csse.eng.auburn.edu.model.Tile;
import bap0031.comp3710.csse.eng.auburn.edu.view.TileView;

/**
 * Created by bradley on 4/13/17
 */

public class Grid2048Adapter extends ArrayAdapter<Tile> {

    private Context context;
    private LayoutInflater inflater;
    private int resource, tileViewResourceId;
    private List<Tile> objects;
    private Resources resources;

    public Grid2048Adapter(@NonNull Context context, @LayoutRes int resource, @IdRes int tileViewResourceId, @NonNull List<Tile> objects) {
        super(context, resource, tileViewResourceId, objects);
        this.context = context;
        this.resource = resource;
        this.tileViewResourceId = tileViewResourceId;
        this.objects = objects;
        this.inflater = LayoutInflater.from(context);
        resources = context.getResources();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = (RelativeLayout) inflater.inflate(resource, null);

        Tile tile = getItem(position);
        TileView tv = (TileView) convertView.findViewById(tileViewResourceId);

        setTileColor(tv, position);

        return convertView;
    }

    private void setTileColor(TileView tv, int position) {
        Tile tile = getItem(position);
        GradientDrawable drawable = (GradientDrawable) resources.getDrawable(R.drawable.layout_tile_background, null);
        switch (tile.getValue()) {
            case 2:
                drawable.setColor(resources.getColor(R.color.colorTileNum2, context.getTheme()));
                break;
            case 4:
                drawable.setColor(resources.getColor(R.color.colorTileNum4, context.getTheme()));
                break;
            case 8:
                drawable.setColor(resources.getColor(R.color.colorTileNum8, context.getTheme()));
                break;
            case 16:
                drawable.setColor(resources.getColor(R.color.colorTileNum16, context.getTheme()));
                break;
            case 32:
                drawable.setColor(resources.getColor(R.color.colorTileNum32, context.getTheme()));
                break;
            case 64:
                drawable.setColor(resources.getColor(R.color.colorTileNum64, context.getTheme()));
                break;
            case 128:
                drawable.setColor(resources.getColor(R.color.colorTileNum128, context.getTheme()));
                break;
            case 256:
                drawable.setColor(resources.getColor(R.color.colorTileNum256, context.getTheme()));
                break;
            case 512:
                drawable.setColor(resources.getColor(R.color.colorTileNum512, context.getTheme()));
                break;
            case 1024:
                drawable.setColor(resources.getColor(R.color.colorTileNum1024, context.getTheme()));
                break;
            case 2048:
                drawable.setColor(resources.getColor(R.color.colorTileNum2048, context.getTheme()));
                break;

        }
    }
}
