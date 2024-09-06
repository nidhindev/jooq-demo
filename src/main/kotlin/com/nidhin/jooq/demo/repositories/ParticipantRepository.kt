package com.nidhin.jooq.demo.repositories

import com.nidhin.jooq.demo.entities.Participant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ParticipantRepository: JpaRepository<Participant, UUID> {

    fun findAllByOlympicsSport(sport: String): List<Participant>
}