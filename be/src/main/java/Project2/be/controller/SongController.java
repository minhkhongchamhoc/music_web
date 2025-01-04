package Project2.be.controller;

import Project2.be.model.Song;
import Project2.be.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/songs")
@CrossOrigin(origins = "http://localhost:8080")
public class SongController {
    private static final Logger logger = LoggerFactory.getLogger(SongController.class);

    @Autowired
    private SongService songService;

    @GetMapping
    public List<Song> searchSongs(@RequestParam String query) {
        logger.info("Searching for songs with query: {}", query);  // Sử dụng logger
        List<Song> songs = songService.searchSongs(query);
        logger.info("Found {} songs", songs.size());  // Log số lượng bài hát tìm được
        return songs;
    }

    @GetMapping("/{id}/stream")
    public ResponseEntity<Resource> streamSong(@PathVariable Long id) throws IOException {
        Optional<Song> songOpt = songService.getSongById(id);
        if (songOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Song song = songOpt.get();
        Path file = Paths.get(song.getFilePath());
        Resource resource = new UrlResource(file.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .body(resource);
    }
}

