package com.example.media.plugin;

import java.io.Closeable;

public interface MediaRenderer {
    void render(Closeable stream, String title) throws InterruptedException;
    String getName();
}
