package teo.ram.css.myexamplesrandom.myviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;

import teo.ram.css.myexamplesrandom.R;

/**
 * Created by csst0111 on 10/14/14.
 */
public class MyPagerAdapter extends FragmentPagerAdapter implements
        ViewPager.OnPageChangeListener {

    private CurrentItem cur = null;
    private CurrentItem next = null;
    private MainActivityViewPager context;
    private FragmentManager fm;
    private float scale;

    public MyPagerAdapter(MainActivityViewPager context, FragmentManager fm) {
        super(fm);
        this.fm = fm;
        this.context = context;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int virtualPosition = position % MainActivityViewPager.PAGES;
        debug("instantiateItem: real position: " + position);
        debug("instantiateItem: virtual position: " + virtualPosition);

        // only expose virtual position to the inner adapter
//        return adapter.instantiateItem(container, virtualPosition);
        return MyFragment.newInstance(context, position, scale);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int virtualPosition = position % MainActivityViewPager.PAGES;
        debug("destroyItem: real position: " + position);
        debug("destroyItem: virtual position: " + virtualPosition);

        // only expose virtual position to the inner adapter
//        adapter.destroyItem(container, virtualPosition, object);
//        super.destroyItem(context,virtualPosition, object );
//        super.destroyItem();
//        MyFragment.newInstance(context, position, scale);
    }


    @Override
    public Fragment getItem(int position) {
        Log.i("getItem", "position== " + position);

        position = position % 5;
        Log.i("getItem", " NEW position== " + position);
        if (position == 3)
            scale = MainActivityViewPager.BIG_SCALE;
        else
            scale = MainActivityViewPager.SMALL_SCALE;

        if (fm.findFragmentByTag(getFragmentTag(position)) != null) {
            Log.i("Fragment", "old fragment  -- position= " + position);
            return fm.findFragmentByTag(getFragmentTag(position));
//            fm.beginTransaction().replace(R.id.myviewpager)
        } else {
            return MyFragment.newInstance(context, position, scale);
        }
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels)     {
//        if (positionOffset >= 0f && positionOffset <= 1f)         {
//            cur = getRootView(position);
//            next = getRootView(position +1);
//
//            cur.setScaleBoth(MainActivityViewPager.BIG_SCALE
//                    - MainActivityViewPager.DIFF_SCALE * positionOffset);
//            next.setScaleBoth(MainActivityViewPager.SMALL_SCALE
//                    + MainActivityViewPager.DIFF_SCALE * positionOffset);
//        }
    }

    @Override
    public void onPageSelected(int position) {}

    @Override
    public void onPageScrollStateChanged(int state) {}

    private CurrentItem getRootView(int position) {
        Log.i("PagerAdapter", "position== " + position);
        return (CurrentItem)
                fm.findFragmentByTag(this.getFragmentTag(position))
                        .getView().findViewById(R.id.root_pager);
    }

    private String getFragmentTag(int position) {
        return "android:switcher:" + context.pager.getId() + ":" + position;
    }


    private static final String TAG = "InfinitePagerAdapter";
    private static final boolean DEBUG = true;

    private void debug(String message) {
        if (DEBUG) {
            Log.i(TAG, message);
        }
    }


}



