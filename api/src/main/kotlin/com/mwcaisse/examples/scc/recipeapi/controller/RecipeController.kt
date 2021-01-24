package com.mwcaisse.examples.scc.recipeapi.controller

import com.mwcaisse.examples.scc.recipeapi.service.RecipeService
import com.mwcaisse.examples.scc.recipeapi.viewmodel.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController()
@RequestMapping("/api/recipe/")
class RecipeController(private val recipeService: RecipeService) {

    @GetMapping("{id}")
    fun getRecipe(@PathVariable id: Long) : ResponseEntity<RecipeViewModel> {
        return ResponseEntity.ok(recipeService.get(id))
    }

    @GetMapping("")
    fun getAllRecipes() : ResponseEntity<List<RecipeViewModel>> {
        return ResponseEntity.ok(recipeService.getAll())
    }

    @PostMapping("")
    fun createRecipe(@RequestBody recipe : CreateRecipeViewModel) : ResponseEntity<RecipeViewModel> {
        return ResponseEntity.ok(recipeService.create(recipe))
    }

    @DeleteMapping("{id}")
    fun deleteRecipe(@PathVariable id: Long) : ResponseEntity<Void> {
        recipeService.delete(id)
        return ResponseEntity.ok().build()
    }

    @PostMapping("{id}/step/")
    fun addStep(@PathVariable id: Long, @RequestBody step: CreateStepViewModel) : ResponseEntity<StepViewModel> {
        return ResponseEntity.ok(recipeService.addStep(id, step))
    }

    @PostMapping("{id}/step/{stepId}")
    fun deleteStep(@PathVariable id: Long, @PathVariable stepId: Long) : ResponseEntity<Void>{
        recipeService.deleteStep(id, stepId)
        return ResponseEntity.ok().build()
    }


    @PostMapping("{id}/ingredient/")
    fun addIngredient(@PathVariable id: Long, @RequestBody ingredient: CreateIngredientViewModel) : ResponseEntity<IngredientViewModel> {
        return ResponseEntity.ok(recipeService.addIngredient(id, ingredient))
    }

    @PostMapping("{id}/ingredient/{ingredientId}")
    fun deleteIngredient(@PathVariable id: Long, @PathVariable ingredientId: Long) : ResponseEntity<Void> {
        recipeService.deleteIngredient(id, ingredientId)
        return ResponseEntity.ok().build()
    }
}