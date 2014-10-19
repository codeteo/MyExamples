package teo.ram.css.myexamplesrandom.endlessviewpager;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import teo.ram.css.myexamplesrandom.R;

/**
 * Created by css on 10/14/14.
 */
public class EndlessAdapter  extends FragmentPagerAdapter implements
        ViewPager.OnPageChangeListener {

    private EndlessLinearLayout cur = null;
    private EndlessLinearLayout next = null;

    private teo.ram.css.myexamplesrandom.endlessviewpager.EndlessActivity context;
    private android.support.v4.app.FragmentManager fm;
    private float scale;
    private Boolean flag=false;


//    public EndlessAdapter(EndlessActivity context, FragmentManager fm) {
//        super(fm);
//        this.fm = fm;
//        this.context = context;
//    }

    public EndlessAdapter(EndlessActivity context, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        this.fm = supportFragmentManager;
        this.context = context;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position)     {
        // make the first pager bigger than others
        if (position == EndlessActivity.FIRST_PAGE)
            scale = EndlessActivity.BIG_SCALE;
        else
            scale = EndlessActivity.SMALL_SCALE;

        position = position % EndlessActivity.PAGES;
        return EndlessFragment.newInstance(context, position, scale);
    }

    @Override
    public int getCount() {
        return EndlessActivity.PAGES * EndlessActivity.LOOPS;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)     {
        if (positionOffset >= 0f && positionOffset <= 1f) {
            cur = getRootView(position);
            next = getRootView(position +1);

            cur.setScaleBoth(EndlessActivity.BIG_SCALE
                    - EndlessActivity.DIFF_SCALE * positionOffset);
            next.setScaleBoth(EndlessActivity.SMALL_SCALE
                    + teo.ram.css.myexamplesrandom.endlessviewpager.EndlessActivity.DIFF_SCALE * positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {}

    @Override
    public void onPageScrollStateChanged(int state) {}

    private EndlessLinearLayout getRootView(int position)     {
        return (EndlessLinearLayout)
                fm.findFragmentByTag(this.getFragmentTag(position))
                        .getView().findViewById(R.id.root);
    }

    private String getFragmentTag(int position)     {
        return "android:switcher:" + context.pager.getId() + ":" + position;
    }

}