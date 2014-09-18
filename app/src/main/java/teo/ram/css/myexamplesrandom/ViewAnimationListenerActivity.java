package teo.ram.css.myexamplesrandom;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by css on 9/10/14.
 */
public class ViewAnimationListenerActivity extends Activity {
    private int state_machine = 0;
    private Animation mAnim = null;
    public Animation mAnim2 = null;

    public ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_listener);

        mAnim = AnimationUtils.loadAnimation(this, R.anim.xform_left_to_right_begin);
        mAnim.setAnimationListener(listenerForFirstAnimation);

        mAnim2 = AnimationUtils.loadAnimation(this, R.anim.xform_left_to_right_end);

        img = (ImageView)findViewById(R.id.blip);
        img.startAnimation(mAnim);
    }


    Animation.AnimationListener listenerForFirstAnimation = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            img.startAnimation(mAnim2);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

}
