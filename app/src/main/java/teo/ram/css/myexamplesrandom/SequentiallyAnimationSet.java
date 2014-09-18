package teo.ram.css.myexamplesrandom;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by css on 9/12/14.
 */
public class SequentiallyAnimationSet extends Activity{
    public Animation animation=null;
    public ImageView img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequentially_animationset);

        img = (ImageView) findViewById(R.id.heartID);

        animation = AnimationUtils.loadAnimation(this, R.anim.sequentially_anim_set);
        img.startAnimation(animation);
    }
}
