package app.pentastagiu.ro.ultrashopmobile.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.pentastagiu.ro.ultrashopmobile.R;

/**
 * Created by deni on 6/29/2015.
 */
public class Cart extends Fragment {
    public Cart() {

    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = layoutInflater.inflate(R.layout.activity_products_list, container, false);
        return rootView;
    }
}
