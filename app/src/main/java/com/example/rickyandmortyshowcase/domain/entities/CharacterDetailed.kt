package com.example.rickyandmortyshowcase.domain.entities

data class CharacterDetailed(
    val id: String,
    val name: String,
    val status: String,
    val image: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String
)
