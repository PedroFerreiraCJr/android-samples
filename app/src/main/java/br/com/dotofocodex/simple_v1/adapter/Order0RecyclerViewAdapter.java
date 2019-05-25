package br.com.dotofocodex.simple_v1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.dotofocodex.simple_v1.R;

public class Order0RecyclerViewAdapter extends RecyclerView.Adapter<Order0RecyclerViewAdapter.ViewHolder> {

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

    public Order0RecyclerViewAdapter(Context ctx, View parent, List<String> data) {
        super();
        this.ctx = ctx;
        this.parent = parent;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(this.ctx, this.parent, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_order0_recycler_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder vh, int i) {
        vh.tv.setText(data.get(i));
        vh.i = i;
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final Context ctx;
        public ImageView iv;
        public TextView tv;
        public CardView cv;
        public int i;

        public ViewHolder(Context ctx, View parent, View view) {
            super(view);
            this.ctx = ctx;
            this.iv = parent.findViewById(R.id.iv_order0);
            this.tv = view.findViewById(R.id.tv_recycler_item_order0);
            this.cv = view.findViewById(R.id.cv_recycler_item_order0);
            this.cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Glide
                .with(this.ctx)
                .load(images[i])
                .into(iv);
            Snackbar.make(v, "Touch on CardView: " + i, Snackbar.LENGTH_LONG).show();
        }
    }
}
