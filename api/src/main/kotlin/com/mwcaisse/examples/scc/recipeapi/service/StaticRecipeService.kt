package com.mwcaisse.examples.scc.recipeapi.service

import com.mwcaisse.examples.scc.recipeapi.exception.EntityNotFoundException
import com.mwcaisse.examples.scc.recipeapi.viewmodel.*
import org.springframework.stereotype.Component

@Component
class StaticRecipeService : RecipeService {

    override fun get(id: Long): RecipeViewModel {
        if (1L == id) {
            return RecipeViewModel(
                id = 1,
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
        else {
            throw EntityNotFoundException("Recipe", id)
        }
    }

    override fun getAll(): List<RecipeViewModel> {
        TODO("Not yet implemented")
    }

    override fun create(toCreate: CreateRecipeViewModel): RecipeViewModel {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        if (1L != id) {
            throw EntityNotFoundException("Recipe", id)
        }
    }

    override fun addIngredient(id: Long, ingredient: CreateIngredientViewModel): IngredientViewModel {
        TODO("Not yet implemented")
    }

    override fun deleteIngredient(id: Long, ingredientId: Long) {
        TODO("Not yet implemented")
    }

    override fun addStep(id: Long, step: CreateStepViewModel): StepViewModel {
        TODO("Not yet implemented")
    }

    override fun deleteStep(id: Long, stepId: Long) {
        TODO("Not yet implemented")
    }
}