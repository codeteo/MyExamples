package teo.ram.css.myexamplesrandom.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import teo.ram.css.myexamplesrandom.R;

/**
 * Created by css on 10/8/14.
 */
public class RandomStuff extends Activity implements View.OnClickListener{
//    @InjectView(R.id.rootID) LinearLayout linearLayout;
    @InjectView(R.id.innerTextID) TextView textView;
    @InjectView(R.id.innerButtonID) Button innerButton;
    @InjectView(R.id.outerButtonID) Button outerButton;


    public static final String TAG = "RandomShit".toUpperCase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomshit);
        ButterKnife.inject(this);

        outerButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.outerButtonID) {
            Log.i(TAG, "*** Outer Button Y== " + outerButton.getY() + "  X==" + outerButton.getX());
            Log.i(TAG, "*** Outer Button Y== " + outerButton.getTop() + "  X==" + outerButton.getLeft());
            Log.i(TAG, "*** Inner Button Y== " + innerButton.getY() + "  X==" + innerButton.getX());
            Log.i(TAG, "*** Inner Button Y== " + innerButton.getTop() + "  X==" + innerButton.getLeft());


//            Log.i(TAG, "*** Inner Root Y== " + linearLayout.getY() + "  X==" + linearLayout.getX());

            int[] locations = new int[2];
            innerButton.getLocationOnScreen(locations);
            int x = locations[0];
            int y = locations[1];
            Log.i(TAG, "Y== " + y + "  X==" + x);

        }
    }

}
