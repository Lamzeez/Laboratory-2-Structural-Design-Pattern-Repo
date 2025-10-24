package com.example.media.plugin;

import java.io.Closeable;

public class SoftwareRenderer implements MediaRenderer {
    @Override
    public void render(Closeable stream, String title) throws InterruptedException {
        System.out.printf("[SoftwareRenderer] software decoding and rendering '%s'...%n", title);
        Thread.sleep(800); // simulate slower decode
        System.out.printf("[SoftwareRenderer] done rendering '%s'%n", title);
    }

    @Override
    public String getName() { return "SoftwareRenderer"; }
}
