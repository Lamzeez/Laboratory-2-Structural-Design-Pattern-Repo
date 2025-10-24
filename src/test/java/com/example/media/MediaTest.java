package com.example.media;

import com.example.media.playback.*;
import com.example.media.plugin.HardwareRenderer;
import com.example.media.source.LocalFileSource;
import org.junit.jupiter.api.Test;

public class MediaTest {
    @Test
    public void testPlaylistPlay() throws InterruptedException {
        var file = new MediaFile("t1","test", new LocalFileSource("tmp"));
        var pl = new Playlist("p1","p");
        pl.add(file);
        com.example.media.playback.MediaPlayerContext ctx = new com.example.media.playback.MediaPlayerContext(new HardwareRenderer());
        pl.play(ctx); // should not throw
    }
}
