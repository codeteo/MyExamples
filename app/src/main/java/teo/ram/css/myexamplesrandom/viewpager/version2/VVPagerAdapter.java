package teo.ram.css.myexamplesrandom.viewpager.version2;

/**
 * Created by css on 10/20/14.
 */

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;

import teo.ram.css.myexamplesrandom.R;

/**
 * Created by css on 10/16/14.
 */
public class VVPagerAdapter  extends FragmentPagerAdapter {

    private static final String TAG = "FragmentPagerAdapter";
    private static final boolean DEBUG = true;
    private final FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction = null;
    private android.support.v4.app.Fragment mCurrentPrimaryItem = null;

    Version2Activity context;


    public VVPagerAdapter(Version2Activity context, FragmentManager fm) {
        super(fm);
        this.context = context;
        mFragmentManager = fm;
    }

    /**
     * Return the Fragment associated with a specified position.
     */
    public  android.support.v4.app.Fragment getItem(int position) {
//        Log.i(TAG, "getItem Times== " + position);
        return Version2Fragment.newInstance(context, position);
    }

    @Override
    public int getCount() {
        return 20;                                                                                                          // return 20;
    }

    public int getRealCount() {
        return 5;
    }


    @Override
    public void startUpdate(ViewGroup container) {
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int virtualPosition = position % getRealCount();                                                                    //uses virtualPosition

        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }
        final long itemId = getItemId(virtualPosition);
//        String name = makeFragmentName(container.getId(), itemId);
        //getItemByPosition
        String name2 = getFragmentTag(virtualPosition);
        android.support.v4.app.Fragment fragment = mFragmentManager.findFragmentByTag(name2);


        if (fragment != null) {
            if (DEBUG) Log.i(TAG, "Attaching item #" + itemId + ": f=" + fragment);
            mCurTransaction.attach(fragment);
        } else {
            fragment = getItem(virtualPosition);
            if (DEBUG) Log.i(TAG, "Adding item #" + itemId + ": f=" + fragment);
            mCurTransaction.add(container.getId(), fragment, makeFragmentName(container.getId(), itemId));
        }
        if (fragment != mCurrentPrimaryItem) {
            fragment.setMenuVisibility(false);
            fragment.setUserVisibleHint(false);
        }
        return fragment;
    }


    @Override
    public long getItemId(int position) {
//        Log.i(TAG, "getItemID==  " + position);
        return position;
    }


    private static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }

    public android.support.v4.app.Fragment getFragmentByPosition(int viewID, long pos) {
        String tag = "android:switcher:" + viewID + ":" + pos;
        return mFragmentManager.findFragmentByTag(tag);
    }


    private String getFragmentTag(int pos){
        return "android:switcher:"+ R.id.myviewpager_pager+":"+pos;
    }

//    public Fragment findFragmentByPosition(int position) {
//        FragmentPagerAdapter fragmentPagerAdapter = getFragmentPagerAdapter();
//        return getSupportFragmentManager().findFragmentByTag(
//                "android:switcher:" + getViewPager().getId() + ":"
//                        + fragmentPagerAdapter.getItemId(position));
//    }


}