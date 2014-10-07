package teo.ram.css.myexamplesrandom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by css on 9/18/14.
 */
public class LibrariesActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libraries);

        Button b1 = (Button) findViewById(R.id.sephiroth74ID);

    }


    public void sephiroth74Click(View view) {
        Intent i = new Intent(this, Sephiroth74Activity.class);
        startActivity(i);
    }

    public void listViewClick(View view) {
        Intent i = new Intent(this, ListViewClick.class);
        startActivity(i);
    }

}
