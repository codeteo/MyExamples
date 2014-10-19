package teo.ram.css.myexamplesrandom.viewpager.version2;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by css on 10/16/14.
 */
public class Version2ViewPager extends ViewPager {

    public Version2ViewPager(Context context) {
        super(context);
    }

    public Version2ViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        // offset first element so that we can scroll to the left
        setCurrentItem(0);
    }

    @Override
    public void setCurrentItem(int item) {
        // offset the current item to ensure there is space to scroll
        setCurrentItem(item, false);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        Log.i("viewpager", "item== " + item);
        item = getOffsetAmount() + (item % getAdapter().getCount());
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public int getCurrentItem() {
        Log.i("viewpager", "Mphke na parei neo item");
        int position = super.getCurrentItem();
        if (getAdapter() instanceof Version2PagerAdapter) {
            Version2PagerAdapter infAdapter = (Version2PagerAdapter) getAdapter();
            // Return the actual item position in the data backing InfinitePagerAdapter
            return (position % infAdapter.getRealCount());
        } else {
            return super.getCurrentItem();
        }
    }

    private int getOffsetAmount() {
        if (getAdapter() instanceof Version2PagerAdapter) {
            Version2PagerAdapter infAdapter = (Version2PagerAdapter) getAdapter();
            // allow for 100 back cycles from the beginning
            // should be enough to create an illusion of infinity
            // warning: scrolling to very high values (1,000,000+) results in
            // strange drawing behaviour
            return infAdapter.getRealCount() + 10;
        } else {
            return 0;
        }
    }

}