package net.lightning_recipes.mixins;

import net.lightning_recipes.LightningRecipes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LightningRodBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningEntity.class)
public class LightningEntityMixin {

    @Inject(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LightningEntity;cleanOxidization(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V", shift = At.Shift.AFTER))
    public void injectedOnTick(CallbackInfo ci) {
        LightningRecipes.debugMsg("Lightning stroke");
        BlockPos pos = ((LightningEntityInvoker) this).invokedGetAffectedBlockPos();
        World world = MinecraftClient.getInstance().player.world;

        BlockState blockState = world.getBlockState(pos);
        BlockPos blockForRecipePos;
        BlockState blockForRecipeState;
        if (blockState.isOf(Blocks.LIGHTNING_ROD)) {
            blockForRecipePos = pos.offset(((Direction)blockState.get(LightningRodBlock.FACING)).getOpposite());
            blockForRecipeState = world.getBlockState(blockForRecipePos);
            checkForRecipes(blockForRecipeState);
        }
    }

    private static void checkForRecipes(BlockState blockState) {
        LightningRecipes.debugMsg("Checking for recipes for " + blockState.getBlock().toString());
    }
}
