package teo.ram.css.myexamplesrandom.myviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import teo.ram.css.myexamplesrandom.R;

//import teo.ram.css.myexamplesrandom.myviewpager.R;

/**
 * Created by csst0111 on 10/14/14.
 */
public class MyFragment   extends Fragment {

    public static Fragment newInstance(MainActivityViewPager context, int pos, float scale)     {
        Bundle b = new Bundle();
        b.putInt("pos", pos);
        b.putFloat("scale", scale);
        return Fragment.instantiate(context, MyFragment.class.getName(), b);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        LinearLayout l = (LinearLayout)
                inflater.inflate(R.layout.myviewpager_fragment, container, false);

        int pos = this.getArguments().getInt("pos");
        TextView tv = (TextView) l.findViewById(R.id.text_pager);
        tv.setText("Position = " + pos);

        CurrentItem root = (CurrentItem) l.findViewById(R.id.root_pager);
        float scale = this.getArguments().getFloat("scale");
        root.setScaleBoth(scale);

        return l;
    }
}
