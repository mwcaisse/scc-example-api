package com.mwcaisse.examples.scc.recipeapi.service

import com.mwcaisse.examples.scc.recipeapi.viewmodel.CreateRecipeViewModel
import com.mwcaisse.examples.scc.recipeapi.viewmodel.IngredientViewModel
import com.mwcaisse.examples.scc.recipeapi.viewmodel.RecipeViewModel
import com.mwcaisse.examples.scc.recipeapi.viewmodel.StepViewModel

interface RecipeService {

    fun get(id: Long) : RecipeViewModel

    fun getAll() : List<RecipeViewModel>

    fun create(toCreate : CreateRecipeViewModel) : RecipeViewModel

    fun delete(id: Long)

    fun addIngredient(id : Long, ingredient: IngredientViewModel) : IngredientViewModel

    fun deleteIngredient(id: Long, ingredientId: Long)

    fun addStep(id : Long, step: StepViewModel) : StepViewModel

    fun deleteStep(id : Long, stepId : Long)

}