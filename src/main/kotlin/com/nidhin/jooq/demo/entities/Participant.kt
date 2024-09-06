package com.nidhin.jooq.demo.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
@Table
class Participant {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    var id: UUID? = null

    @Column
    var name: String? = null

    @Column
    var age: Int? = null

    @Column
    var sex: String? = null

    @ManyToOne
    @JoinColumn(
        name = "olympics_id",
        nullable = false,
        foreignKey = ForeignKey(name = "fk_participant__olympics_id")
    )
    @JsonIgnore
    var olympics: Olympics? = null
}