package teo.ram.css.myexamplesrandom.endlessviewpager;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import teo.ram.css.myexamplesrandom.R;

/**
 * Created by css on 10/14/14.
 */
public class EndlessFragment extends android.support.v4.app.Fragment {

    public static android.support.v4.app.Fragment newInstance(EndlessActivity context, int pos,
                                       float scale)
    {
        Bundle b = new Bundle();
        b.putInt("pos", pos);
        b.putFloat("scale", scale);
        return android.support.v4.app.Fragment.instantiate(context, EndlessFragment.class.getName(), b);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        LinearLayout l = (LinearLayout)
                inflater.inflate(R.layout.endless_fragment, container, false);

        int pos = this.getArguments().getInt("pos");
        TextView tv = (TextView) l.findViewById(R.id.text);
        tv.setText("Position = " + pos);

        EndlessLinearLayout root = (EndlessLinearLayout) l.findViewById(R.id.root);
        float scale = this.getArguments().getFloat("scale");
        root.setScaleBoth(scale);

        return l;
    }
}