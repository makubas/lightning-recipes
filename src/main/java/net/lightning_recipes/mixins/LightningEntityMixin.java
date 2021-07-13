package net.lightning_recipes.mixins;

import net.lightning_recipes.LightningRecipes;
import net.lightning_recipes.lib.LightningRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LightningRodBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LightningEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(LightningEntity.class)
public class LightningEntityMixin {

    @Inject(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LightningEntity;cleanOxidization(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V", shift = At.Shift.AFTER))
    public void injectedOnTick(CallbackInfo ci) {
        BlockPos pos = ((LightningEntityInvoker) this).invokedGetAffectedBlockPos();
        World world = MinecraftClient.getInstance().player.world;
        BlockState blockState = world.getBlockState(pos);

        BlockPos blockForRecipePos;
        BlockState blockForRecipeState;

        System.out.println(blockState.getBlock());

        if (blockState.isOf(Blocks.LIGHTNING_ROD)) {
            System.out.println("1");
            blockForRecipePos = pos.offset(((Direction)blockState.get(LightningRodBlock.FACING)).getOpposite());
            blockForRecipeState = world.getBlockState(blockForRecipePos);
            checkForRecipes(blockForRecipeState, world, blockForRecipePos);
            System.out.println("2");
        }
    }

    private static void checkForRecipes(BlockState blockState, World world, BlockPos blockPos) {
        System.out.println("3");
        System.out.println(world.isClient);

        SimpleInventory inventory = new SimpleInventory(new ItemStack(blockState.getBlock().asItem()));
        Optional<LightningRecipe> match = world.getRecipeManager().getFirstMatch(LightningRecipe.Type.INSTANCE, inventory, world);
        System.out.println("4");

        if (match.isPresent()) {
            LightningRecipes.debugMsg("Found recipe for " + blockState.getBlock().toString());
            ItemStack outputStack = match.get().getOutput();
            Block blockResult = Block.getBlockFromItem(outputStack.getItem());
            world.setBlockState(blockPos, blockResult.getDefaultState());
            System.out.println("5");
        } else {
            LightningRecipes.debugMsg("No recipe found for " + blockState.getBlock().toString());
            System.out.println("6");
        }

    }
}
