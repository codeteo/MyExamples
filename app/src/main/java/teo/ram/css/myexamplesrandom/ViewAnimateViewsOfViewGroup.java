package teo.ram.css.myexamplesrandom;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

/**
 * Created by css on 9/16/14.
 */
public class ViewAnimateViewsOfViewGroup extends Activity{
    public ObjectAnimator anim0, anim1, anim2, anim3;
    public TextView textViewId0, textViewId1, textViewId2, textViewId3;

    boolean firstTime= true;

    public static final String TAG = "ViewAnimateViewsOfViewGroup".toUpperCase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate_views_of_viewgroup);


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Log.i(TAG, "width=  " + width + " height= " + height);


        textViewId0 = (TextView) findViewById(R.id.textViewId0);
        textViewId1 = (TextView) findViewById(R.id.textViewId1);
        textViewId2 = (TextView) findViewById(R.id.textViewId2);
        textViewId3 = (TextView) findViewById(R.id.textViewId3);



        anim0  = ObjectAnimator.ofFloat(textViewId0, "translationX", -120f, 480f);
        anim0.setDuration(8000);
        anim0.setRepeatCount(5);
        anim0.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.i(TAG, "anim0 onAnimationStart " );
                textViewId0.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i(TAG, "anim0 onAnimationEND " );
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.i(TAG, "anim0 onAnimation REPEAT " );
                animation.setStartDelay(8000);
            }
        });
        anim0.start();



        anim1  = ObjectAnimator.ofFloat(textViewId1, "translationX", -120f, 480f);
        anim1.setDuration(8000);
        anim1.setStartDelay(4000);
        anim1.setRepeatCount(5);
        anim1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.i(TAG, "anim1 onAnimationStart " );
                textViewId1.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.i(TAG, "anim1 onAnimation REPEAT " );
                anim1.setStartDelay(8000);
            }
        });
        anim1.start();



        anim2  = ObjectAnimator.ofFloat(textViewId2, "translationX", -120f, 480f);
        anim2.setDuration(8000);
        anim2.setStartDelay(8000);
        anim2.setRepeatCount(5);
        anim2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.i(TAG, "anim2 onAnimationStart ");
                textViewId2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.i(TAG, "anim2 onAnimation REPEAT " );
                anim2.setStartDelay(8000);
            }
        });
        anim2.start();



        anim3  = ObjectAnimator.ofFloat(textViewId3, "translationX", -120f, 480f);
        anim3.setDuration(8000);
        anim3.setRepeatCount(5);
        anim3.setStartDelay(12000);
        anim3.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.i(TAG, "anim3 onAnimationStart " );
                textViewId3.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                anim3.setStartDelay(8000);
            }
        });
        anim3.start();



    }
}
