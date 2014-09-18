package teo.ram.css.myexamplesrandom;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by css on 9/14/14.
 */
public class ObjectAnimator2Activity extends Activity implements ValueAnimator.AnimatorUpdateListener {
    TextView t0, t1, t2,t3,t4,t5,t6;
    public AnimatorSet animatorSet = new AnimatorSet();
    public Handler hand;
    public ArrayList<TextView> textViewArray = new ArrayList<TextView>();
    public ArrayList<ObjectAnimator> animatorArrayList = new ArrayList<ObjectAnimator>();
    public ObjectAnimator anim0=null, anim1, anim2, anim3, anim4, anim5, anim6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectanimatoractivity2);


        t0 = (TextView) findViewById(R.id.textViewId0);
        t1 = (TextView) findViewById(R.id.textViewId1);
        t2 = (TextView) findViewById(R.id.textViewId2);
        t3 = (TextView) findViewById(R.id.textViewId3);
        t4 = (TextView) findViewById(R.id.textViewId4);
        t5 = (TextView) findViewById(R.id.textViewId5);
        t6 = (TextView) findViewById(R.id.textViewId6);


        textViewArray.add(t0);
        textViewArray.add(t1);
        textViewArray.add(t2);
        textViewArray.add(t3);
        textViewArray.add(t4);
        textViewArray.add(t5);
        textViewArray.add(t6);


        startAnimation();

//        hand = new Handler()         {
//            @Override
//            public void handleMessage(Message msg) {
//                /// here change the visibility
//                Log.i("HANDLER", "Changes the VISIBILITY");
//                t1.setVisibility(View.VISIBLE);
//                super.handleMessage(msg);
//            }
//
//        };


//        ObjectAnimator translateX = ObjectAnimator.ofFloat(t1 , "translationX", 0f, 100f);
//        ObjectAnimator translateY = ObjectAnimator.ofFloat(t1 , "translationY", 0f, 100f);

//        if( t1.getVisibility() == View.GONE) {
//            Log.i("Visibility", "Now is visible");
//            t1.setVisibility(View.VISIBLE);
//            t1.animate().x(480f).setDuration(1800);
//        }

    }



    public void createAnimation() {

//        String name = objArray.get(0).getClass().getName();
//        Log.i("NAME", "Name == " + name);

        anim0  = ObjectAnimator.ofFloat(textViewArray.get(0), "translationX", 0f, 480f);
        anim1  = ObjectAnimator.ofFloat(textViewArray.get(1), "translationX", 0f, 480f);
        anim2  = ObjectAnimator.ofFloat(textViewArray.get(2), "translationX", 0f, 480f);
        anim3  = ObjectAnimator.ofFloat(textViewArray.get(3), "translationX", 0f, 480f);
        anim4  = ObjectAnimator.ofFloat(textViewArray.get(4), "translationX", 0f, 480f);
        anim5  = ObjectAnimator.ofFloat(textViewArray.get(5), "translationX", 0f, 480f);
        anim6  = ObjectAnimator.ofFloat(textViewArray.get(6), "translationX", 0f, 480f);

        anim0.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator arg0) {
                Log.i("animationStart", "AnimationStart --- anim 0");
                textViewArray.get(0).setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animator arg0) {}

            @Override
            public void onAnimationEnd(Animator arg0) {
                textViewArray.get(0).setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator arg0) {}
        });

        anim1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator arg0) {
                Log.i("animationStart", "AnimationStart --- anim 1");
                textViewArray.get(1).setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationRepeat(Animator arg0) {}

            @Override
            public void onAnimationEnd(Animator arg0) {
                textViewArray.get(1).setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator arg0) {}
        });

        anim2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator arg0) {
                Log.i("animationStart", "AnimationStart --- anim 2");
                textViewArray.get(2).setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                textViewArray.get(2).setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator arg0) {
            }
        });

        anim3.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator arg0) {
                Log.i("animationStart", "AnimationStart --- anim 3");
                textViewArray.get(3).setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationRepeat(Animator arg0) {}

            @Override
            public void onAnimationEnd(Animator arg0) {
                textViewArray.get(3).setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator arg0) {}
        });


        animatorArrayList.add(anim0);
        animatorArrayList.add(anim1);
        animatorArrayList.add(anim2);
        animatorArrayList.add(anim3);
        animatorArrayList.add(anim4);
        animatorArrayList.add(anim5);
        animatorArrayList.add(anim6);

//
//        for (ObjectAnimator anim : animatorArrayList) {
//            int counter=0;
//            long offset = 500l;
//            anim.setDuration(3000l);
//            anim.setStartDelay(2000);
//            anim.setRepeatCount(10);
//            counter++;
//        }

        anim3.setDuration(4000);
        anim3.setStartDelay(500);

        anim2.setDuration(4000);
        anim2.setStartDelay(1500);

        anim1.setDuration(4000);
        anim1.setStartDelay(3000);


        animatorSet.play(anim3).with(anim2).with(anim1);
//        animatorSet.play(anim2).after(3000);
//        animatorSet.play(anim1).after(3000);
//        animatorSet.play(anim0).after(3000);
        animatorSet.start();

//        animatorSet.playTogether(anim0, anim1, anim2,
//                    anim3, anim4, anim5, anim6);

    }


    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
    }

    public void  startAnimation() {
        createAnimation();
        animatorSet.start();
    }

}
