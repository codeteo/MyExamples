package teo.ram.css.myexamplesrandom.viewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import teo.ram.css.myexamplesrandom.R;

/**
 * Created by css on 10/4/14.
 */
public class ViewPagerActivity extends Activity implements View.OnClickListener {
    Button b1, b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        b1 = (Button) findViewById(R.id.v1);
        b2 = (Button) findViewById(R.id.v2);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.v1) {
//            Toast.makeText(this, "Button 1", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, ViewPagerSimpler.class);
            startActivity(i);
        } else if (view.getId() == R.id.v2) {
            Intent i = new Intent(this, CarouselActivity.class);
            startActivity(i);
            //
        } else {
            Toast.makeText(this, "ARXIDIA", Toast.LENGTH_SHORT).show();
        }


    }


}
