package me.evasive.cSMultiblock.multiblocks.moltenforge.recipes;

import me.evasive.cSMultiblock.multiblocks.RecipeBuilder;

public enum MoltenForgeRecipes {
    HARDENED_IRON(new HardenedIron()),
    STEEL(new Steel()),
    GOLDERITE_BLOCK(new GolderiteBlock());


    private final RecipeBuilder recipeBuilder;

    MoltenForgeRecipes(RecipeBuilder recipeBuilder) {
        this.recipeBuilder = recipeBuilder;
    }

    public RecipeBuilder getRecipeBuilder(){
        return recipeBuilder;
    }
}
