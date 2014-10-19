package teo.ram.css.myexamplesrandom.myviewpager;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by csst0111 on 10/14/14.
 */
public class CurrentItem extends LinearLayout {
    private float scale = MainActivityViewPager.BIG_SCALE;

    public CurrentItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CurrentItem(Context context) {
        super(context);
    }

    public void setScaleBoth(float scale) {
        this.scale = scale;
        this.invalidate(); 	// Invalidate will call onDraw()
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // The main mechanism to display scale animation, you can customize it
        // as your needs
        int w = this.getWidth();
        int h = this.getHeight();
        canvas.scale(scale, scale, w/2, h/2);

        super.onDraw(canvas);
    }
}
