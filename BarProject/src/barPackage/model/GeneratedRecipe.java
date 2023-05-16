package barPackage.model;

import barPackage.controller.recipe.RecipeGenerationController;

import java.util.ArrayList;

public class GeneratedRecipe implements Runnable {
    private ArrayList<Content> contents;
    private RecipeGenerationController recipeGenerationController;
    public GeneratedRecipe(ArrayList<Content> contents, RecipeGenerationController recipeGenerationController) {
        this.contents = contents;
        this.recipeGenerationController = recipeGenerationController;
    }

    public void run() {
    }


}
