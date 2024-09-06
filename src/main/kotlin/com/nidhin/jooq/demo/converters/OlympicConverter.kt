package com.nidhin.jooq.demo.converters

import com.nidhin.jooq.demo.entities.Olympics
import com.nidhin.jooq.demo.jooq.tables.records.OlympicsRecord
import org.springframework.stereotype.Component
import java.util.*

@Component
class OlympicConverter {

    fun toJOlympics(olympics: Olympics): OlympicsRecord {
        return OlympicsRecord().apply {
            id = UUID.randomUUID()
            sport = olympics.sport
            venue = olympics.venue
        }
    }
}