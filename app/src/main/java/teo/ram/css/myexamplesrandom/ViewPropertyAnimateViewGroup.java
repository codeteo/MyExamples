package teo.ram.css.myexamplesrandom;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by css on 9/16/14.
 */
public class ViewPropertyAnimateViewGroup extends Activity{
    public ObjectAnimator anim0 = null;
    public TextView textViewId0, textViewId1, textViewId2, textViewId3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate_viewgroup);

        LinearLayout ll = (LinearLayout) findViewById(R.id.containerID);
        int w = ll.getWidth();
        Log.i("width", "width= " + w);
        textViewId0 = (TextView) findViewById(R.id.textViewId0);
        textViewId1 = (TextView) findViewById(R.id.textViewId1);
        textViewId2 = (TextView) findViewById(R.id.textViewId2);
        textViewId3 = (TextView) findViewById(R.id.textViewId3);

        anim0  = ObjectAnimator.ofFloat(ll, "translationX", -480f, 480f);
        anim0.setDuration(8000);
        anim0.setStartDelay(1000);
        anim0.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                textViewId0.setVisibility(View.VISIBLE);
                textViewId1.setVisibility(View.VISIBLE);
                textViewId2.setVisibility(View.VISIBLE);
                textViewId3.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                int w1 = textViewId0.getWidth();
                Log.i("width", "width11=  " + w1);
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
