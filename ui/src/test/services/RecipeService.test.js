import {RecipeService} from "@app/services/ApplicationProxy";

const recipeService = new RecipeService("http://localhost:8950/api/");

describe("getRecipe", () => {

    test("returnsNotFoundWhenRecipeNotFound", async () => {
        try {
            await recipeService.get(500);
            expect(true).toBeFalsy();
        }
        catch (e) {
            expect(e.response.status).toBe(404);
        }

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
});

describe("createRecipe", () => {

    test("returnsBadRequestWhenInvalidRecipeGiven", async () => {
        try {
            await recipeService.create({
                name: null,
                description: null,
                prepTime: 20,
                cookTime: 60,
                servings: 10,
                servingsUnit: "people"
            });

            //if we get here, it didn't throw an error
            expect(true).toBeFalsy();
        }
        catch (e) {
            expect(e.response.status).toBe(400);
        }
    });

    test("returnsOkWhenGivenValidRecipeToCreate", async () => {
        let created = await recipeService.create({
            name: "Potato Salad",
            description: "Salad that is made of Potatoes and a whole lot of eggs",
            prepTime: 20,
            cookTime: 60,
            servings: 10,
            servingsUnit: "people"
        });

        expect(created).toBeDefined();

        expect(created.id).toBeDefined();
        expect(created.name).toBeDefined();
        expect(created.description).toBeDefined();
        expect(created.prepTime).toBeDefined();
        expect(created.cookTime).toBeDefined();
        expect(created.servings).toBeDefined();
        expect(created.servingsUnit).toBeDefined();
    });
});


