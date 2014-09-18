package teo.ram.css.myexamplesrandom;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

/**
 * Created by css on 9/17/14.
 */
public class ViewAnimationRepeat extends Activity{
    public TextView textViewId00;

    public static final String TAG = "ViewAnimationRepeat".toUpperCase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation_repeat);

        textViewId00 = (TextView) findViewById(R.id.textViewId00);

//
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textViewId00, "translationX", -120f, 480f);
//        objectAnimator.setStartDelay(500);


        animateView();

//        anim0  = ObjectAnimator.ofFloat(textViewId00, "translationX", -120f, 480f);
//        anim0.setDuration(2000);
//        anim0.setRepeatCount(5);
//        anim0.setRepeatMode(ValueAnimator.RESTART);
//        anim0.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                Log.i(TAG, "anim0 onAnimationStart ");
//                textViewId00.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                Log.i(TAG, "anim0 onAnimationEND ");
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//                Log.i(TAG, "anim0 onAnimation REPEAT ");
//                anim0.setStartDelay(5000);
//            }
//        });
//        anim0.start();

    }

    public void animateView() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        final int screenWidth = size.x;

        final ObjectAnimator anim0 = ObjectAnimator.ofFloat(textViewId00, "translationX", -120f, screenWidth);
        anim0.setDuration(2000);
        anim0.addListener(new Animator.AnimatorListener() {

            int count=0;

            @Override
            public void onAnimationStart(Animator animation) {
                textViewId00.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (++count < 5) {
                    anim0.setStartDelay(5000);
                    anim0.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        anim0.start();
    }

}
