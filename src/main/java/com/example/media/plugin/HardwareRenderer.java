package com.example.media.plugin;

import java.io.Closeable;

public class HardwareRenderer implements MediaRenderer {
    @Override
    public void render(Closeable stream, String title) throws InterruptedException {
        System.out.printf("[HardwareRenderer] hardware decoding and rendering '%s'...%n", title);
        Thread.sleep(400); // simulate fast decode
        System.out.printf("[HardwareRenderer] done rendering '%s'%n", title);
    }

    @Override
    public String getName() { return "HardwareRenderer"; }
}
