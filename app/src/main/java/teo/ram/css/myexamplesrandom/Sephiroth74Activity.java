package teo.ram.css.myexamplesrandom;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.widget.HListView;

/**
 * Created by css on 9/18/14.
 */
public class Sephiroth74Activity extends Activity{
    HListView hll;
    TestAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cephiroth74);


        List<String> items = new ArrayList<String>();
        for( int i = 0; i < 9; i++ ) {
            items.add( String.valueOf( i ) );
        }


        hll = (HListView) findViewById(R.id.hListView1);
        mAdapter = new TestAdapter( this, R.layout.test_item_1, android.R.id.text1, items );

        hll.setAdapter( mAdapter );


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

//        @Override
//        public int getCount() {
//            return Integer.MAX_VALUE;
//        }

        @Override
        public View getView( int position, View convertView, ViewGroup parent ) {

            if( null == convertView ) {
                convertView = mInflater.inflate( mResource, parent, false );
            }

            TextView textView = (TextView) convertView.findViewById( mTextResId );
            textView.setText( getItem( position ) );

            int type = getItemViewType( position );

            ViewGroup.LayoutParams params = convertView.getLayoutParams();
            if( type == 0 ) {
                params.width = getResources().getDimensionPixelSize( R.dimen.item_size_1 );
            } else if( type == 1 ) {
                params.width = getResources().getDimensionPixelSize( R.dimen.item_size_2 );
            } else {
                params.width = getResources().getDimensionPixelSize( R.dimen.item_size_3 );
            }

            return convertView;
        }
    }


}
