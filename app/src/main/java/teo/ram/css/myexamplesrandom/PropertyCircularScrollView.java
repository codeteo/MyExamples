package teo.ram.css.myexamplesrandom;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.mobsandgeeks.adapters.CircularListAdapter;


/**
 * Created by css on 9/17/14.
 */
public class PropertyCircularScrollView extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular);

//        ListView l = (ListView) findViewById(R.id.list);

        // 1. Your data source
        String[] circularData = new String[] {
                "PAOK", "SKOULIKIA", "GRIA,", "GAYROS",
                "VAZELA", "manchester", "chelsea", "Leeds", "west Brom",
                "united", "inter", "real madrid", "barca"
        };

        // 2. Create your adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, circularData);

        // 3. Wrap your adapter within the CircularListAdapter
        final CircularListAdapter circularListAdapter = new CircularListAdapter(adapter);

        // 4. Set the adapter to your ListView
        setListAdapter(circularListAdapter);

    }

}
