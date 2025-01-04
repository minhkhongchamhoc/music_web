package Project2.be;

import Project2.be.model.Song;
import Project2.be.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private SongRepository songRepository;

    @Override
    public void run(String... args) throws Exception {
        songRepository.saveAll(List.of(
                new Song("Song", "Artist 1", "/path/to/song1.mp3"),
                new Song("Song", "Artist 2", "/path/to/song2.mp3")
        ));
    }
}
