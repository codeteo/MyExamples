package teo.ram.css.myexamplesrandom.viewpager;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import teo.ram.css.myexamplesrandom.R;

/**
 * Created by css on 10/7/14.
 */
public class CarouselFragment extends android.support.v4.app.ListFragment implements View.OnClickListener{

    private static String[] cheeses = {"Ramones", "Clash", "Sex Pistols", "Dead Kennedys",
            "Fall", "Jonathan Richman", "Mark E. Smith", "999", "Mark Lanegan", "Asimos"};

    private boolean mActionDown;
    LinearLayout l;
    TextView tv;
    Button button;
    CarouselLinearLayout root;


    public static final String TAG = "CarouselFragment".toUpperCase();

    private ObjectAnimator animator;
    private Boolean isAnimated = false;

    OnAnimationStart mCallback;

    private Boolean flag=false;

    public static Fragment newInstance(CarouselActivity context, int pos, float scale, Boolean flag)    {
        Bundle b = new Bundle();
        b.putInt("pos", pos);
//        Log.i(TAG, "New Instance @ pos== " + pos);
        b.putFloat("scale", scale);
        b.putBoolean("flag", flag);
        return Fragment.instantiate(context, CarouselFragment.class.getName(), b);
    }

    public interface OnAnimationStart {
        public void onAnimation();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        Log.i(TAG, "On Attach ");

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnAnimationStart) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        flag = this.getArguments().getBoolean("flag");

        if( isAnimated || flag) {
            l = (LinearLayout)
                    inflater.inflate(R.layout.fragment_carousel_animated, container, false);
        } else {
            l = (LinearLayout)
                    inflater.inflate(R.layout.fragment_carousel, container, false);
        }

        int pos = this.getArguments().getInt("pos");                    //getArguments
        tv = (TextView) l.findViewById(R.id.text);
        tv.setText("Position = " + pos);

        button = (Button) l.findViewById(R.id.content);
        button.setOnClickListener(this);

        root = (CarouselLinearLayout) l.findViewById(R.id.root);
        float scale = this.getArguments().getFloat("scale");            //getArguments
        root.setScaleBoth(scale);

        return l;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter( new ArrayAdapter<String> ( getActivity(),
                android.R.layout.simple_list_item_1, cheeses) );
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i("FragmentList", "Item clicked: " + id);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if ( id == R.id.content ) {
            if ( !flag ) {
                Log.i(TAG, "Animation!!");
                flag = true;
                mCallback.onAnimation();
                moveAnimation();
            }
        }
    }

    public void moveAnimation() {
        animator = ObjectAnimator.ofFloat(root, "y", 20f);
        animator.setDuration(500);
        animator.start();
    }


}
