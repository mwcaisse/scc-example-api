package com.mwcaisse.examples.scc.recipeapi.viewmodel

data class CreateRecipeViewModel(
    val name: String,
    val description: String,
    val prepTime: Int,
    val cookTime: Int,
    val servings: Int,
    val servingsUnit: String
)