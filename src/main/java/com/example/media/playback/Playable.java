package com.example.media.playback;

public interface Playable {
    String id();
    String title();
    void play(MediaPlayerContext context) throws InterruptedException;
}
