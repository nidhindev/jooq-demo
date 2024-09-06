package com.nidhin.jooq.demo.repositories.jooq

import com.nidhin.jooq.demo.jooq.tables.references.OLYMPICS
import com.nidhin.jooq.demo.jooq.tables.references.PARTICIPANT
import org.jooq.DSLContext
import org.springframework.stereotype.Component

@Component
class JooqRepository(private val context: DSLContext,) {

    fun findNamesForSport(sport: String): List<String> {

        return context.select(PARTICIPANT.NAME)
            .from(PARTICIPANT)
            .join(OLYMPICS).on(PARTICIPANT.OLYMPICS_ID.eq(OLYMPICS.ID))
            .where(OLYMPICS.SPORT.eq(sport))
            .fetch(PARTICIPANT.NAME)
            .mapNotNull { it }

    }
}