package com.example.media;

import com.example.media.plugin.MediaRenderer;

public class MediaPlayerContext {
    private MediaRenderer renderer;

    public MediaPlayerContext(MediaRenderer renderer) {
        this.renderer = renderer;
    }

    public MediaRenderer getRenderer() { return renderer; }
    public void setRenderer(MediaRenderer r) { this.renderer = r; }
}
