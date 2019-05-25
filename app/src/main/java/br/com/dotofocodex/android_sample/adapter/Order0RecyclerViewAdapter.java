package br.com.dotofocodex.android_sample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.dotofocodex.android_sample.R;
import br.com.dotofocodex.android_sample.glide.GlideApp;

public class Order0RecyclerViewAdapter extends RecyclerView.Adapter<Order0RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "Order0RecyclerViewAdapt";
    private static final int[] images = new int[] {
        R.drawable.cat_0,
        R.drawable.cat_1,
        R.drawable.cat_2,
        R.drawable.cat_0,
        R.drawable.cat_1,
    };

    private final Context ctx;
    private final View parent;
    private final List<String> data;
    private List<ViewHolder> holders;

    public Order0RecyclerViewAdapter(Context ctx, View parent, List<String> data) {
        super();
        this.ctx = ctx;
        this.parent = parent;
        this.data = data;
        this.holders = new ArrayList<>(5);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder");
        ViewHolder vh = new ViewHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_order0_recycler_item, viewGroup, false));
        this.holders.add(vh);
        return vh;
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        Log.d(TAG, "onViewRecycled");
        super.onViewRecycled(holder);
        this.holders.remove(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.bind(data.get(i), i);
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        private final Order0RecyclerViewAdapter adapter;
        public ImageView iv;
        public TextView tv;
        public CheckBox cb;
        public CardView cv;
        public int i;

        private ViewHolder(Order0RecyclerViewAdapter adapter, View view) {
            super(view);
            this.adapter = adapter;
            this.iv = adapter.parent.findViewById(R.id.iv_order0);
            this.tv = view.findViewById(R.id.tv_recycler_item_order0);
            this.cb = view.findViewById(R.id.cb_recycler_item_order0);
            this.cb.setOnCheckedChangeListener(this);
            this.cv = view.findViewById(R.id.cv_recycler_item_order0);
            this.cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            selected(this.i, true);
        }

        @Override
        public void onCheckedChanged(CompoundButton v, boolean isChecked) {
            if (isChecked) {
                action(v);
                selected(this.i, false);
            }
        }

        private void bind(String value, int i) {
            this.tv.setText(value);
            this.i = i;
        }

        private void selected(int position, boolean bool) {
            for (ViewHolder vh : this.adapter.holders) {
                if (bool && vh.i == position) {
                    vh.cb.setChecked(true);
                    continue;
                }

                if (!(vh.i == position)) {
                    vh.cb.setChecked(false);
                }
            }
        }

        private void action(View v) {
            GlideApp
                .with(this.adapter.ctx)
                .load(images[i])
                .into(iv);
            Snackbar.make(v, "Touch on CardView: " + i, Snackbar.LENGTH_LONG).show();
        }
    }
}
