package teo.ram.css.myexamplesrandom.viewpager;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;


/**
 * Created by css on 10/12/14.
 */
public class CarouselScroller extends Scroller {

    private int mDuration = 5000;

    public CarouselScroller(Context context) {
        super(context);
    }

    public CarouselScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public CarouselScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }


    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
}