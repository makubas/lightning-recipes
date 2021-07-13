package net.lightning_recipes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.lightning_recipes.lib.LightningRecipe;
import net.lightning_recipes.lib.LightningRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LightningRecipes implements ModInitializer {
	public static final String MOD_ID = "lightning_recipes";
	public static final Logger logger = LogManager.getLogger("LightningRecipes");

	@Override
	public void onInitialize() {
		logger.info("Lightning Recipes initialising...");

		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			debugMsg("##############################################################");
			debugMsg("# WARNING: Lightning Recipes Library is running in dev mode! #");
			debugMsg("##############################################################");
		}

		Registry.register(Registry.RECIPE_SERIALIZER, LightningRecipeSerializer.ID, LightningRecipeSerializer.INSTANCE);
		Registry.register(Registry.RECIPE_TYPE, new Identifier(MOD_ID, LightningRecipe.Type.ID), LightningRecipe.Type.INSTANCE);

		logger.info("Lightning Recipes successfully initialized");
	}

	public static void debugMsg(String msg) {
		logger.warn("[DEBUG] " + msg);
	}
}
