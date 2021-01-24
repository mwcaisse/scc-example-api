import Proxy from "@app/services/Proxy.js"

const defaultBaseUrl = "/api/";

class RecipeService {

    constructor(baseUrl = defaultBaseUrl) {
        this.proxy = new Proxy(baseUrl);
    }

    get(id) {
        return this.proxy.get(`recipe/${id}`);
    }

    create(recipe) {
        return this.proxy.post("recipe/", recipe);
    }

    deleteRecipe(id) {
        return this.proxy.delete(`recipe/${id}`);
    }

    addStep(id, step) {
        return this.proxy.post(`recipe/${id}/step/`, step);
    }

    deleteStep(id, stepId) {
        return this.proxy.delete(`recipe/${id}/step/${stepId}`);
    }

    addIngredient(id, ingredient) {
        return this.proxy.post(`recipe/${id}/ingredient/`, ingredient);
    }

    deleteIngredient(id, ingredientId) {
        return this.proxy.delete(`recipe/${id}/ingredient/${ingredientId}`);
    }
}


export {
    RecipeService
};