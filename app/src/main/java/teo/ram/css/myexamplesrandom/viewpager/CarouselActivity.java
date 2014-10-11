package teo.ram.css.myexamplesrandom.viewpager;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.android.debug.hv.ViewServer;

import teo.ram.css.myexamplesrandom.R;

/**
 * Created by css on 10/7/14.
 */
public class CarouselActivity extends FragmentActivity  implements CarouselFragment.OnAnimationStart{

    public final static int PAGES = 5;
    // You can choose a bigger number for LOOPS, but you know, nobody will fling
    // more than 1000 times just in order to test your "infinite" ViewPager :D
    public final static int LOOPS = 1000;
    public final static int FIRST_PAGE = PAGES * LOOPS / 2;

    public final static float BIG_SCALE = 1.0f;
    public final static float SMALL_SCALE = 0.7f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;

    public static Boolean FLAG=false;

    public CarouselPagerAdapter adapter;
    public ViewPager pager;

    public static final String TAG = "CarouselActivity".toUpperCase();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carouselpager);

        pager = (ViewPager) findViewById(R.id.myviewpager);

        adapter = new CarouselPagerAdapter(this, getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(adapter);


        // Set current item to the middle page so we can fling to both
        // directions left and right
        pager.setCurrentItem(FIRST_PAGE);

        // Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        pager.setOffscreenPageLimit(2);

        // Set margin for pages as a negative number, so a part of next and
        // previous pages will be showed
        pager.setPageMargin(-200);

        // ViewServer
        ViewServer.get(this).addWindow(this);       // ViewServer
    }


    // ViewServer

    @Override
    public void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }

    @Override
    public void onAnimation() {
        // gia ola ta fragments ektos apo ayto pou xo parei to fragmentposition tou
        // steile na ksekinhsoun to animation

        if ( !FLAG ) {
            for (int i = 2400; i < 2600; i++) {
        //      Log.i(TAG, " Mphke mes ---  i= " + i  );

                String fragmentTag = "android:switcher:" + pager.getId() + ":" + i;
        //      Log.i(TAG, "fragmentTag== " + fragmentTag);

                CarouselFragment carouselFragment = (CarouselFragment) getSupportFragmentManager().findFragmentByTag(fragmentTag);
                if (carouselFragment != null) {
                    Log.i(TAG, "Carousel Fragment TAG===  " + carouselFragment.getTag());
                    carouselFragment.moveAnimation();
                }
            }
        }
        FLAG = true;
    }
}
