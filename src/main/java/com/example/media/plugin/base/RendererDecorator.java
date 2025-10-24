package com.example.media.plugin.base;

import com.example.media.plugin.MediaRenderer;
// import java.io.Closeable;

public abstract class RendererDecorator implements MediaRenderer {
    protected final MediaRenderer wrapped;
    public RendererDecorator(MediaRenderer wrapped) { this.wrapped = wrapped; }
    @Override public String getName() { return wrapped.getName(); }
}
