package com.mwcaisse.examples.scc.recipeapi.viewmodel

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateIngredientViewModel(
    @JsonProperty("description")
    val description: String

)