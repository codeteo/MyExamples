package teo.ram.css.myexamplesrandom.viewpager.version2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import teo.ram.css.myexamplesrandom.R;

/**
 * Created by css on 10/16/14.
 */
public class Version2Fragment extends Fragment {

    private int identifier;
    private int colour;

    public static  Fragment newInstance(Version2Activity context, int position) {
//        Log.i("TAG", "Mphke");
        Bundle b = new Bundle();
        b.putInt("pos", position);

       return Fragment.instantiate(context, Version2Fragment.class.getName(), b);
    }

//
//    public android.support.v4.app.Fragment Version2Fragment(Version2Activity context, int pos) {
//        Log.i("TAG", "Mphke");
//        Bundle b = new Bundle();
//        b.putInt("pos", pos);
//
//        return instantiate(context, Version2Fragment.class.getName(), b);
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        colour = args.getInt("colour");
        identifier = args.getInt("identifier");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        Log.i("CONTAINER", "Container container container");

        if (container == null) {
//            Log.i("TAG", "container is NULL");
            return null;
        }

//        Log.i("TAG", "Container NOT null");

        LinearLayout l = (LinearLayout)
                inflater.inflate(R.layout.version2_fragment, container, false);

        int pos = this.getArguments().getInt("pos");
        TextView tv = (TextView) l.findViewById(R.id.version2text);
        tv.setText("Position = " + pos);

        return l;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        Log.i("ATTACH", "On Attach ");
    }


}
