package com.mwcaisse.examples.scc.recipeapi.service

import com.mwcaisse.examples.scc.recipeapi.exception.EntityNotFoundException
import com.mwcaisse.examples.scc.recipeapi.exception.EntityValidationException
import com.mwcaisse.examples.scc.recipeapi.viewmodel.*
import org.springframework.stereotype.Component

@Component
class StaticRecipeService : RecipeService {

    private val existingRecipeId : Long = 1L
    private val existingIngredientId : Long = 1L
    private val existingStepId : Long = 1L

    override fun get(id: Long): RecipeViewModel {
        recipeExists(id)

        return RecipeViewModel(
            id = existingRecipeId,
            name = "Fluffy Pancakes",
            description = "They are pancakes, what else do you need to know?",
            prepTime = 5,
            cookTime = 20,
            servings = 6,
            servingsUnit = "pancakes",
            steps = listOf(
                StepViewModel(
                    id = 1,
                    order = 1,
                    description ="Mix up the flour and stuff"
                ),
                StepViewModel(
                    id = 2,
                    order = 2,
                    description ="Whisk up the eggs and stuff"
                ),
                StepViewModel(
                    id = 3,
                    order = 3,
                    description ="Pour the mixed and whisked stuff onto a pan"
                )
            ),
            ingredients = listOf(
                IngredientViewModel(
                    id = 1,
                    description = "10 cups flour"
                ),
                IngredientViewModel(
                    id = 2,
                    description = "15 eggs"
                ),
                IngredientViewModel(
                    id = 3,
                    description = "20 cups sugar"
                )
            )
        )
    }

    override fun getAll(): List<RecipeViewModel> {
        TODO("Not yet implemented")
    }

    override fun create(toCreate: CreateRecipeViewModel): RecipeViewModel {
        validateRecipe(toCreate)

        return RecipeViewModel(
            id = 2,
            name = toCreate.name,
            description = toCreate.description,
            prepTime = toCreate.prepTime,
            cookTime = toCreate.cookTime,
            servings = toCreate.servings,
            servingsUnit = toCreate.servingsUnit,
            steps = emptyList(),
            ingredients = emptyList()
        )
    }



    override fun delete(id: Long) {
        recipeExists(id)
    }

    override fun addIngredient(id: Long, ingredient: CreateIngredientViewModel): IngredientViewModel {
        recipeExists(id)
        validateIngredient(ingredient)

        return IngredientViewModel(
            id = 1,
            description = ingredient.description
        )
    }

    override fun deleteIngredient(id: Long, ingredientId: Long) {
        recipeExists(id)
        ingredientExists(ingredientId)
    }

    override fun addStep(id: Long, step: CreateStepViewModel): StepViewModel {
        recipeExists(id)
        validateStep(step)

        return StepViewModel(
            id = 1,
            order = step.order,
            description = step.description
        )
    }

    override fun deleteStep(id: Long, stepId: Long) {
        recipeExists(id)
        stepExists(stepId)
    }

    fun recipeExists(id: Long) {
        if (existingRecipeId != id) {
            recipeNotFound(id)
        }
    }

    fun ingredientExists(id: Long) {
        if (existingIngredientId != id) {
            recipeNotFound(id)
        }
    }

    fun stepExists(id: Long) {
        if (existingStepId != id) {
            recipeNotFound(id)
        }
    }


    fun validateRecipe(recipe : CreateRecipeViewModel) {
        if (recipe.name.isBlank()) {
            throw EntityValidationException("Recipe name cannot be blank!")
        }

        if (recipe.description.isBlank()) {
            throw EntityValidationException("Recipe name cannot be blank!")
        }
    }

    fun validateStep(step : CreateStepViewModel) {
        if (step.description.isBlank()) {
            throw EntityValidationException("Step description cannot be blank!")
        }
        if (step.order < 0) {
            throw EntityValidationException("Step order cannot be less than 0!")
        }
    }

    fun validateIngredient(ingredient: CreateIngredientViewModel) {
        if (ingredient.description.isBlank()) {
            throw EntityValidationException("Ingredient description cannot be blank!")
        }
    }

    fun recipeNotFound(id: Long) {
        throw EntityNotFoundException("Recipe", id)
    }
}