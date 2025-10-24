package com.example.media.source;

import java.io.Closeable;
// import java.io.IOException;

public class LocalFileSource implements MediaSource {
    private final String path;
    public LocalFileSource(String path) { this.path = path; }

    @Override
    public Closeable openStream() {
        System.out.printf("[LocalFileSource] Opening file %s%n", path);
        return () -> System.out.printf("[LocalFileSource] closed %s%n", path);
    }

    @Override
    public String describe() { return "local:" + path; }
}
