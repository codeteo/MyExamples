package teo.ram.css.myexamplesrandom.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import teo.ram.css.myexamplesrandom.R;
/**
 * Created by css on 9/22/14.
 */
public class FragmentA extends Fragment {
    @NonNull
    Communication mCallback;


    public interface Communication {
        public boolean communicate();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("FRAGMENT_A", "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("FRAGMENT_A", "onCreateView");
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onDetach() {
        Log.i("FRAGMENT_A", "onDetach");
        try {
            mCallback = (Communication) getActivity();
            mCallback.communicate();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement Interface");
        }
        super.onDetach();
    }


}
