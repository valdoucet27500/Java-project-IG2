package barPackage.business;

import barPackage.controller.recipe.RecipeGenerationController;
import barPackage.model.Content;

import java.io.IOException;
import java.util.ArrayList;

public class RecipeGenerationThread implements Runnable {
    private ArrayList<Content> contents;
    private RecipeGenerationController recipeGenerationController;

    public RecipeGenerationThread(ArrayList<Content> contents, RecipeGenerationController recipeGenerationController) {
        this.contents = contents;
        this.recipeGenerationController = recipeGenerationController;
    }

    public void run() {
        StringBuilder request = new StringBuilder();
        request.append("Tu es barman depuis 20 ans. Donne moi une recette (Ingrédients, quantités et instructions) pour une personne que je peux faire avec ce que j'ai dans mon bar (tu n'est pas obligé d'utiliser tous les ingrédients, MAIS tu ne peut PAS utiliser des ingrédients qui ne sont pas présents dans le bar !). Dans mon bar, j'ai :");
        for (Content content : contents) {
            request.append(" - ").append(content.getQuantity()).append(" (").append(content.getUnit()).append(") de ").append(content.getConsumableName());
        }
        request.append(". Attention : La recette doit contenir uniquement des ingrédients cités ci-dessus. La recette doit être écrite en français.");
        try {
            recipeGenerationController.setTextArea(ChatGPT.chatGPT(request.toString()));
        } catch (IOException e) {
            recipeGenerationController.setTextArea("Erreur lors de la génération de la recette. Veuillez réessayer.");
        }
    }


}
