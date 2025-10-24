package com.example.media.source.proxy;

import com.example.media.source.MediaSource;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;

public class StreamProxy implements MediaSource {
    private final MediaSource upstream;
    private static final Map<String, String> cache = new HashMap<>();
    private final String cacheKey;

    public StreamProxy(MediaSource upstream, String cacheKey) {
        this.upstream = upstream;
        this.cacheKey = cacheKey;
    }

    @Override
    public Closeable openStream() {
        if (cache.containsKey(cacheKey)) {
            System.out.printf("[StreamProxy] Serving cached stream for %s%n", cacheKey);
            return () -> System.out.printf("[StreamProxy] closed cached stream %s%n", cacheKey);
        } else {
            System.out.printf("[StreamProxy] Cache miss; fetching and caching %s%n", cacheKey);
            // open upstream and cache (simulate)
            var s = upstream.openStream();
            cache.put(cacheKey, "cached:" + cacheKey);
            return () -> {
                s.close();
                System.out.printf("[StreamProxy] closed upstream for %s%n", cacheKey);
            };
        }
    }

    @Override
    public String describe() { return "proxy(" + upstream.describe() + ")"; }

    // helper to clear cache for tests
    public static void clearCache() { cache.clear(); }
}
