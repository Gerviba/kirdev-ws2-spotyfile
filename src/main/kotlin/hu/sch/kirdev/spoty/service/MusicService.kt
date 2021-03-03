package hu.sch.kirdev.spoty.service

import hu.sch.kirdev.spoty.TrackListDto
import hu.sch.kirdev.spoty.model.MusicEntity
import hu.sch.kirdev.spoty.repository.MusicRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

// NOTE: Open class to support transaction proxy generation
@Service
open class MusicService {

    @Autowired
    private lateinit var repo: MusicRepository

    fun getAll(): List<MusicEntity> {
        return repo.findAll().toList()
    }

    fun search(query: String): List<MusicEntity> {
        return repo.findAllByNameContainingIgnoreCaseOrArtistsContainingIgnoreCase(query, query)
    }

    fun searchNewest(): List<MusicEntity> {
        return repo.findAllByOrderByYear()
    }

    fun searchPopular(): List<MusicEntity> {
        return repo.findAllByOrderByPopularityDesc()
    }

    fun searchUnique(): List<MusicEntity> {
        return repo.findAllByOrderByTempoDesc()
    }

    @Transactional(readOnly = true)
    fun getRandom10(): List<MusicEntity> {
        return repo.findAll().take(10) // TODO: Solve it better
    }

    @Transactional(readOnly = true)
    fun getTrack(id: Long): MusicEntity {
        return repo.findById(id).orElseThrow { RuntimeException("No track was found with id: $id") }
    }

    @Transactional(readOnly = true)
    fun getTrackOrNull(id: Long): MusicEntity? {
        return repo.findById(id).orElse(null)
    }

    // NOTE: Not readonly
    @Transactional(readOnly = false)
    fun addTrack(trackDto: TrackListDto) {

        // NOTE: Parse Dto to Entity
        val entity = MusicEntity(
            0,
            trackId = trackDto.id,
            year = trackDto.year.toInt(),
            loudness = trackDto.loudness.toFloat(),
            liveness = trackDto.liveness.toFloat(),
            tempo = trackDto.tempo.toFloat(),
            valence= trackDto.valence.toFloat(),
            instrumentalness = trackDto.instrumentalness.toFloat(),
            danceability = trackDto.danceability.toFloat(),
            speechiness = trackDto.speechiness.toFloat(),

            // NOTE: ms to sec
            duration = if (trackDto.duration.isBlank()) 0 else (trackDto.duration.toInt() / 1000),
            explicit = trackDto.explicit.toInt(),
            mode = trackDto.mode.toInt(),

            // NOTE: Zero effort solution to parse string list json
            artists = trackDto.artists.replace("['", "")
                        .replace("']", "")
                        .replace("','", ", ")
                        .replace("', '", ", "),

            // NOTE: release_date -> releaseDate
            releaseDate = trackDto.release_date,
            acousticness = trackDto.acousticness.toFloat(),
            popularity = trackDto.popularity.toInt(),
            name = trackDto.name,
            key = trackDto.key.toInt(),
            energy = trackDto.energy.toFloat(),
            cover = trackDto.cover
        )

        repo.save(entity)
        println("Track ${trackDto.id} loaded")
    }

}