package hu.sch.kirdev.spoty.repository

import hu.sch.kirdev.spoty.model.MusicEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MusicRepository : CrudRepository<MusicEntity, Long> {

    fun findAllByYear(year: Int): List<MusicEntity>

    // SELECT * FROM tábla WHERE name LIKE %a1% OR artists LIKE %a2%
    fun findAllByNameContainingIgnoreCaseOrArtistsContainingIgnoreCase(name: String, artists: String): List<MusicEntity>

    fun findAllByOrderByYear(): List<MusicEntity>

    fun findAllByOrderByPopularityDesc(): List<MusicEntity>

    fun findAllByOrderByTempoDesc(): List<MusicEntity>

    // TODO: other

}