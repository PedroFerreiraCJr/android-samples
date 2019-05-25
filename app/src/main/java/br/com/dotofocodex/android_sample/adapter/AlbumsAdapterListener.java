package br.com.dotofocodex.android_sample.adapter;

import android.widget.ImageView;

public interface AlbumsAdapterListener {
    void onCardSelected(int position, ImageView imageView);
}
