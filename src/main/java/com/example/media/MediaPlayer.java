package com.example.media;

import com.example.media.facade.MediaFacade;
import com.example.media.playback.Playable;

public class MediaPlayer {
    private final MediaFacade facade;
    public MediaPlayer(MediaFacade f) { this.facade = f; }

    public void play(Playable item) { facade.play(item); }
    public void setRenderer(com.example.media.plugin.MediaRenderer r) { facade.setRenderer(r); }
}
