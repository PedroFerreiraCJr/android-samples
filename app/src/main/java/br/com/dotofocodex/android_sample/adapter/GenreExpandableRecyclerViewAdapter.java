package br.com.dotofocodex.android_sample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

import br.com.dotofocodex.android_sample.R;
import br.com.dotofocodex.android_sample.model.Artist;
import br.com.dotofocodex.android_sample.model.Genre;

public class GenreExpandableRecyclerViewAdapter extends ExpandableRecyclerViewAdapter<GenreExpandableRecyclerViewAdapter.GenreViewHolder, GenreExpandableRecyclerViewAdapter.ArtistViewHolder> {

    private LayoutInflater inflater;

    public GenreExpandableRecyclerViewAdapter(Context context, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public GenreViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_genre, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public ArtistViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_artist, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ArtistViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Artist artist = ((Genre) group).getItems().get(childIndex);
        holder.onBind(artist);
    }

    @Override
    public void onBindGroupViewHolder(GenreViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGenreTitle(group);
    }

    public static class GenreViewHolder extends GroupViewHolder {

        private TextView genreTitle;

        public GenreViewHolder(View itemView) {
            super(itemView);
            this.genreTitle = itemView.findViewById(R.id.list_item_genre_name);
        }

        public void setGenreTitle(ExpandableGroup group) {
            genreTitle.setText(group.getTitle());
        }
    }

    public static class ArtistViewHolder extends ChildViewHolder {

        private TextView artistName;

        public ArtistViewHolder(View itemView) {
            super(itemView);
            this.artistName = itemView.findViewById(R.id.list_item_artist_name);
        }

        public void onBind(Artist artist) {
            artistName.setText(artist.getName());
        }
    }
}
