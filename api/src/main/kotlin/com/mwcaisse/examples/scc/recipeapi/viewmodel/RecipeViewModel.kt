package com.mwcaisse.examples.scc.recipeapi.viewmodel

data class RecipeViewModel(
    val id: Long,
    val name: String,
    val description: String,
    val prepTime: Int,
    val cookTime: Int,
    val servings: Int,
    val servingsUnit: String,
    val steps: List<StepViewModel>,
    val ingredients: List<IngredientViewModel>
)