package br.com.dotofocodex.android_sample.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.dotofocodex.android_sample.R;

public class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "Frag " + position;
    }

    @Override
    public int getCount() {
        return 10;
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
            case 2: {
                f = new Tab2Fragment();
                break;
            }
            case 3: {
                f = new Tab3Fragment();
                break;
            }
            case 4: {
                f = new Tab4Fragment();
                break;
            }
            case 5: {
                f = new Tab5Fragment();
                break;
            }
            case 6: {
                f = new Tab6Fragment();
                break;
            }
            case 7: {
                f = new Tab7Fragment();
                break;
            }
            case 8: {
                f = new Tab8Fragment();
                break;
            }
            case 9: {
                f = new Tab9Fragment();
                break;
            }
        }

        return f;
    }

    public static final class Tab0Fragment extends Fragment {

        public Tab0Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_tab0, container, false);
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
            View v = inflater.inflate(R.layout.fragment_tab1, container, false);
            return v;
        }
    }

    public static final class Tab2Fragment extends Fragment {

        public Tab2Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_tab2, container, false);
            return v;
        }
    }

    public static final class Tab3Fragment extends Fragment {

        public Tab3Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_tab3, container, false);
            return v;
        }
    }

    public static final class Tab4Fragment extends Fragment {

        public Tab4Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_tab4, container, false);
            return v;
        }
    }

    public static final class Tab5Fragment extends Fragment {

        public Tab5Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_tab5, container, false);
            return v;
        }
    }

    public static final class Tab6Fragment extends Fragment {

        public Tab6Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_tab6, container, false);
            return v;
        }
    }

    public static final class Tab7Fragment extends Fragment {

        public Tab7Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_tab7, container, false);
            return v;
        }
    }

    public static final class Tab8Fragment extends Fragment {

        public Tab8Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_tab8, container, false);
            return v;
        }
    }

    public static final class Tab9Fragment extends Fragment {

        public Tab9Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_tab9, container, false);
            return v;
        }
    }
}