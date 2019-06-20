package br.com.dotofocodex.android_sample.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.dotofocodex.android_sample.R;
import br.com.dotofocodex.android_sample.adapter.BottomSheetTabAdapter;
import br.com.dotofocodex.android_sample.adapter.OrderTabAdapter;
import br.com.dotofocodex.android_sample.adapter.TabAdapter;
import br.com.dotofocodex.android_sample.util.DisplayMetricsUtil;

/**
 * Expandable RecyclerView Basic Impl:
 *  https://android.jlelse.eu/get-expandable-recyclerview-in-a-simple-way-8946046b4573
 * Expandable RecyclerView Libs:
 *  https://github.com/thoughtbot/expandable-recycler-view
 * */
public class OrderActivity extends AppCompatActivity {

    private static final String TAG = "OrderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        init();
    }

    private void init() {
        // bind to the view element
        TabLayout tl = findViewById(R.id.tl_order0);
        ViewPager vp = findViewById(R.id.vp_order0);

        // create the adapter
        vp.setAdapter(new OrderTabAdapter(getSupportFragmentManager()));

        // configure de tablayout with the adapter
        tl.setupWithViewPager(vp);

        // bind the fab
        FloatingActionButton fab = findViewById(R.id.fab_order);
        fab.setOnClickListener(view -> {
            BottomSheetFragment.newInstance().show(getSupportFragmentManager(), TAG);
            /*
            fab.hide();
            new Handler().postDelayed(() -> fab.show(), 2000);
            */
        });

        /*
        fab.setOnClickListener(view -> Snackbar
            .make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show());
        */
    }

    /***
     * More information on:
     *  https://medium.com/android-dev-br/android-ui-bottom-sheet-4709cad826d2
     */
    public static class BottomSheetFragment extends BottomSheetDialogFragment {

        private View bottomSheet;
        private int peekHeight;

        public BottomSheetFragment() {
            super();
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.peekHeight = getResources().getDimensionPixelSize(R.dimen.bottom_sheet_default_peek_height);
        }

        @Override
        public int getTheme() {
            return R.style.Theme_MaterialComponents_Light_BottomSheetDialog;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_order_bottom_sheet_main_dialog, container, false);

            bottomSheet = view.findViewById(R.id.ll_activity_order_bottom_sheet_dialog);

            ViewPager vp = view.findViewById(R.id.vp_activity_order);
            TabLayout tl = view.findViewById(R.id.tl_activity_order);
            vp.setAdapter(new BottomSheetTabAdapter(getChildFragmentManager()));
            tl.setupWithViewPager(vp);

            return view;
        }

        @Override
        public void onResume() {
            super.onResume();
            setupBottomSheet();
        }

        private void setupBottomSheet() {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) getView().getParent());
            bottomSheetBehavior.setPeekHeight(this.peekHeight);
            bottomSheetBehavior.setHideable(true);
            ViewGroup.LayoutParams childLayoutParams = this.bottomSheet.getLayoutParams();
            childLayoutParams.height = (int) DisplayMetricsUtil.screenHeightInPixels();
            this.bottomSheet.setLayoutParams(childLayoutParams);
        }

        public static final BottomSheetFragment newInstance() {
            return new BottomSheetFragment();
        }
    }

}