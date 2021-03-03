package hu.sch.kirdev.spoty.controller

import hu.sch.kirdev.spoty.service.MusicService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class MainController {

    @Autowired
    private lateinit var musicService: MusicService

    // TODO: GET / ?query=QUERY & prepared=0..3
    @GetMapping("/")
    fun index() = "index"

    // TODO: GET /track/id -> track:MusicEntity
    @GetMapping("/track/{id}")
    fun details(@PathVariable id: Long, model: Model): String {
        return "details"
    }

}