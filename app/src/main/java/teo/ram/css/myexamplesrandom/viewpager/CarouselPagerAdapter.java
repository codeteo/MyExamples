package teo.ram.css.myexamplesrandom.viewpager;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import teo.ram.css.myexamplesrandom.R;

//  import android.support.v4.app.Fragment;
//

/**
 * Created by css on 10/7/14.
 */
public class CarouselPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {


    private CarouselLinearLayout cur = null;
    private CarouselLinearLayout next = null;
    private CarouselActivity context;
    private android.support.v4.app.FragmentManager fm;
    private float scale;
    private Boolean flag=false;


    public CarouselPagerAdapter(CarouselActivity context, android.support.v4.app.FragmentManager fm) {
        super(fm);
        this.fm = fm;
        this.context = context;
    }

    @Override
    public android.support.v4.app.Fragment  getItem(int fragmentPosition) {  //   *** GET ITEM *** //
//        Log.i("GETITEM", "BEFORE position== " + fragmentPosition + "    flag== " + flag);
        if (fragmentPosition == CarouselActivity.FIRST_PAGE)
            scale = CarouselActivity.BIG_SCALE;
        else
            scale = CarouselActivity.SMALL_SCALE;

        flag = CarouselActivity.FLAG;
        int pagePosition = fragmentPosition % CarouselActivity.PAGES;
//        Log.i("GETITEM", "AFTER position== " + pagePosition + "    flag== " + flag);
        return CarouselFragment.newInstance(context, pagePosition, scale, flag);
    }

    @Override
    public int getCount()  {
        return CarouselActivity.PAGES * CarouselActivity.LOOPS;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)  {
        if (positionOffset >= 0f && positionOffset <= 1f)   {
            cur = getRootView(position);
            next = getRootView(position +1);

            cur.setScaleBoth(CarouselActivity.BIG_SCALE
                    - CarouselActivity.DIFF_SCALE * positionOffset);
            next.setScaleBoth(CarouselActivity.SMALL_SCALE
                    + CarouselActivity.DIFF_SCALE * positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) { }

    @Override
    public void onPageScrollStateChanged(int state) {}

    private CarouselLinearLayout getRootView(int fragmentPosition) {
//        Log.i("getRootVIEW", "fragmentPosition== " + fragmentPosition);
        return (CarouselLinearLayout)
                fm.findFragmentByTag(this.getFragmentTag(fragmentPosition))
                        .getView().findViewById(R.id.root);
    }

    private String getFragmentTag(int fragmentPosition) {
//        Log.i("getFragmentTag", "Position=== " + fragmentPosition);
        return "android:switcher:" + context.pager.getId() + ":" + fragmentPosition;
    }

}
