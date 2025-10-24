package com.example.media;

import com.example.media.facade.MediaFacade;
import com.example.media.playback.*;
import com.example.media.source.*;
import com.example.media.source.adapters.LegacySourceAdapter;
import com.example.media.source.proxy.StreamProxy;
import com.example.media.plugin.*;

public class Main {
    public static void main(String[] args) {
        try {
            //SOURCES
            MediaSource local = new LocalFileSource("/media/song.mp3");
            MediaSource hls = new HlsSource("https://example.com/live.m3u8");
            MediaSource remoteApi = new RemoteApiSource("https://api.example.com/stream/123");

            //Cached via Proxy
            MediaSource cachedApi = new StreamProxy(remoteApi, "stream-123");

            //MEDIA FILES
            MediaFile f1 = new MediaFile("f1", "Local Song", local);
            MediaFile f2 = new MediaFile("f2", "Live Stream", hls);
            MediaFile f3 = new MediaFile("f3", "API Track (proxied)", cachedApi);

            //COMPOSITE PLAYLIST (with sub-playlist)
            Playlist sub = new Playlist("pl2", "Sub-Playlist");
            sub.add(f1);
            sub.add(f2);

            Playlist main = new Playlist("pl1", "Main Playlist");
            main.add(sub);
            main.add(f3);

            //RENDERERS (Bridge)
            MediaRenderer hw = new HardwareRenderer();
            MediaRenderer sw = new SoftwareRenderer();

            //DECORATOR TOGGLING (wrap/unwrap the renderer)
            //Starting with watermark + subtitle over hardware renderer -- DEFAULT DECORATOR SETUP
            MediaRenderer decoratedHW = new WatermarkDecorator(new SubtitleDecorator(hw));

            //FACADE + PLAYER
            MediaFacade facade = new MediaFacade(decoratedHW);
            MediaPlayer player = new MediaPlayer(facade);

            //---- DEMO PHASES ----

            //DEMO A) Play the full playlist with watermark+subtitle (hardware)
            System.out.println("\n=== Demo A: Playlist with watermark+subtitle (Hardware) ===");
            player.play(main);

            //DEMO B) Toggle watermark OFF (remove decorator) but keep subtitles on hardware
            System.out.println("\n=== Demo B: Toggle watermark OFF (Subtitle only, Hardware) ===");
            MediaRenderer subtitleOnlyHW = new SubtitleDecorator(hw);
            player.setRenderer(subtitleOnlyHW);
            player.play(f1); //quick play a single item to show effect

            //DEMO C) Switch renderer to Software and add Equalizer (Decorator on new renderer)
            System.out.println("\n=== Demo C: Switch to Software renderer + Equalizer ===");
            MediaRenderer eqOnSW = new EqualizerDecorator(sw);
            player.setRenderer(eqOnSW);
            player.play(f2);

            //DEMO D) Remote cached stream: first play = cache miss, second play = cache hit
            System.out.println("\n=== Demo D: Proxied Remote (Cache miss then hit) ===");
            //ensures a clean slate if your StreamProxy exposes clearCache()
            StreamProxy.clearCache(); //no-op if already empty
            player.play(f3); //miss -> fetch + cache
            player.play(f3); //hit -> serve from cache

            //DEMO E) Optional: Adapter demo (legacy source wrapped to MediaSource)
            System.out.println("\n=== Demo E: Legacy source via Adapter ===");
            Object legacy = new Object(); //simulates legacy object/SDK
            MediaSource adapted = new LegacySourceAdapter(legacy);
            player.setRenderer(new HardwareRenderer()); //plain hardware for contrast
            player.play(new MediaFile("legacy", "Legacy Clip", adapted));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
