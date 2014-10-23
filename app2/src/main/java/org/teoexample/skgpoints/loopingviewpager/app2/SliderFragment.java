package org.teoexample.skgpoints.loopingviewpager.app2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by css on 10/24/14.
 */
public class SliderFragment extends  Fragment implements ViewPager.OnPageChangeListener {

    private static final String LOG_TAG = "DetailsScreenSliderFragment";
    private static final boolean DEBUG = false;

    // we name the left, middle and right page
    private static final int PAGE_LEFT = 0;
    private static final int PAGE_MIDDLE = 1;
    private static final int PAGE_RIGHT = 2;

    private int mSelectedPageIndex = 1;
    // we save each page in a model
    private PageModel[] mPageModel = new PageModel[3];

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    // Контекст Activity-контейнера
    private Context context;

    class PageModel {
        private int index;
        private String uid;

        PageModel(int index, String uid) {
            this.index = index;
            this.uid = uid;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getUid() {
            return uid;
        }

        private void setUid(String uid) {
            this.uid = uid;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Лучше, чем getActivity(), т.к. она возвращает null,
        // если обработчик onAttach еще не был вызыван
        context = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_pager, container, false);
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) view.findViewById(R.id.detailsTopPager);

        initPageModel();

        // так как используем вложенный фрагмент, то getChildFragmentManager()
        mPagerAdapter = new MyPagerAdaper(getChildFragmentManager());

        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(PAGE_MIDDLE, false);
        mPager.setOnPageChangeListener(this);
        return view;
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {
        // Log.d("SCROLL", "from " + i + " to " + i2 + "(" + v);
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("SCROLL", "onPageSelected " + position);
        mSelectedPageIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {

            final PageModel leftPage = mPageModel[PAGE_LEFT];
            final PageModel middlePage = mPageModel[PAGE_MIDDLE];
            final PageModel rightPage = mPageModel[PAGE_RIGHT];

            final int oldLeftIndex = leftPage.getIndex();
            final int oldMiddleIndex = middlePage.getIndex();
            final int oldRightIndex = rightPage.getIndex();

            // user swiped to right direction --> left page
            if (mSelectedPageIndex == PAGE_LEFT) {

                // moving each page content one page to the right
                leftPage.setIndex(oldLeftIndex - 1);
                middlePage.setIndex(oldLeftIndex);
                rightPage.setIndex(oldMiddleIndex);

                setContent(PAGE_RIGHT);
                setContent(PAGE_MIDDLE);
                setContent(PAGE_LEFT);

                // user swiped to left direction --> right page
            } else if (mSelectedPageIndex == PAGE_RIGHT) {

                leftPage.setIndex(oldMiddleIndex);
                middlePage.setIndex(oldRightIndex);
                rightPage.setIndex(oldRightIndex + 1);

                setContent(PAGE_LEFT);
                setContent(PAGE_MIDDLE);
                setContent(PAGE_RIGHT);
            }
            mPager.setCurrentItem(PAGE_MIDDLE, false);
        }
    }

    private void setContent(int index) {
        final PageModel model = mPageModel[index];
    }

    private void initPageModel() {
        for (int i = 0; i < mPageModel.length; i++) {
            // initing the pagemodel with indexes of -1, 0 and 1
            mPageModel[i] = new PageModel(i - 1, "");
        }
    }




    private class MyPagerAdaper extends PagerAdapter {
        private final FragmentManager mFragmentManager;
        private FragmentTransaction mCurTransaction = null;
        private ArrayList<Fragment> mFragments = new ArrayList<Fragment>(3);
        private Fragment mCurrentPrimaryItem = null;

        MyPagerAdaper(FragmentManager fm) {
            mFragmentManager = fm;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            // we only need three pages
            return 3;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            /* TextView textView = (TextView)mInflater.inflate(R.layout.content, null);
            PageModel currentPage = mPageModel[position];
            /*currentPage.textView = textView;
            textView.setText(currentPage.getText());
            container.addView(textView);*/
            //return textView;
            return null;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

}