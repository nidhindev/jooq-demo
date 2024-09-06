package com.nidhin.jooq.demo.services

import com.nidhin.jooq.demo.entities.Olympics
import com.nidhin.jooq.demo.repositories.OlympicsRepository
import com.nidhin.jooq.demo.repositories.ParticipantRepository
import com.nidhin.jooq.demo.repositories.jooq.JooqRepository
import org.springframework.stereotype.Service

@Service
class OlympicsService(
    private val olympicsRepository: OlympicsRepository,
    private val participantRepository: ParticipantRepository,
    private val jooqRepository: JooqRepository
) {

    fun createNewOlympicGame(olympics: Olympics) {

        olympicsRepository.save(olympics)
    }

    fun getNames(sport: String): List<String> {

        return participantRepository.findAllByOlympicsSport(sport)
            .mapNotNull { it.name }
    }

    fun getNamesJooq(sport: String): List<String> {

        return jooqRepository.findNamesForSport(sport)
    }

}