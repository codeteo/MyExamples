package teo.ram.css.myexamplesrandom.viewpager.version2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import teo.ram.css.myexamplesrandom.R;

/**
 * Created by css on 10/16/14.
 */
public class Version2Activity extends FragmentActivity {
    public final static int PAGES = 5;
    public final static float BIG_SCALE = 1.0f;
    public final static float SMALL_SCALE = 0.7f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;

    public Version2PagerAdapter adapter;
//    public Version2ViewPager pager;
    public ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myviewpager_main);

        pager = (ViewPager) findViewById(R.id.myviewpager_pager);

        adapter = new Version2PagerAdapter(this, getSupportFragmentManager());
        pager.setAdapter(adapter);
//        pager.setOnPageChangeListener(adapter);

        pager.setCurrentItem(0, true);            // center to 3rd view
        pager.setOffscreenPageLimit(4);     // 2 pages ( fragments) retained in each side of currentItem
        pager.setPageMargin(-150);

    }

}