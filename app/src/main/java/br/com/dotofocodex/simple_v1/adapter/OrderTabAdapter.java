package br.com.dotofocodex.simple_v1.adapter;

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

import java.util.ArrayList;
import java.util.List;

import br.com.dotofocodex.simple_v1.R;

public class OrderTabAdapter extends FragmentPagerAdapter {

    private static final String[] titles = new String[] {
        "Passo 01",
        "Passo 02",
        "Passo 03",
        "Passo 04",
        "Passo 05",
        "Passo 06",
        "Passo 07",
        "Passo 08",
        "Passo 09",
        "Passo 10",
    };

    public OrderTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
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
                f = new OrderTab0Fragment();
                break;
            }
            case 1: {
                f = new OrderTab1Fragment();
                break;
            }
            case 2: {
                f = new OrderTab2Fragment();
                break;
            }
            case 3: {
                f = new OrderTab3Fragment();
                break;
            }
            case 4: {
                f = new OrderTab4Fragment();
                break;
            }
            case 5: {
                f = new OrderTab5Fragment();
                break;
            }
            case 6: {
                f = new OrderTab6Fragment();
                break;
            }
            case 7: {
                f = new OrderTab7Fragment();
                break;
            }
            case 8: {
                f = new OrderTab8Fragment();
                break;
            }
            case 9: {
                f = new OrderTab9Fragment();
                break;
            }
        }

        return f;
    }

    private static final List<String> of(int num) {
        List<String> data = new ArrayList<>(num);
        for (int i=1; i<=num; i++) {
            data.add("Value " + i);
        }
        return data;
    }

    public static final class OrderTab0Fragment extends Fragment {

        public OrderTab0Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            final View v = inflater.inflate(R.layout.fragment_order0, container, false);

            // bind to recycler view
            RecyclerView rv = v.findViewById(R.id.rv_order0);

            // configure the layout manager for recycler view
            rv.setLayoutManager(new LinearLayoutManager(getContext()));

            // set the adapter of recycler view
            rv.setAdapter(new Order0RecyclerViewAdapter(getContext(), v, of(5)));
            rv.setHasFixedSize(true);
            return v;
        }

    }

    public static final class OrderTab1Fragment extends Fragment {

        public OrderTab1Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_order1, container, false);
            return v;
        }
    }

    public static final class OrderTab2Fragment extends Fragment {

        public OrderTab2Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_order2, container, false);
            return v;
        }
    }

    public static final class OrderTab3Fragment extends Fragment {

        public OrderTab3Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_order3, container, false);
            return v;
        }
    }

    public static final class OrderTab4Fragment extends Fragment {

        public OrderTab4Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_order4, container, false);
            return v;
        }
    }

    public static final class OrderTab5Fragment extends Fragment {

        public OrderTab5Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_order5, container, false);
            return v;
        }
    }

    public static final class OrderTab6Fragment extends Fragment {

        public OrderTab6Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_order6, container, false);
            return v;
        }
    }

    public static final class OrderTab7Fragment extends Fragment {

        public OrderTab7Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_order7, container, false);
            return v;
        }
    }

    public static final class OrderTab8Fragment extends Fragment {

        public OrderTab8Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_order8, container, false);
            return v;
        }
    }

    public static final class OrderTab9Fragment extends Fragment {

        public OrderTab9Fragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_order9, container, false);
            return v;
        }
    }
}