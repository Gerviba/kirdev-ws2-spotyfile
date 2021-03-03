package hu.sch.kirdev.spoty.controller

import hu.sch.kirdev.spoty.model.MusicEntity
import hu.sch.kirdev.spoty.service.MusicService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@Controller
class MainController {

    @Autowired
    private lateinit var musicService: MusicService

//    @GetMapping("/")
//    fun index() = "index"

    @GetMapping("/")
    fun index(@RequestParam(defaultValue = "") query: String, model: Model): String {
        if (query.isBlank()) {
            model.addAttribute("results", musicService.getRandom10())
        } else {
            model.addAttribute("results", listOf<MusicEntity>())
        }

        model.addAttribute("motd", "Kici, kinai, valtozat")
        model.addAttribute("query", query)
        return "index"
    }

    @GetMapping("/track/{id}")
    fun details(@PathVariable id: Long, model: Model): String {
        model.addAttribute("track",
            musicService.getTrackOrNull(id) ?: MusicEntity(0, name = "Track not Found"))
        return "details"
    }

}