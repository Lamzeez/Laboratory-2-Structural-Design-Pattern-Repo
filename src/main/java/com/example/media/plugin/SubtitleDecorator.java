package com.example.media.plugin;

import com.example.media.plugin.base.RendererDecorator;

import java.io.Closeable;

public class SubtitleDecorator extends RendererDecorator implements Plugin {
    public SubtitleDecorator(MediaRenderer wrapped) { super(wrapped); }

    @Override
    public void render(Closeable stream, String title) throws InterruptedException {
        System.out.printf("[Subtitle] Rendering subtitles for '%s'%n", title);
        wrapped.render(stream, title);
    }

    @Override
    public String name() { return "subtitle"; }

    @Override
    public String getName() { return wrapped.getName() + "+subtitle"; }
}
