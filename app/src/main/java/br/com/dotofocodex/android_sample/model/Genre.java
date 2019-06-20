package br.com.dotofocodex.android_sample.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Genre extends ExpandableGroup<Artist> {

    public Genre(String title, List<Artist> items) {
        super(title, items);
    }
}
