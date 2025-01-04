package Project2.be.service;

import Project2.be.model.Song;
import Project2.be.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    public List<Song> searchSongs(String query) {
        return songRepository.findByNameContainingIgnoreCase(query);
    }

    public Optional<Song> getSongById(Long id) {
        return songRepository.findById(id);
    }
}

