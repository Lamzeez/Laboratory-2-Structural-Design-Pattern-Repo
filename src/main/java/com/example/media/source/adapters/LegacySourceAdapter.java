package com.example.media.source.adapters;

import com.example.media.source.MediaSource;
import java.io.Closeable;

public class LegacySourceAdapter implements MediaSource {
    private final Object legacy;

    public LegacySourceAdapter(Object legacy) {
        this.legacy = legacy;
    }

    @Override
    public Closeable openStream() {
        System.out.println("[LegacySourceAdapter] Adapting legacy source");
        return () -> System.out.println("[LegacySourceAdapter] closed legacy source");
    }

    @Override
    public String describe() {
        return "legacy-adapted";
    }
}
