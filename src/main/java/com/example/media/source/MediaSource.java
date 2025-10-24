package com.example.media.source;

import java.io.Closeable;
// import java.io.IOException;

public interface MediaSource {
    // returns a stream (simulated by Closeable) — in real: InputStream or URL
    Closeable openStream();
    String describe();
}
