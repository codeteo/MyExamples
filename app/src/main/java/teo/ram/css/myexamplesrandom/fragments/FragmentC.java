package teo.ram.css.myexamplesrandom.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import teo.ram.css.myexamplesrandom.R;

/**
 * Created by css on 9/22/14.
 */
public class FragmentC extends Fragment {
    TextView t1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("FRAGMENT_C", "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("FRAGMENT_C", "onCreateView");
        return inflater.inflate(R.layout.fragment_c, container, false);
    }
}
