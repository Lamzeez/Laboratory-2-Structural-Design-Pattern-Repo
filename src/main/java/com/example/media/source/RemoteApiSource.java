package com.example.media.source;

import java.io.Closeable;

public class RemoteApiSource implements MediaSource {
    private final String endpoint;
    public RemoteApiSource(String endpoint) { this.endpoint = endpoint; }

    @Override
    public Closeable openStream() {
        System.out.printf("[RemoteApiSource] Fetching stream from API %s%n", endpoint);
        return () -> System.out.printf("[RemoteApiSource] closed API connection %s%n", endpoint);
    }

    @Override
    public String describe() { return "api:" + endpoint; }
}
