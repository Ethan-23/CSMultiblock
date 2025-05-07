package org.evasive.me.cSMultiblock.multiblocks.moltenforge.recipes;

import org.evasive.me.cSMultiblock.multiblocks.RecipeBuilder;

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
