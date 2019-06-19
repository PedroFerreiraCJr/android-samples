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
import android.widget.Button;
import android.widget.Toast;

import br.com.dotofocodex.android_sample.R;
import br.com.dotofocodex.android_sample.adapter.OrderTabAdapter;
import br.com.dotofocodex.android_sample.util.DisplayMetricsUtil;

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
        private int bottomSheetPeekHeight;

        public BottomSheetFragment() {
            super();
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
            bottomSheetPeekHeight = getResources().getDimensionPixelSize(R.dimen.bottom_sheet_default_peek_height);

            Button done = view.findViewById(R.id.bt_activity_order_bottom_sheet_item_0);
            done.setOnClickListener((View v) -> {
                dismiss();
                Toast.makeText(getContext(), "Concluindo pedido...", Toast.LENGTH_SHORT).show();
            });

            return view;
        }

        @Override
        public void onResume() {
            super.onResume();
            setupBottomSheet();
        }

        private void setupBottomSheet() {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) getView().getParent());
            bottomSheetBehavior.setPeekHeight(bottomSheetPeekHeight);
            bottomSheetBehavior.setHideable(true);
            ViewGroup.LayoutParams childLayoutParams = bottomSheet.getLayoutParams();
            childLayoutParams.height = (int) DisplayMetricsUtil.screenHeightInPixels();
            bottomSheet.setLayoutParams(childLayoutParams);
        }

        public static final BottomSheetFragment newInstance() {
            return new BottomSheetFragment();
        }
    }

}