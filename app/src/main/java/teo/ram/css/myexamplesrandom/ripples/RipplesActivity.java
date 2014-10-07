package teo.ram.css.myexamplesrandom.ripples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import teo.ram.css.myexamplesrandom.R;

/**
 * Created by css on 9/25/14.
 */
public class RipplesActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripples);
    }


    public void waveDrawableID(View view) {
        Intent i = new Intent(this, WaveDrawableActivity.class);
        startActivity(i);
    }

    public void waveDrawableID2(View view) {
        Intent i = new Intent(this, WaveDrawableRevampedActivity.class);
        startActivity(i);
    }

}
