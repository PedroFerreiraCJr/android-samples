package br.com.dotofocodex.android_sample.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.dotofocodex.android_sample.R;

public class OrderTabAdapter extends FragmentPagerAdapter {

    private static final String TAG = "OrderTabAdapter";

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
        OrderTab0Fragment.instantiate(true);
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
        Log.d(TAG, "getItem");
        Fragment f = null;

        switch (i) {
            case 0: {
                f = OrderTab0Fragment.getInstance();
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

    @SuppressLint("ValidFragment")
    public static final class OrderTab0Fragment extends Fragment {
        private static OrderTab0Fragment instance;
        private static boolean instantiate;

        private View view;

        private OrderTab0Fragment() {
            super();
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            if (this.view == null) {
                Log.d(TAG, "onCreateView: view is null!");
                this.view = inflater.inflate(R.layout.fragment_order0, container, false);

                // bind to recycler view
                RecyclerView rv = this.view.findViewById(R.id.rv_order0);

                // configure the layout manager for recycler view
                rv.setLayoutManager(new LinearLayoutManager(getContext()));

                // set the adapter of recycler view
                // selection tracker
                // https://www.thiengo.com.br/selectiontracker-para-selecao-de-itens-no-recyclerview-android
                rv.setAdapter(new Order0RecyclerViewAdapter(getContext(), this.view, of(5)));
                rv.setHasFixedSize(true);

                SimpleSwipeCallback ssc = new SimpleSwipeCallback();
                ItemTouchHelper ith = new ItemTouchHelper(ssc);
                ith.attachToRecyclerView(rv);
            }

            return this.view;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
        }

        @Override
        public void onStart() {
            super.onStart();
        }

        @Override
        public void onResume() {
            super.onResume();
        }

        @Override
        public void onPause() {
            super.onPause();
        }

        @Override
        public void onStop() {
            super.onStop();
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }

        @Override
        public void onDetach() {
            super.onDetach();
        }

        public static final void instantiate(boolean instantiate) {
            OrderTab0Fragment.instantiate = instantiate;
        }

        public static final Fragment getInstance() {
            if (instantiate) {
                instance = null;
            }
            if (instance == null) {
                synchronized (Order0RecyclerViewAdapter.class) {
                    if (instance == null) {
                        instance = new OrderTab0Fragment();
                    }
                }
            }
            return instance;
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

    /*
      from where this come from:
    * https://codeburst.io/android-swipe-menu-with-recyclerview-8f28a235ff28
    * */
    public static class SimpleSwipeCallback extends ItemTouchHelper.Callback {

        private boolean swipeBack;

        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        }

        @Override
        public int convertToAbsoluteDirection(int flags, int layoutDirection) {
            if (swipeBack) {
                swipeBack = false;
                return 0;
            }
            return super.convertToAbsoluteDirection(flags, layoutDirection);
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                setTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

        private void setTouchListener(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,float dX, float dY,int actionState, boolean isCurrentlyActive) {
            recyclerView.setOnTouchListener((v, event) -> {
                swipeBack = event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP;
                return false;
            });
        }
    }
}