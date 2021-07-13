package net.lightning_recipes.lib;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.block.Block;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.lightning_recipes.LightningRecipes.MOD_ID;

public class LightningRecipeSerializer implements RecipeSerializer<LightningRecipe> {
    private LightningRecipeSerializer() {
    }

    public static final LightningRecipeSerializer INSTANCE = new LightningRecipeSerializer();
    public static final Identifier ID = new Identifier(MOD_ID, "lightning_recipe");

    @Override
    public LightningRecipe read(Identifier id, JsonObject json) {
        LightningRecipeJsonFormat recipeJson = new Gson().fromJson(json, LightningRecipeJsonFormat.class);

        if (recipeJson.input == null || recipeJson.output == null) {
            throw new JsonSyntaxException("A required attribute is missing!");
        }
        Ingredient input = Ingredient.fromJson(recipeJson.input);
        Block output = Registry.BLOCK.getOrEmpty(new Identifier(recipeJson.output)).get();

        return new LightningRecipe(input, output, id);
    }

    @Override
    public void write(PacketByteBuf buf, LightningRecipe recipe) {
        recipe.getInput().write(buf);
        buf.writeItemStack(recipe.getOutput());
    }

    @Override
    public LightningRecipe read(Identifier id, PacketByteBuf buf) {
        Ingredient input = Ingredient.fromPacket(buf);
        Block output = Block.getBlockFromItem(buf.readItemStack().getItem());
        return new LightningRecipe(input, output, id);
    }
}
