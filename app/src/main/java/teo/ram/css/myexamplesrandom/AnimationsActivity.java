package teo.ram.css.myexamplesrandom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by css on 9/10/14.
 */
public class AnimationsActivity extends Activity{
    Button viewAnimations, viewPropertyAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations);

        viewAnimations = (Button) findViewById(R.id.viewAnimationsID);
        viewPropertyAnimator = (Button) findViewById(R.id.viewPropertyAnimatorID);
    }


    public void viewAnimationsClick(View view){
        Intent i = new Intent(this, ViewAnimationsActivity.class);
        startActivity(i);
    }

    public void viewPropertyAnimator(View view){
        Intent i = new Intent(this, ViewPropertyAnimatorActivity.class);
        startActivity(i);
    }


}
