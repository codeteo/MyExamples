package teo.ram.css.myexamplesrandom.ripples;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
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
 * Created by css on 9/25/14.
 */
public class WaveDrawableActivity extends Activity {
    private WaveDrawable waveDrawable;
    FloatingActionButton fab;
    FrameLayout parent;


    LinearLayout frameLayout;
    LinearLayout.LayoutParams  newView_params;

    private float x, y;
    int maxX, maxY;
    Boolean flag = false;
    Boolean isHidden = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wavedrawable);

        Display mdisp = getWindowManager().getDefaultDisplay();
        maxX= mdisp.getWidth();
        maxY= mdisp.getHeight();

        parent = (FrameLayout) findViewById(R.id.parentID);
        fab = (FloatingActionButton) findViewById(R.id.button_floating_action);

//        final FrameLayout newView = (FrameLayout) findViewById(R.id.newViewID);
        frameLayout = new LinearLayout(this);
        frameLayout.setGravity(Gravity.BOTTOM);
        newView_params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);


//        ImageView imageView = (ImageView) findViewById(R.id.image);

//        imageView.setBackgroundDrawable(waveDrawable);

            fab.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    isHidden = true;
                    if ( flag == false) {
                        Log.i("TAGTAGTAGTAG", " ************ adds to parent child view***********");
                        parent.addView(frameLayout, newView_params);
                    }
                    flag = true;

                    x = fab.getX();
                    y  = fab.getY();

                    float centerX = x + fab.getWidth() / 2;
                    float centerY = y +  fab.getHeight() / 2;
                    Log.i("TAG", " fab.getY()== " + y);
                    Log.i("TAG", "fab.getHeight()/2 ===" + centerY);


                    waveDrawable = new WaveDrawable(Color.parseColor("#ff00897b"), 1500, centerX, centerY);
                    waveDrawable.setWaveInterpolator(new LinearInterpolator());

                    frameLayout.setBackground(waveDrawable);
                    waveDrawable.startAnimation();

                    fab.animate().y(y + 200f).setDuration(500).start();

                }
            });

    }


    @Override
    public void onBackPressed() {
        Log.i("ONBACKPRESSED", "*** On Back Pressed ***   y==  " +  y );

        if ( isHidden == true ) {
            isHidden = false;
            waveDrawable.startReverseAnimation();
            fab.animate().y(y).setDuration(1200).start();
        } else {
            super.onBackPressed();
        }

    }




    public class WaveDrawable extends Drawable {

        private Paint wavePaint;
        private int color;
        private int radius;
        private long animationTime = 1000;

        protected float waveScale;
        protected int alpha;


        private ObjectAnimator waveAnimator;
        private ObjectAnimator alphaAnimator;

        private Interpolator waveInterpolator;
        private Interpolator alphaInterpolator;

        private Animator animator;
        private AnimatorSet animatorSet;

        private float centerX, centerY;

        /**
         * @param color
         * @param radius
         * @param animationTime
         */
        public WaveDrawable(int color, int radius, long animationTime) {
            this(color, radius);
            this.animationTime = animationTime;
        }

        /**
         * @param color
         * @param radius
         */
        public WaveDrawable(int color, int radius) {
            this.color = color;
            this.radius = radius;
            this.waveScale = 0f;
            this.alpha = 255;

            wavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            animatorSet = new AnimatorSet();

        }

        public WaveDrawable(int color, int radius, float centerX, float centerY) {
            this.color = color;
            this.radius = radius;
            this.waveScale = 0f;
            this.alpha = 255;

            this.centerX = centerX;
            this.centerY = centerY;

            wavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            animatorSet = new AnimatorSet();

        }

        @Override
        public void draw(Canvas canvas) {

            final Rect bounds = getBounds();

            // circle
            wavePaint.setStyle(Paint.Style.FILL);
            wavePaint.setColor(color);
            wavePaint.setAlpha(alpha);
//            Log.i("Ginomeno", "ginomeno=  " + radius * waveScale);
            canvas.drawCircle(centerX , centerY, radius * waveScale, wavePaint);

        }

        /**
         * @param interpolator
         */
        public void setWaveInterpolator(Interpolator interpolator) {
            this.waveInterpolator = interpolator;
        }

        /**
         * @param interpolator
         */
        public void setAlphaInterpolator(Interpolator interpolator) {
            this.alphaInterpolator = interpolator;
        }

        /**
         *
         */
        public void startAnimation() {
            animator = generateAnimation();
            animator.start();
        }

        public void startReverseAnimation() {
            animator = generateReverseAnimation();
            animator.start();
        }

        public void stopAnimation() {
            if (animator.isRunning()) {
                animator.end();
            }
        }


        public boolean isAnimationRunning() {
            if (animator != null) {
                return animator.isRunning();
            }
            return false;
        }


        @Override
        public void setAlpha(int alpha) {
            this.alpha = alpha;
            invalidateSelf();
        }

        @Override
        public void setColorFilter(ColorFilter cf) {
            wavePaint.setColorFilter(cf);
        }

        @Override
        public int getOpacity() {
            return wavePaint.getAlpha();
        }


        protected void setWaveScale(float waveScale) {
            this.waveScale = waveScale;
            invalidateSelf();
        }

        protected float getWaveScale() {
            return waveScale;
        }

        private Animator generateAnimation() {

            //Wave animation
            waveAnimator = ObjectAnimator.ofFloat(this, "waveScale", 0f, 1f);
            waveAnimator.setDuration(animationTime);
            if (waveInterpolator != null) {
                waveAnimator.setInterpolator(waveInterpolator);
            }

            //alpha animation
            alphaAnimator = ObjectAnimator.ofInt(this, "alpha", 215, 185);
            alphaAnimator.setDuration(animationTime);
            if (alphaInterpolator != null) {
                alphaAnimator.setInterpolator(alphaInterpolator);
            }

            animatorSet.playTogether(waveAnimator, alphaAnimator);
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Drawable d = frameLayout.getBackground();
//                    Log.i("TRANSPARENT", "Alpha=  " + d.getOpacity() );
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

            return animatorSet;
        }

        private Animator generateReverseAnimation() {

            //Wave animation
            waveAnimator = ObjectAnimator.ofFloat(this, "waveScale", 1f, 0f);
            waveAnimator.setDuration(animationTime);
            if (waveInterpolator != null) {
                waveAnimator.setInterpolator(waveInterpolator);
            }

            //alpha animation
            alphaAnimator = ObjectAnimator.ofInt(this, "alpha", 215, 215);
            alphaAnimator.setDuration(animationTime);
            if (alphaInterpolator != null) {
                alphaAnimator.setInterpolator(alphaInterpolator);
            }

            animatorSet.playTogether(waveAnimator, alphaAnimator);
            return animatorSet;
        }

    }

}
