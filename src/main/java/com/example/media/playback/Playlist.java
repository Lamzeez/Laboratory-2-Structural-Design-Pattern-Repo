package com.example.media.playback;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements Playable {
    private final String id;
    private final String title;
    private final List<Playable> children = new ArrayList<>();

    public Playlist(String id, String title) {
        this.id = id; this.title = title;
    }

    public void add(Playable p) { children.add(p); }
    public void remove(Playable p) { children.remove(p); }

    @Override
    public String id() { return id; }
    @Override
    public String title() { return title; }

    @Override
    public void play(MediaPlayerContext ctx) throws InterruptedException {
        System.out.printf("[Playlist] Begin playlist '%s' (%d items)%n", title, children.size());
        for (Playable p : children) {
            p.play(ctx);
        }
        System.out.printf("[Playlist] Finished playlist '%s'%n", title);
    }
}
