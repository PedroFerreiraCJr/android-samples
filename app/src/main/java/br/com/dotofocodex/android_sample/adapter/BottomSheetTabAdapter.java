package br.com.dotofocodex.android_sample.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import br.com.dotofocodex.android_sample.R;
import br.com.dotofocodex.android_sample.model.Artist;
import br.com.dotofocodex.android_sample.model.Genre;

public class BottomSheetTabAdapter extends FragmentPagerAdapter {

    private static final String[] TITLES = {
        "Resumo",
        "Confirmação"
    };

    public BottomSheetTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment f = null;

        switch (i) {
            case 0: {
                f = new Tab0Fragment();
                break;
            }
            case 1: {
                f = new Tab1Fragment();
                break;
            }
        }

        return f;
    }

    public static final class Tab0Fragment extends Fragment {

        public Tab0Fragment() {
            super();
        }

        private List<Genre> prepare() {
            return Arrays.asList(new Genre("Rock", Arrays.asList(new Artist("Martin Garrix"), new Artist("David Guetta"))), new Genre("Pop", Arrays.asList(new Artist("Madonna"), new Artist("Anitta"))));
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.activity_order_bottom_sheet_dialog_fragment_0, container, false);

            RecyclerView rv = v.findViewById(R.id.rv_activity_order_bottom_sheet_dialog_0);
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(new GenreExpandableRecyclerViewAdapter(getContext(), prepare()));
            //rv.setHasFixedSize(true);
            return v;
        }
    }

    public static final class Tab1Fragment extends Fragment {

        public Tab1Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.activity_order_bottom_sheet_dialog_fragment_1, container, false);
            return v;
        }
    }

}