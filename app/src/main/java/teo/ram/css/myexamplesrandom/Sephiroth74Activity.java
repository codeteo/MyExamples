package teo.ram.css.myexamplesrandom;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.AutoScrollHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.widget.AbsHListView;
import it.sephiroth.android.library.widget.HListView;

/**
 * Created by css on 9/18/14.
 */
public class Sephiroth74Activity extends Activity implements HListView.OnTouchListener, HListView.OnScrollListener {
    private HListView hll;
    TestAdapter mAdapter;
    public TextView textView;
    public static final String TAG = "Sephiroth74Activity".toUpperCase();

    // https://gist.github.com/jpardogo/70143625771697844896
    private int TOTAL_ITEMS = 2000;
    private AutoScrollHelper mScrollHelper;
    private boolean mActionDown;

    private ObjectAnimator mScaleUp;
    private View mDownView;


    /** DEBUG **/
    boolean DEBUG = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cephiroth74);


        List<String> items = new ArrayList<String>();
        for( int i = 0; i < 9; i++ ) {
            items.add( String.valueOf( i ) );
        }

        textView = (TextView) findViewById(R.id.textID);

        hll = (HListView) findViewById(R.id.hListView1);
        mAdapter = new TestAdapter( this, R.layout.test_item_1, R.id.textID, items );

        hll.setAdapter( mAdapter );

//        hll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                String data = (String) adapterView.getItemAtPosition(position);
//                String pos = String.valueOf(position);
//                Log.i(TAG, "text=  " + data + "  pos= " + pos);
//
//            }
//        });


        hll.setOnScrollListener(this);
        hll.setOnTouchListener(this);
//        hll.setSelection(TOTAL_ITEMS / 2);



        initScrollHelper();
        startAutoScroll();

    }


    private void initScrollHelper() {

       if(DEBUG) Log.i(TAG, "***initScrollHelper***");

        mScrollHelper = new AutoScrollHelper(hll) {
            @Override
            public void scrollTargetBy(int deltaX, int deltaY) {
                if(DEBUG) Log.i(TAG, "***initScrollHelper   AutoScrollHelper***");
                hll.smoothScrollBy(2, 0);
            }

            @Override
            public boolean canTargetScrollHorizontally(int direction) {
                if(DEBUG) Log.i(TAG, "***initScrollHelper   AutoScrollHelper Horizontally***");
                return true;
            }

            @Override
            public boolean canTargetScrollVertically(int direction) {
                if(DEBUG) Log.i(TAG, "***initScrollHelper   AutoScrollHelper  Vertically***");
                return true;
            }

        };


        mScrollHelper.setEnabled(true);
        mScrollHelper.setEdgeType(AutoScrollHelper.EDGE_TYPE_OUTSIDE);
    }

    private void startAutoScroll() {
         hll.post(new Runnable() {
             @Override
             public void run() {
                 if(DEBUG) Log.i(TAG, "***startAutoScroll***");
                 forceScroll();
             }
         });
    }


    private void forceScroll() {
        if(DEBUG) Log.i(TAG, "***forceScroll***");
        MotionEvent event = MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), MotionEvent.ACTION_MOVE, hll.getX(), -1, 0);
        mScrollHelper.onTouch(hll, event);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(DEBUG) Log.i(TAG, " ******** Activity's ONTOUCH********* ");
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                // Find the child view that was touched (perform a hit test)
                Rect rect = new Rect();
                int childCount = hll.getChildCount();           // number of views in group currently visible
                int[] listViewCoords = new int[2];
                hll.getLocationOnScreen(listViewCoords);
                int x = (int) event.getRawX() - listViewCoords[0];
                int y = (int) event.getRawY() - listViewCoords[1];
                View child;
                for (int i = 0; i < childCount; i++) {
                    child = hll.getChildAt(i);

                    int tag  = (Integer) child.getTag();

                    child.getHitRect(rect);
                    if (rect.contains(x, y)) {
                        Log.i(TAG, "child==  " + i  + "  tag== "  + tag);
                        mDownView = child; // This is your down view

                    mScaleUp = ObjectAnimator.ofPropertyValuesHolder(mDownView,
                            PropertyValuesHolder.ofFloat("scaleX", 4f),
                            PropertyValuesHolder.ofFloat("scaleY", 5f));
                    mScaleUp.setDuration(1000);
                    mScaleUp.start();

                        break;
                    }
                }

                Log.i(TAG, " *** ACTION DOWN *** ");


                mActionDown=true;
                break;
            case MotionEvent.ACTION_UP:
                mActionDown=false;
                break;
        }
        return mScrollHelper.onTouch(v, event);
    }

