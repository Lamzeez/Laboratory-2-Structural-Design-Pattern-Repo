Modular Media Streaming Suite - architecture overview

Goals:
- Support multiple media sources (local, HLS, remote API) via a single MediaSource interface.
- Allow runtime renderer strategy selection (hardware/software) via Bridge pattern.
- Enable on-the-fly feature plugins (subtitles, equalizer, watermark) via Decorator around renderer.
- Support composite playlists (files and nested playlists) via Composite pattern.
- Provide remote-proxying/caching using Proxy pattern.
- Offer an easy-to-use facade for assembly and playback.

Core packages:
- playback: Playable, MediaFile, Playlist, MediaPlayerContext
- source: MediaSource and concrete implementations; adapters and proxy subpackages
- plugin: MediaRenderer implementations (Bridge) and Decorator-based plugins
- facade: MediaFacade to simplify common flows
