package teo.ram.css.myexamplesrandom.fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import teo.ram.css.myexamplesrandom.R;

/**
 * Created by css on 9/22/14.
 */
public class FragmentsActivity extends Activity implements FragmentA.Communication{
    FragmentA fragmentA;
    FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);


        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        FragmentC fragmentC = new FragmentC();

        FragmentManager fm = getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.containerA, fragmentA);

        // Work with fragment A
        ft.add(R.id.containerB, fragmentB).commit();
        //Work with fragment B
//        ft.replace(R.id.container, fragmentB).commit();


        detaching();

    }

    public void detaching(){
        if ( fragmentA.isAdded() ) {
            Log.i("MAIN", "Fragment A is Added");
        }
    }

    @Override
    public boolean communicate() {
        Toast.makeText(this, "Text", Toast.LENGTH_SHORT).show();
        return true;
    }
}
