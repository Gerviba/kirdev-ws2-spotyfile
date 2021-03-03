package hu.sch.kirdev.spoty.repository

import hu.sch.kirdev.spoty.model.MusicEntity
import org.springframework.data.repository.CrudRepository

interface MusicRepository : CrudRepository<MusicEntity, Long> {

    fun findAllByYear(year: Int): List<MusicEntity>

    // TODO: top 100 newest

    // TODO: top 100 most loved

    // TODO: other

}