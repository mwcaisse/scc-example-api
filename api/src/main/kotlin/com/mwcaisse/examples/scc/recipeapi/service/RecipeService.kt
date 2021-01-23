package com.mwcaisse.examples.scc.recipeapi.service

import com.mwcaisse.examples.scc.recipeapi.viewmodel.*

interface RecipeService {

    fun get(id: Long) : RecipeViewModel

    fun getAll() : List<RecipeViewModel>

    fun create(toCreate : CreateRecipeViewModel) : RecipeViewModel

    fun delete(id: Long)

    fun addIngredient(id : Long, ingredient: CreateIngredientViewModel) : IngredientViewModel

    fun deleteIngredient(id: Long, ingredientId: Long)

    fun addStep(id : Long, step: CreateStepViewModel) : StepViewModel

    fun deleteStep(id : Long, stepId : Long)

}