package teo.ram.css.myexamplesrandom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.debug.hv.ViewServer;

import teo.ram.css.myexamplesrandom.fragments.FragmentsActivity;
import teo.ram.css.myexamplesrandom.ripples.RipplesActivity;
import teo.ram.css.myexamplesrandom.viewpager.ViewPagerActivity;


public class EndlessActivity extends Activity {

    Button animationsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animationsButton = (Button) findViewById(R.id.animationsID);

        ViewServer.get(this).addWindow(this);       // ViewServer

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void animationsClick(View view){
        Intent i = new Intent(this, AnimationsActivity.class);
        startActivity(i);
    }


    public void librariesClick(View view){
        Intent i = new Intent(this, LibrariesActivity.class);
        startActivity(i);
    }

    public void fragmentClick(View view){
        Intent i = new Intent(this, FragmentsActivity.class);
        startActivity(i);
    }

    public void rippleClick(View view){
        Intent i = new Intent(this, RipplesActivity.class);
        startActivity(i);
    }

    public void viewpagerClick(View view){
        Intent i = new Intent(this, ViewPagerActivity.class);
        startActivity(i);
    }



    // ViewServer

    @Override
    public void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }

}
