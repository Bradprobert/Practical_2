package bap0031.comp3710.csse.eng.auburn.edu.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by bradley on 4/13/17
 */

public class TileView extends AppCompatTextView {
    public TileView(Context context) {
        super(context);
    }

    public TileView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TileView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
