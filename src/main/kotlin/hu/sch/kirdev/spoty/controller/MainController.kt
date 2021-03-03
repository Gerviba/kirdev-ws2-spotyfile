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

    // TODO: GET / ?query=QUERY & prepared=0..3
    @GetMapping("/")
    fun index(
        @RequestParam(defaultValue = "", required = false) query: String,
        @RequestParam(defaultValue = "0", required = false) prepared: Int,
        model: Model
    ): String {

        model.addAttribute("query", query)
        model.addAttribute("motd", "Kici, kinai, változat")

        when {
            prepared == 0 -> {
                if (query.isBlank()) {
                    model.addAttribute("results", musicService.getAll())
                } else {
                    model.addAttribute("results", musicService.search(query))
                }
            }
            prepared == 1 -> model.addAttribute("results", musicService.searchNewest())
            prepared == 2 -> model.addAttribute("results", musicService.searchPopular())
            prepared == 3 -> model.addAttribute("results", musicService.searchUnique())
        }
        return "index"
    }

    // TODO: GET /track/id -> track:MusicEntity
    @GetMapping("/track/{id}")
    fun details(@PathVariable id: Long, model: Model): String {
        model.addAttribute("track",
            musicService.getTrackOrNull(id) ?: MusicEntity(-1, name="Music $id not found"))
        return "details"
    }

}