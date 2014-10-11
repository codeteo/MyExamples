package teo.ram.css.myexamplesrandom.viewpager;

import android.animation.ObjectAnimator;

/**
 * Created by css on 10/11/14.
 */
public class AnimationEvent  {
    private ObjectAnimator animator;

    public AnimationEvent(CarouselLinearLayout root) {
        animator = ObjectAnimator.ofFloat(root, "y", 20f);
        animator.setDuration(500);
        animator.start();
    }
}
