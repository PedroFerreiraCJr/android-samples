package br.com.dotofocodex.simple_v1.adapter;

import android.widget.ImageView;

public interface AlbumsAdapterListener {
    void onCardSelected(int position, ImageView imageView);
}
