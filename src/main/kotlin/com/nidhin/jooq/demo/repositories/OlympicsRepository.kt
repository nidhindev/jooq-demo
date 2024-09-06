package com.nidhin.jooq.demo.repositories

import com.nidhin.jooq.demo.entities.Olympics
import com.nidhin.jooq.demo.jooq.tables.references.OLYMPICS
import org.jooq.DSLContext
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface OlympicsRepository: JpaRepository<Olympics, UUID> {
}

interface OlympicsRepositoryExt {

    fun jSave(olympics: Olympics)
    fun findSportByName(name: String)
}

class OlympicsRepositoryExtImpl(private val context: DSLContext): OlympicsRepositoryExt {

    override fun jSave(olympics: Olympics) {

        context.insertInto(OLYMPICS)
            .set(OLYMPICS.SPORT,  olympics.sport)
            .set(OLYMPICS.VENUE, olympics.venue)
    }

    override fun findSportByName(name: String) {
        TODO("Not yet implemented")
    }

}