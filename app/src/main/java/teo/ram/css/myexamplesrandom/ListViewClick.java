package teo.ram.css.myexamplesrandom;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by css on 9/21/14.
 */
public class ListViewClick extends Activity {
    public ArrayList<String> items = new ArrayList<String>() {{
        add("PAOK");
        add("ARIS");
        add("GRIA");
        add("Gayros");
        add("Vazelos");
        add("Panaitolikos");
        add("Ergotelhs");
        add("Platanias");
        add("Xanthi");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);


    }


    class CustomAdapter extends ArrayAdapter<String> {

        public CustomAdapter(Context context, int resource) {
            super(context, resource);
        }

    }

}
