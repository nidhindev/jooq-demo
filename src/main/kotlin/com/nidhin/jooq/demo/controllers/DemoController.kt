package com.nidhin.jooq.demo.controllers

import com.nidhin.jooq.demo.api.OlympicsDto
import com.nidhin.jooq.demo.entities.toEntity
import com.nidhin.jooq.demo.services.OlympicsService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("demo")
class DemoController(
    private val olympicsService: OlympicsService
) {


    @GetMapping()
    fun healthCheck(): String {
        return "Looks good"
    }

    @PostMapping("/jpa/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody olympicsDto: OlympicsDto) {

        olympicsService.createNewOlympicGame(olympicsDto.toEntity())
    }

    @GetMapping("/jpa/create")
    fun getNames(@RequestParam sport: String): List<String> {
        return olympicsService.getNames(sport)
    }

    @GetMapping("/jooq/create")
    fun getNamesJooq(@RequestParam sport: String): List<String> {
        return olympicsService.getNamesJooq(sport)
    }
}