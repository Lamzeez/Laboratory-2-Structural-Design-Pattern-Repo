package com.example.media.facade;

import com.example.media.playback.*;
import com.example.media.plugin.MediaRenderer;
// import com.example.media.source.MediaSource;

public class MediaFacade {
    private final MediaPlayerContext context;

    public MediaFacade(MediaRenderer renderer) {
        this.context = new MediaPlayerContext(renderer);
    }

    public void setRenderer(MediaRenderer r) { context.setRenderer(r); }
    public MediaPlayerContext getContext() { return context; }

    public void play(Playable item) {
        try {
            item.play(context);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
