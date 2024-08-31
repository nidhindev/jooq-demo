package com.nidhin.jooq.demo

import com.nidhin.jooq.demo.JooqDemo.Companion.LOG
import jakarta.annotation.PostConstruct
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class JooqDemo {

    companion object {
        val LOG: Logger = LoggerFactory.getLogger(JooqDemo::class.java)
    }

    /**
     * Set the time zone to UTC to make sure it matches the timezone of the target environment
     */
    @PostConstruct
    fun started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }

}

fun main(args: Array<String>) {

    try {

        runApplication<JooqDemo>(*args)
    }
    catch (e: Exception) {
        LOG.error("Unable to start AuroraSsimExportService", e)
    }
}