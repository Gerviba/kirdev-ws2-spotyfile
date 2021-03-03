package hu.sch.kirdev.spoty

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import hu.sch.kirdev.spoty.service.MusicService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import java.nio.file.Files
import java.nio.file.Path
import javax.annotation.PostConstruct

@Configuration
class TestingConfig {

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Autowired
    private lateinit var musicService: MusicService

    @PostConstruct
    fun init() {

        val typeRef: TypeReference<List<TrackListDto>> = object : TypeReference<List<TrackListDto>>() {}
        val jsonInput = Files.readAllLines(Path.of("parsed_y2021.json")).joinToString("")
        val allTracks = mapper.readValue(jsonInput, typeRef).take(100)

        for (track in allTracks)
            musicService.addTrack(track)

        println("Loaded ${allTracks.size} tracks")

    }

}

data class TrackListDto(
    var id: String = "",
    var year: String = "",
    var loudness: String = "",
    var liveness: String = "",
    var tempo: String = "",
    var valence: String = "",
    var instrumentalness: String = "",
    var danceability: String = "",
    var speechiness: String = "",
    var duration: String = "",
    var explicit: String = "",
    var mode: String = "",
    var artists: String,
    var release_date: String = "",
    var acousticness: String = "",
    var popularity: String = "",
    var name: String = "",
    var key: String = "",
    var energy: String = "",
    var cover: String = ""
)
