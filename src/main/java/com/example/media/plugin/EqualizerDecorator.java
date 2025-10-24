package com.example.media.plugin;

import com.example.media.plugin.base.RendererDecorator;

import java.io.Closeable;

public class EqualizerDecorator extends RendererDecorator implements Plugin {
    public EqualizerDecorator(MediaRenderer wrapped) { super(wrapped); }

    @Override
    public void render(Closeable stream, String title) throws InterruptedException {
        System.out.printf("[Equalizer] Adjusting audio for '%s'%n", title);
        wrapped.render(stream, title);
    }

    @Override
    public String name() { return "equalizer"; }

    @Override
    public String getName() { return wrapped.getName() + "+eq"; }
}
