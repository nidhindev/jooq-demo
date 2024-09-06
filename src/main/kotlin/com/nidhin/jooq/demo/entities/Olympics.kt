package com.nidhin.jooq.demo.entities

import com.nidhin.jooq.demo.api.OlympicsDto
import com.nidhin.jooq.demo.api.ParticipantDto
import jakarta.persistence.*
import java.util.*

@Entity
@Table
class Olympics {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    var id: UUID? = null

    @Column
    var sport: String? = null

    @Column
    var venue: String? = null

    @OneToMany(targetEntity = Participant::class, mappedBy = "olympics", cascade = [CascadeType.ALL])
    var participants: MutableSet<Participant>? = null
        set(value) {
            value?.forEach { it.olympics = this }
            field = value
        }
}

fun OlympicsDto.toEntity(): Olympics {
    val dto = this
    return Olympics().apply {
        this.sport = dto.sport
        this.venue = dto.venue
        this.participants = dto.participants
            ?.map {
                Participant().apply {
                    name = it.name
                    age = it.age
                    sex = it.sex
                }
            }
            ?.toMutableSet()
    }
}

fun Olympics.toDto(): OlympicsDto {
    val entity = this
    return OlympicsDto(
        id = entity.id,
        sport = entity.sport,
        venue = entity.venue,
        participants = entity.participants
            ?.map {
                ParticipantDto(
                    id = it.id,
                    name = it.name,
                    age = it.age,
                    sex = it.sex
                )
            }
    )
}