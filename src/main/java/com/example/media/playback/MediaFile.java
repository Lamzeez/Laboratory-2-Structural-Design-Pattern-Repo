package com.example.media.playback;

import java.io.IOException;

import com.example.media.source.MediaSource;

public class MediaFile implements Playable {
    private final String id;
    private final String title;
    private final MediaSource source;

    public MediaFile(String id, String title, MediaSource source) {
        this.id = id;
        this.title = title;
        this.source = source;
    }

    @Override
    public String id() { return id; }

    @Override
    public String title() { return title; }

    @Override
    public void play(MediaPlayerContext context) throws InterruptedException {
        // Acquire stream from source (could be proxied)
        var stream = source.openStream();
        System.out.printf("[MediaFile] Playing '%s' from %s via renderer %s%n",
                title, source.describe(), context.getRenderer().getName());
        // Simulate streaming
        context.getRenderer().render(stream, title);
        // close stream
        try {
            stream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
