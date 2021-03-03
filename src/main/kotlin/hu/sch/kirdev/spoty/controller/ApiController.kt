package hu.sch.kirdev.spoty.controller

import hu.sch.kirdev.spoty.model.MusicEntity
import hu.sch.kirdev.spoty.service.MusicService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api")
@RestController
class ApiController {

    @Autowired
    private lateinit var musicService: MusicService

    @GetMapping("/top10")
    fun fetchTop10(): List<MusicEntity> {
        return musicService.getRandom10()
    }

}