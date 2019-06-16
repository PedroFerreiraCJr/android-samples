package br.com.dotofocodex.android_sample.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import br.com.dotofocodex.android_sample.R;
import br.com.dotofocodex.android_sample.util.DisplayMetricsUtil;

/***
 * Simple explanation of working way of bottom sheet:
 * Bottom sheet main use is to be an options against dialogs and context menus;
 * It can be inflated or xml static;
 * If xml static, must be inner a CoordinatorLayout;
 * Main states:
 *  1. Expanded - have his full height, been the height of layout that has your layout_behavior equal to @string/bottom_sheet_behavior;
 *  2. Collapsed - have his peek height, been that that is in the layout cited above;
 *  3. Hidden - totally invisible by the user; When it is hidden, it can not be expanded by sliding up;
 *  4. Dragging - the user is sliding the bottom sheet up and down;
 *  5. Setting - called between state changes, i guess;
 * For last but not least, the layout that has the layout_behavior equal to @string/bottom_sheet_behavior, can be various components;
 * State Constants:
 *  1. BottomSheetBehavior.STATE_COLLAPSED
 *  2. BottomSheetBehavior.STATE_HIDDEN
 *  3. BottomSheetBehavior.STATE_EXPANDED
 *  4. BottomSheetBehavior.STATE_DRAGGING
 *  5. BottomSheetBehavior.STATE_SETTLING
 *
 * More information can be found here:
 *  1. http://thetechnocafe.com/make-bottom-sheet-android/
 *  2. https://medium.com/android-dev-br/android-ui-bottom-sheet-4709cad826d2
 */
public class BottomSheetActivity extends AppCompatActivity {

    private static final String TAG = "BottomSheetActivity";

    private LinearLayout linearLayout;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        Button expand = findViewById(R.id.bt_bottom_sheet_expand);
        expand.setOnClickListener((View v) -> bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED));

        Button collapsed = findViewById(R.id.bt_bottom_sheet_collapsed);
        collapsed.setOnClickListener((View v) -> bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED));

        Button hidden = findViewById(R.id.bt_bottom_sheet_hidden);
        hidden.setOnClickListener((View v) -> bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN));

        Button dialog = findViewById(R.id.bt_bottom_sheet_dialog);
        dialog.setOnClickListener((View v) -> BottomSheetFragment.newInstance().show(getSupportFragmentManager(), TAG));

        linearLayout = findViewById(R.id.ll_bottom_sheet);
        // expands and collapse bottom sheet on touch
        linearLayout.setOnClickListener((View v) -> {
            int state = bottomSheetBehavior.getState();

            if (state == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                return;
            }

            if (state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                return;
            }
        });

        linearLayout.setOnLongClickListener((View v) -> {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            return true;
        });

        Button item0 = findViewById(R.id.bt_bottom_sheet_item_0);
        item0.setOnClickListener((View v) -> {
            Toast t = Toast.makeText(BottomSheetActivity.this, "Item 0 clicked!", Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP, 0, 0);
            t.show();
        });

        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                String desc;
                switch (i) {
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        desc = "Expanded!";
                        break;
                    }
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        desc = "Collapsed!";
                        break;
                    }
                    case BottomSheetBehavior.STATE_HIDDEN: {
                        desc = "Hidden!";
                        break;
                    }
                    case BottomSheetBehavior.STATE_DRAGGING: {
                        desc = "Dragging!";
                        break;
                    }
                    case BottomSheetBehavior.STATE_SETTLING: {
                        desc = "Settling!";
                        break;
                    }
                    default: {
                        desc = "Another state!";
                    }
                }
                Log.d(TAG, "onStateChanged: " + desc);
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                // do something when sliding
            }
        });

        // set the default behavior
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        goFullScreen(true);
    }

    /***
     * If @param enable is true, enables full screen on the bottom sheet;
     * @param enable
     */
    private void goFullScreen(boolean enable) {
        if (enable) {
            ViewGroup.LayoutParams childLayoutParams = linearLayout.getLayoutParams();
            childLayoutParams.height = (int) (DisplayMetricsUtil.screenHeightInPixels() - DisplayMetricsUtil.getStatusBarHeight());
            linearLayout.setLayoutParams(childLayoutParams);
        }
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
            View view = inflater.inflate(R.layout.activity_bottom_sheet_main_dialog, container, false);

            bottomSheet = view.findViewById(R.id.ll_bottom_sheet_dialog);
            bottomSheetPeekHeight = getResources().getDimensionPixelSize(R.dimen.bottom_sheet_default_peek_height);

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