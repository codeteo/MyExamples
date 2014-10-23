package teo.ram.css.myexamplesrandom.viewpager.infiniteviewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import teo.ram.css.myexamplesrandom.R;

/**
 * Created by css on 10/15/14.
 */
public class InfiniteActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infiniteactivity);

        PagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            int[] colours = new int[]{Color.CYAN, Color.GRAY, Color.MAGENTA, Color.LTGRAY, Color.GREEN, Color.WHITE,
                    Color.YELLOW};

            @Override
            public int getCount() {
                return colours.length;
            }

            @Override
            public Fragment getItem(int position) {
                Fragment fragment = new ColourFragment();
                Bundle args = new Bundle();
                args.putInt("colour", colours[position]);
                args.putInt("identifier", position);
                fragment.setArguments(args);
                return fragment;
            }
        };

        // wrap pager to provide infinite paging with wrap-around
        PagerAdapter wrappedAdapter = new InfinitePagerAdapter(adapter);

//        wrappedAdapter.setM
//        wrappedAdapter.setPageMargin(-200);


        // actually an InfiniteViewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager_infiniteviewpager);
        viewPager.setAdapter(wrappedAdapter);

    }
}