package teo.ram.css.myexamplesrandom.viewpager.version2;

import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import teo.ram.css.myexamplesrandom.R;

/**
 * Created by css on 10/16/14.
 */
public class Version2PagerAdapter  extends FragmentPagerAdapter {

    private static final String TAG = "FragmentPagerAdapter";
    private static final boolean DEBUG = true;
    private final FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction = null;
    private android.support.v4.app.Fragment mCurrentPrimaryItem = null;

    Version2Activity context;


    public Version2PagerAdapter(Version2Activity context, FragmentManager fm) {
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
        final long poss = (long)position;
        // Do we already have this fragment?
        String name = makeFragmentName(container.getId(), itemId);
//        android.support.v4.app.Fragment fragment = mFragmentManager.findFragmentByTag(name);
//        android.support.v4.app.Fragment fragment = getFragmentByPosition(container.getId(), itemId);
        String name2 = getFragmentTag(poss);
        android.support.v4.app.Fragment fragment = mFragmentManager.findFragmentByTag(name2);
        //getItemByPosition

        if (fragment != null) {
            if (DEBUG) Log.i(TAG, "Attaching item #" + itemId + ": f=" + fragment);
            mCurTransaction.attach(fragment);
        } else {
            fragment = getItem(virtualPosition);
            if (DEBUG) Log.i(TAG, "Adding item #" + itemId + ": f=" + fragment);
            mCurTransaction.add(container.getId(), fragment, makeFragmentName(container.getId(), itemId));
        }
        if (fragment != mCurrentPrimaryItem) {
//            Log.i(TAG, "mCurrentPrimaryItem --- Visibility");
            fragment.setMenuVisibility(false);
            fragment.setUserVisibleHint(false);
        }
        return fragment;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        //destroy NONE                                                                                                      // I dont need to destroy

//        if (mCurTransaction == null) {
//            mCurTransaction = mFragmentManager.beginTransaction();
//        }
//        if (DEBUG) Log.v(TAG, "Detaching item #" + getItemId(position) + ": f=" + object
//                + " v=" + ((android.support.v4.app.Fragment)object).getView());
//        mCurTransaction.detach((android.support.v4.app.Fragment)object);
    }



    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        android.support.v4.app.Fragment fragment = (android.support.v4.app.Fragment) object;
        if (fragment != mCurrentPrimaryItem) {
            if (mCurrentPrimaryItem != null) {
                mCurrentPrimaryItem.setMenuVisibility(false);
                mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            if (fragment != null) {
                fragment.setMenuVisibility(true);
                fragment.setUserVisibleHint(true);
            }
            mCurrentPrimaryItem = fragment;
        }
    }
    @Override
    public void finishUpdate(ViewGroup container) {
        if (mCurTransaction != null) {
            mCurTransaction.commitAllowingStateLoss();
            mCurTransaction = null;
            mFragmentManager.executePendingTransactions();
        }
    }




    @Override
    public boolean isViewFromObject(View view, Object object) {
        return ((android.support.v4.app.Fragment)object).getView() == view;
    }
    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    /**
     * Return a unique identifier for the item at the given position.
     *
     * <p>The default implementation returns the given position.
     * Subclasses should override this method if the positions of items can change.</p>
     *
     * @param position Position within this adapter
     * @return Unique identifier for the item at position
     */
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


    private String getFragmentTag(long pos){
        return "android:switcher:"+ R.id.myviewpager_pager+":"+pos;
    }

//    public Fragment findFragmentByPosition(int position) {
//        FragmentPagerAdapter fragmentPagerAdapter = getFragmentPagerAdapter();
//        return getSupportFragmentManager().findFragmentByTag(
//                "android:switcher:" + getViewPager().getId() + ":"
//                        + fragmentPagerAdapter.getItemId(position));
//    }


}