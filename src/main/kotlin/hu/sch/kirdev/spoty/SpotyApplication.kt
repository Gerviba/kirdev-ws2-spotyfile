package hu.sch.kirdev.spoty

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpotyApplication

fun main(args: Array<String>) {
    runApplication<SpotyApplication>(*args)
}
