Design rationale (summary, patterns & placement)

Goals & constraints:
Refactor a monolith into modular components, support multiple sources and runtime features, keep runtime swapping low-cost and safe, and provide a clear API for client code and UI/demo.

Patterns used and why:

1. Adapter (LegacySourceAdapter)
   - Problem: legacy source objects expose different APIs.
   - Use: wrap legacy sources with an adapter implementing MediaSource so the rest of the system depends on a single interface.
   - Benefit: isolates legacy glue code and allows incremental migration.

2. Composite (Playlist implements Playable)
   - Problem: playlists contain files and sub-playlists; playback must treat both uniformly.
   - Use: Playable is the component; MediaFile is a leaf; Playlist is a composite holding Playable children.
   - Benefit: simplifies playlist traversal and supports nested playlists easily.

3. Bridge (MediaRenderer abstractions)
   - Problem: rendering strategy (hardware vs software) must be swappable at runtime without changing high-level playback logic.
   - Use: MediaRenderer is the abstraction (bridge implementor); HardwareRenderer and SoftwareRenderer are concrete implementors.
   - Benefit: decouples playback (what to play) from rendering (how to render). New renderers can be added without touching playback code.

4. Decorator (watermark, subtitle, equalizer)
   - Problem: plugins are orthogonal to rendering and may be layered arbitrarily.
   - Use: renderer decorators wrap MediaRenderer instances to add behavior before/after render() calls.
   - Benefit: dynamic, runtime composition of plugins; no combinatorial explosion of combined classes.

5. Proxy (StreamProxy)
   - Problem: remote streams may be expensive/unstable; caching and local proxying are desirable.
   - Use: StreamProxy implements MediaSource and caches upstream results; clients talk to the proxy transparently.
   - Benefit: caching/latency improvements and a single place to add prefetching or authorization.

6. Facade (MediaFacade)
   - Problem: client/demo code must assemble multiple pieces (renderer, playlist, proxy) so tests and demos remain simple.
   - Use: MediaFacade provides start/stop/renderer-switching conveniences.
   - Benefit: reduces boilerplate in UI/demo flows and centralizes high-level orchestration.

Trade-offs & notes:
- This implementation simulates streams and rendering (console + sleep) to keep the lab runnable. In production, MediaSource.openStream would return real InputStream, and renderers would decode/pipe to audio/video subsystems.
- The Decorator wraps MediaRenderer, not the Playable, so feature effects are applied at render time — appropriate for rendering-focused concerns (watermarks, subtitles, EQ). If a plugin needed to mutate the stream (e.g., transcoding), consider a pipeline that decorates stream providers as well (Decorator on MediaSource).
- The Composite/Playlist delegates playback control; evolving Playable to return a PlaybackHandle with lifecycle operations would be best if seek/async parrallel playback is needed.
- Proxy caching is simple: an in-memory map keyed by logical ID. Production would use disk caches and TTL, plus HTTP partial reads.
- The Bridge/Decorator separation keeps concerns orthogonal: Renderer focus is how to draw/emit frames, Decorator focus is cross-cutting rendering features.

This arrangement demonstrates how structural patterns reduce coupling, increase extensibility, and keep runtime decisions simple for the UI/demo flows required by the lab.
