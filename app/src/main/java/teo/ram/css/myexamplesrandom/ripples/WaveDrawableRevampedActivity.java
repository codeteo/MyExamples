package teo.ram.css.myexamplesrandom.ripples;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.melnykov.fab.FloatingActionButton;

import teo.ram.css.myexamplesrandom.R;

/**
 * Created by css on 9/29/14.
 */
public class WaveDrawableRevampedActivity extends Activity {
    int screenWidth, screenHeight;
    Boolean flag = false, isHidden = false;
    private float fabX, fabY;


    FloatingActionButton fab;
    FrameLayout parent;

    LinearLayout newFrame;
    LinearLayout.LayoutParams  newFrame_params;


    RevampedWaveDrawable revampedWaveDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wavedrawablerevamped);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth= size.x;
        screenHeight= size.y;

        parent = (FrameLayout) findViewById(R.id.parentID2);
        fab = (FloatingActionButton) findViewById(R.id.button_floating_action2);

        newFrame = new LinearLayout(this);
        newFrame.setGravity(Gravity.BOTTOM);
        newFrame_params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHidden = true;
                if ( flag == false) {
                    Log.i("TAGTAGTAGTAG", " ************ adds to parent child view***********");
                    parent.addView(newFrame, newFrame_params);
                }
                flag = true;

                fabX = fab.getX();
                fabY  = fab.getY();

                float centerX = fabX + fab.getWidth() / 2;
                float centerY = fabY +  fab.getHeight() / 2;

                Log.i("CENTER", " centerX== " + centerX + "   centerY==" + centerY);

                revampedWaveDrawable = new RevampedWaveDrawable(Color.parseColor("#ff00897b"), 1500, centerX, centerY);
//                revampedWaveDrawable.setWaveInterpolator(new LinearInterpolator());

                if (revampedWaveDrawable != null) {
                    revampedWaveDrawable.startAnimation();
                }
                newFrame.setBackground(revampedWaveDrawable);
                // fab.animate().y(fabY + 200f).setDuration(500).start();

            }
        });


    }

    class RevampedWaveDrawable extends Drawable {
        private Paint revampedWavePaint;
        private int color;
        private int radius;
        protected int alpha;

        private float waveScale=0.1f;

        private ObjectAnimator alphaAnimator, waveAnimator;
        private ObjectAnimator animX, animY, animColor;

//        private Interpolator waveInterpolator;
//        private Interpolator alphaInterpolator;

        private Animator animator;
        private AnimatorSet animatorSet;
        private AnimatorSet animSetXY;
        private AnimatorSet animSetWave;

        private float x, y;

        public RevampedWaveDrawable(int color, int radius, float centerX, float centerY) {
            this.color = color;
            this.radius = radius;
            this.x = centerX;
            this.y = centerY;

            this.alpha = 255;

            revampedWavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            animatorSet = new AnimatorSet();

        }

        @Override
        public void draw(Canvas canvas) {
            revampedWavePaint.setStyle(Paint.Style.FILL);
            revampedWavePaint.setColor(color);
            revampedWavePaint.setAlpha(alpha);

//            Log.i("draw", "radius*waveScale== " + radius * waveScale);
            canvas.drawCircle(x, y, radius * waveScale, revampedWavePaint);
        }


        private Animator generateAnimation() {

            Log.i("VALUES", " x= " + x + "   screenWidth== " + screenWidth/2);

            animX = ObjectAnimator.ofFloat(this, "x", x, screenWidth/2);
            animY = ObjectAnimator.ofFloat(this, "y", y, screenHeight/2);
//            alphaAnimator = ObjectAnimator.ofInt(this, "alpha", alpha, 50);
            animX.setDuration(1000);
            animY.setDuration(1000);
//            alphaAnimator.setDuration(1000);



            // ******* next AnimatorSet **** //
            waveAnimator = ObjectAnimator.ofFloat(this, "waveScale", 0.1f, 0.5f);
            alphaAnimator = ObjectAnimator.ofInt(this, "alpha", 255, 155);
            if (alphaAnimator != null) {
                alphaAnimator.setInterpolator(new LinearInterpolator());
            }
            alphaAnimator.setDuration(1500);
            waveAnimator.setDuration(1500);


            animSetXY = new AnimatorSet();
            animSetWave = new AnimatorSet();
            animSetXY.playTogether(animX, animY);
            animSetWave.playTogether(waveAnimator, alphaAnimator);


            animatorSet.play(animSetXY).before(animSetWave);

            return animatorSet;
        }


        public void startAnimation() {
            animator = generateAnimation();
            animator.start();
        }

        public void setWaveInterpolator(Interpolator interpolator) {
//            this.waveInterpolator = interpolator;
        }


        @Override
        public int getOpacity() {
            return  0;
//            return revampedWavePaint.getAlpha();
        }



        @Override
        public void setAlpha(int alpha) {
            this.alpha = alpha;
            invalidateSelf();
        }

        @Override
        public int getAlpha() {
            return alpha;
        }

        @Override
        public void setColorFilter(ColorFilter cf) {

        }

        protected void setX(float centerX) {
            this.x = centerX;
            invalidateSelf();
        }

        protected float getX() {
            return x;
        }

        protected void setY(float centerY) {
            this.y = centerY;
            invalidateSelf();
        }

        protected float getY() {
            return y;
        }

        protected void setColor(int color) {
            this.color = color;
            invalidateSelf();
        }

        protected int getColor() {
            return color;
        }

        /**
         * @param waveScale
         *  getter and setter for the scale so I can animate the value
         */
        protected void setWaveScale(float waveScale) {
            this.waveScale = waveScale;
            invalidateSelf();
        }

        protected float getWaveScale() {
            return waveScale;
        }

    }

}
