package br.com.challenge.core.city

class City(
    val name: String,
    val temperature: Double
){

    companion object {

        fun create(name: String, temperature: Double) = City(name, temperature)
    }
}