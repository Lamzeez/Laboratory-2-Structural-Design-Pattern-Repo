package com.example.media.source;

import java.io.Closeable;

public class HlsSource implements MediaSource {
    private final String playlistUrl;
    public HlsSource(String url) { this.playlistUrl = url; }

    @Override
    public Closeable openStream() {
        System.out.printf("[HlsSource] Connecting to HLS %s%n", playlistUrl);
        return () -> System.out.printf("[HlsSource] disconnected %s%n", playlistUrl);
    }

    @Override
    public String describe() { return "hls:" + playlistUrl; }
}
