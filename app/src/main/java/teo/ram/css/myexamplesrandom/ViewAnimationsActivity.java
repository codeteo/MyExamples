package teo.ram.css.myexamplesrandom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by css on 9/10/14.
 */
public class ViewAnimationsActivity extends Activity{
    Button viewAnimationListener, sequentiallyAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animations);

        viewAnimationListener = (Button) findViewById(R.id.viewAnimationListenerID);
        sequentiallyAnim = (Button) findViewById(R.id.sequentiallyAnimID);
    }

    public void viewAnimationListenerClick(View v) {
        Intent i = new Intent(this, ViewAnimationListenerActivity.class);
        startActivity(i);
    }

    public void sequentiallyAnimationSet(View v) {
        Intent i = new Intent(this, SequentiallyAnimationSet.class);
        startActivity(i);
    }

}
