package br.com.challenge.application.config

import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

val objectMapper = jacksonObjectMapper().apply {
    propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
    setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
        indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
        indentObjectsWith(DefaultIndenter("  ", "\n"))
    })
    enable(SerializationFeature.INDENT_OUTPUT)
    enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
    disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
}
