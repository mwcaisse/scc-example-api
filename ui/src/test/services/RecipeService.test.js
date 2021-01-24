
import {RecipeService} from "@app/services/ApplicationProxy";

describe("getRecipe", () => {

    const recipeService = new RecipeService("http://localhost:8080/api/");

    test("returnsNotFoundWhenRecipeNotFound", async () => {
        await expect(recipeService.get(500)).rejects.toThrow();
    })

    test("shouldReturnRecipeIfExists", async () => {
        const recipe = await recipeService.get(1);

        expect(recipe).toBeDefined();

        expect(recipe.id).toBeDefined();
        expect(recipe.name).toBeDefined();
        expect(recipe.description).toBeDefined();
        expect(recipe.prepTime).toBeDefined();
        expect(recipe.cookTime).toBeDefined();
        expect(recipe.servings).toBeDefined();
        expect(recipe.servingsUnit).toBeDefined();
        expect(recipe.steps).toBeDefined();
        expect(recipe.ingredients).toBeDefined();

        let steps = recipe.steps;
        let ingredients = recipe.ingredients;

        expect(steps.length).toBeGreaterThan(0);

        expect(steps[0].id).toBeDefined();
        expect(steps[0].order).toBeDefined();
        expect(steps[0].description).toBeDefined();

        expect(ingredients.length).toBeGreaterThan(0);
        expect(ingredients[0].id).toBeDefined();
        expect(ingredients[0].description).toBeDefined();
    });
})