package com.example.media.plugin;

import com.example.media.plugin.base.RendererDecorator;

import java.io.Closeable;

public class WatermarkDecorator extends RendererDecorator implements Plugin {
    public WatermarkDecorator(MediaRenderer wrapped) { super(wrapped); }

    @Override
    public void render(Closeable stream, String title) throws InterruptedException {
        // pre-processing watermark
        System.out.printf("[Watermark] Applying watermark to '%s'%n", title);
        wrapped.render(stream, title);
        System.out.printf("[Watermark] Watermark finished for '%s'%n", title);
    }

    @Override
    public String name() { return "watermark"; }

    @Override
    public String getName() { return wrapped.getName() + "+watermark"; }
}
