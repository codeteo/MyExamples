package teo.ram.css.myexamplesrandom.viewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import teo.ram.css.myexamplesrandom.R;
import teo.ram.css.myexamplesrandom.viewpager.infiniteviewpager.InfiniteActivity;
import teo.ram.css.myexamplesrandom.viewpager.version2.Version2Activity;

/**
 * Created by css on 10/4/14.
 */
public class ViewPagerActivity extends Activity implements View.OnClickListener {
    Button b1, b2, b3, b4, b5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        b1 = (Button) findViewById(R.id.v1);
        b2 = (Button) findViewById(R.id.v2);
        b3 = (Button) findViewById(R.id.v3);
        b4 = (Button) findViewById(R.id.v4);
        b5 = (Button) findViewById(R.id.v5);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);

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
        } else if(view.getId() == R.id.v3 ) {
            Intent i = new Intent(this, RandomStuff.class);
            startActivity(i);
        }else if (view.getId() == R.id.v4) {
            Intent i = new Intent(this, InfiniteActivity.class);
            startActivity(i);
        }else if (view.getId() == R.id.v5) {
            Intent i = new Intent(this, Version2Activity.class);
            startActivity(i);

        } else {
            Toast.makeText(this, "ARXIDIA", Toast.LENGTH_SHORT).show();
        }


    }


}
