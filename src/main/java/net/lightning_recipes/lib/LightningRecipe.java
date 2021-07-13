package net.lightning_recipes.lib;

import net.minecraft.block.Block;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class LightningRecipe implements Recipe<SimpleInventory> {
    private final Ingredient input;
    private final Block output;
    private final Identifier id;

    public LightningRecipe(Ingredient input, Block output, Identifier id) {
        this.input = input;
        this.output = output;
        this.id = id;
    }

    public Ingredient getInput() {
        return this.input;
    }

    public static class Type implements RecipeType<LightningRecipe> {
        private Type() {
        }

        public static final Type INSTANCE = new Type();

        public static final String ID = "lightning_recipe";
    }

    @Override
    public boolean matches(SimpleInventory simpleInventory, World world) {
        if (simpleInventory.size() < 1) return false;
        return input.test(simpleInventory.getStack(0));
    }
    @Override
    public ItemStack craft(SimpleInventory simpleInventory) {
        return ItemStack.EMPTY;
    }
    @Override
    public boolean fits(int var1, int var2) {
        return false;
    }
    @Override
    public ItemStack getOutput() {
        return new ItemStack(output.asItem());
    }
    @Override
    public Identifier getId() {
        return id;
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return LightningRecipeSerializer.INSTANCE;
    }
    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
}
