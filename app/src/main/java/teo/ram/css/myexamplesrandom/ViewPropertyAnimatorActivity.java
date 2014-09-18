package teo.ram.css.myexamplesrandom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by css on 9/14/14.
 */
public class ViewPropertyAnimatorActivity extends Activity{
    Button viewPropertyAnimatorAlpha, objectAnimator, objectAnimator2, animateViewgroup, animateViewsOFViewGroup;
    Button viewAnimationRepeat, propertyCustomEvaluator, propertyCircularScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpropertyanimator);

        viewPropertyAnimatorAlpha = (Button) findViewById(R.id.viewPropertyAnimatorAlphaID);
        objectAnimator = (Button) findViewById(R.id.objectAnimatorID);
        objectAnimator2 = (Button) findViewById(R.id.objectAnimator2ID);
        animateViewgroup = (Button) findViewById(R.id.animateViewGroupID);
        animateViewsOFViewGroup = (Button) findViewById(R.id.animateViewsOfViewGroupID);
        viewAnimationRepeat = (Button) findViewById(R.id.viewAnimationRepeatID);
        propertyCustomEvaluator = (Button) findViewById(R.id.propertyCustomEvaluatorID);
        propertyCircularScrollView = (Button) findViewById(R.id.PropertyCircularScrollViewID);

    }

    public void viewPropertyAnimatorAlpha(View v) {
        Intent i = new Intent(this, ViewPropertyAnimatorAlpha.class);
        startActivity(i);
    }

    public void objectAnimator(View v) {
        Intent i = new Intent(this, ObjectAnimatorActivity.class);
        startActivity(i);
    }

    public void objectAnimator2(View v) {
        Intent i = new Intent(this, ObjectAnimator2Activity.class);
        startActivity(i);
    }

    public void animateViewGroup(View v) {
        Intent i = new Intent(this, ViewPropertyAnimateViewGroup.class);
        startActivity(i);
    }

    public void animateViewsOfViewGroup(View v) {
        Intent i = new Intent(this, ViewAnimateViewsOfViewGroup.class);
        startActivity(i);
    }

    public void viewAnimationRepeat(View v) {
        Intent i = new Intent(this, ViewAnimationRepeat.class);
        startActivity(i);
    }

    public void propertyCustomEvaluator(View v) {
        Intent i = new Intent(this, PropertyCustomEvaluator.class);
        startActivity(i);
    }

    public void PropertyCircularScrollView(View v) {
        Intent i = new Intent(this, PropertyCircularScrollView.class);
        startActivity(i);
    }

}
