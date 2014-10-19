package teo.ram.css.myexamplesrandom.viewpager;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import com.android.debug.hv.ViewServer;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

import teo.ram.css.myexamplesrandom.R;

/**
 * Created by css on 10/7/14.
 */
public class CarouselActivity extends FragmentActivity  implements CarouselFragment.OnAnimationStart,
                    View.OnTouchListener{

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

    private int currentPage=FIRST_PAGE;
    private Handler handler;
    private Timer swipeTimer;
    private Runnable Update;

    int centerFragmentPosition = FIRST_PAGE;
    int pagerIncrement = 0;

    CarouselScroller scroller;
    private Field mScrollerField;
    private Scroller mOldScroller;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carouselpager);

        pager = (ViewPager) findViewById(R.id.myviewpager);

        adapter = new CarouselPagerAdapter(this, getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(adapter);

        pager.setCurrentItem(FIRST_PAGE);
        pager.setOffscreenPageLimit(2);
        pager.setPageMargin(-200);


        pager.setOnTouchListener(this);

        Interpolator decelerator = new DecelerateInterpolator();
        try {
            mScrollerField= ViewPager.class.getDeclaredField("mScroller");
            mScrollerField.setAccessible(true);
            mOldScroller = (Scroller)mScrollerField.get(pager); //getting old value
            scroller = new CarouselScroller(pager.getContext(), decelerator);
            mScrollerField.set(pager, scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        /*IMPORTANT at least pass the exception to logcat also for your
        sanity (fx: in next version of compat library the field could be renamed
        to mTheScroller and you could get NPE in restore code and then ask
        stupid(without logcat) questions on SO why ...*/
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        handler = new Handler();
        Update = new Runnable() {
            public void run() {
                currentPage = currentPage % CarouselActivity.PAGES;
                currentPage++;
                centerFragmentPosition++;
                Log.i(TAG + " /onRunnable", "centerFragmentPosition== " + centerFragmentPosition  + "  currentPage== " + currentPage % CarouselActivity.PAGES);
                pager.setCurrentItem(2500+pagerIncrement++, true);
            }
        };

        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(Update);                       // TODO postDelayed
            }
        }, 1000, 3000);

        // ViewServer
        ViewServer.get(this).addWindow(this);       // ViewServer
    }


    // ViewServer

    @Override
    public void onDestroy() {
        super.onDestroy();
        FLAG = false;
        ViewServer.get(this).removeWindow(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }

    @Override
    public void onAnimation() {

//        if ( !FLAG ) {
//            Log.i(TAG, "Removing Handler");
//            handler.removeCallbacks(Update);
//            swipeTimer.cancel();
//
//            for (int i = 2400; i < 2600; i++) {
//                //      Log.i(TAG, " Mphke mes ---  i= " + i  );
//
//                String fragmentTag = "android:switcher:" + pager.getId() + ":" + i;
//                //      Log.i(TAG, "fragmentTag== " + fragmentTag);
//
//                CarouselFragment carouselFragment = (CarouselFragment) getSupportFragmentManager().findFragmentByTag(fragmentTag);
//                if (carouselFragment != null) {
//                    Log.i(TAG, "Carousel Fragment TAG===  " + carouselFragment.getTag());
//                    carouselFragment.moveAnimation();
//                }
//            }
//        }
//        FLAG = true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if ( !FLAG ) {
            Log.i(TAG, "Removing Handler");
            swipeTimer.cancel();
            handler.removeCallbacks(Update);


            Log.i(TAG + " /onTouch", "currentPage == " + currentPage);
            String fTag = "android:switcher:" + pager.getId() + ":" + centerFragmentPosition;
            CarouselFragment carouselFragment = (CarouselFragment) getSupportFragmentManager().findFragmentByTag(fTag);
            if (carouselFragment != null) {
                Log.i(TAG, "Carousel Fragment TAG===  " + carouselFragment.getTag());
//                carouselFragment.moveAnimation();
                pager.setCurrentItem(FIRST_PAGE + ( currentPage % CarouselActivity.PAGES) ) ;
//                pager.setOnPageChangeListener(adapter);

                /* *** on Touch change Scroller *** */
                try {
                    Log.i(TAG, "new Scroller");
                    mScrollerField.set(pager, mOldScroller);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
        FLAG = true;
        return true;
    }
}