//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//        switch (scrollState) {
//            case SCROLL_STATE_IDLE:
//                if(!mActionDown){
//                    forceScroll();
//                }
//                break;
//        }
//    }

    @Override
    public void onScrollStateChanged(AbsHListView absHListView, int i) {

    }

    @Override
    public void onScroll(AbsHListView absHListView, int i, int i2, int i3) {

    }


    class TestAdapter extends ArrayAdapter<String> {

        List<String> mItems;
        LayoutInflater mInflater;
        int mResource;
        int mTextResId;

        public static final int HALF_MAX_VALUE = Integer.MAX_VALUE/2;
        public int MIDDLE;
        List<String> objects;
//
//        public TestAdapter( Context context, int resourceId, int textViewResourceId, List<String> objects ) {
//            super( context, resourceId, textViewResourceId, objects );
//            mInflater = LayoutInflater.from(context);
//            mResource = resourceId;
//            mTextResId = textViewResourceId;
//            mItems = objects;
//        }




        public TestAdapter(Context context, int resourceId, int textViewResourceId, List<String> objects)        {
            super(context, resourceId, textViewResourceId, objects);
            mInflater = LayoutInflater.from(context);
            mResource = resourceId;
            mTextResId = textViewResourceId;
            this.objects = objects;
            MIDDLE = HALF_MAX_VALUE - HALF_MAX_VALUE % objects.size();
        }


        @Override
        public int getCount()         {
            return Integer.MAX_VALUE;
        }

        @Override
        public String getItem(int position)         {
            return objects.get(position % objects.size());
        }


        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public long getItemId( int position ) {
            return getItem( position ).hashCode();
        }

        @Override
        public int getViewTypeCount() {
            return 3;
        }

        @Override
        public int getItemViewType( int position ) {
            return position%3;
        }


        @Override
        public View getView( int position, View convertView, ViewGroup parent ) {

            if( null == convertView ) {
                convertView = mInflater.inflate( mResource, parent, false );
            }

            TextView textView = (TextView) convertView.findViewById( mTextResId );
            textView.setText( getItem( position ) );

            Log.i(TAG, "***POSITION=== " + position);

            if (position%9 == 0) {
                convertView.setTag(0);
            } else if (position%9 == 1) {
                convertView.setTag(1);
            } else if (position%9 == 2) {
                convertView.setTag(2);
            } else if (position%9 == 3) {
                convertView.setTag(3);
            } else if (position%9 == 4) {
                convertView.setTag(4);
            } else if (position%9 == 5) {
                convertView.setTag(5);
            } else if (position%9 == 6) {
                convertView.setTag(6);
            } else if (position%9 == 7) {
                convertView.setTag(7);
            } else if (position%9 == 8) {
                convertView.setTag(8);
            } else convertView.setTag(100);


//            View v = super.getView(position, convertView, parent);
//            v.setOnTouchListener(this);

            /** depending on Type returns width for the view **/
            int type = getItemViewType( position );

            ViewGroup.LayoutParams params = convertView.getLayoutParams();
            if( type == 0 ) {
                params.width = getResources().getDimensionPixelSize( R.dimen.three_items_size );
            } else if( type == 1 ) {
                params.width = getResources().getDimensionPixelSize( R.dimen.three_items_size );
            } else {
                params.width = getResources().getDimensionPixelSize( R.dimen.three_items_size );
            }

            return convertView;
        }

//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            Log.i(TAG, "***MESA sto onTouch***");
//
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
////                    mActionDown=true;
//                    mScaleUp = ObjectAnimator.ofPropertyValuesHolder(v,
//                            PropertyValuesHolder.ofFloat("scaleX", 1.5f),
//                            PropertyValuesHolder.ofFloat("scaleY", 1.5f));
//                    mScaleUp.setDuration(1000);
//                    mScaleUp.start();
//
//                    break;
//                case MotionEvent.ACTION_UP:
////                    mActionDown=false;
//                    break;
//            }
//            return true;
//        }


    }


}
