package teo.ram.css.myexamplesrandom.viewpager.version2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;

import teo.ram.css.myexamplesrandom.R;

/**
 * Created by css on 10/16/14.
 */
public class Version2PagerAdapter  extends FragmentStatePagerAdapter {

    private static final String TAG = "FragmentPagerAdapter";
    private static final boolean DEBUG = true;
    private final FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction = null;
    private Fragment mCurrentPrimaryItem = null;

    Version2Activity context;


    public Version2PagerAdapter(Version2Activity context, FragmentManager fm) {
        super(fm);
        this.context = context;
        mFragmentManager = fm;
    }

    /**
     * Return the Fragment associated with a specified position.
     */
    public android.support.v4.app.Fragment getItem(int position) {
        Log.i(TAG, "Fragment newInstance== " + position);
        return Version2Fragment.newInstance(context, position);
    }

    @Override
    public int getCount() {
        return 10;                                                                                                          // return 20;
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
        final long itemId = (long)(virtualPosition);
        final long pos = (long)position;
        String fragmentTag = getFragmentTag(itemId);
        Fragment fragment = mFragmentManager.findFragmentByTag(fragmentTag);

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


//    @Override
//    public int getItemPosition(Object object){
//        return PagerAdapter.POSITION_NONE;
//    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int virtualPosition = position % getRealCount();

        Fragment fragment = (Fragment) object;
        if (mCurTransaction != null) {
            Log.i(TAG, " ******************* DETACH **** ");
            mCurTransaction.detach(fragment);
            mCurTransaction.commitAllowingStateLoss();      //like commit()
            mCurTransaction = null;
            mFragmentManager.executePendingTransactions();
        }
//        object.det
        Log.i(TAG, "destroy Item  f== "  + object );

//        android.support.v4.app.Fragment detachFragment;
//        String fragmentTag = getFragmentTag((long)virtualPosition);
//        detachFragment = mFragmentManager.findFragmentByTag(fragmentTag);
//
//
//        if (mCurTransaction == null) {
//            mCurTransaction = mFragmentManager.beginTransaction();
//        }
//        mCurTransaction.detach(detachFragment);

    }


    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Log.i(TAG, "setPrimaryItem");
        Fragment fragment = (Fragment) object;
        if (fragment != mCurrentPrimaryItem) {
            Log.i(TAG, " fragment != mCurrentPrimaryItem ");
            if (mCurrentPrimaryItem != null ) {
                Log.i(TAG, "mCurrentPrimaryItem != null ");
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
        Log.i(TAG, "finish Update");
        if (mCurTransaction != null) {
            Log.i(TAG, "finish Update !=null ");
            mCurTransaction.commitAllowingStateLoss();      //like commit()
            mCurTransaction = null;
            mFragmentManager.executePendingTransactions();
        }
    }


    private static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }

    public Fragment getFragmentByPosition(int viewID, long pos) {
        String tag = "android:switcher:" + viewID + ":" + pos;
        return mFragmentManager.findFragmentByTag(tag);
    }


    private String getFragmentTag(long pos){
        return "android:switcher:"+ R.id.myviewpager_pager+":"+pos;
    }

}