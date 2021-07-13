package net.lightning_recipes.mixins;

import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LightningEntity.class)
public interface LightningEntityInvoker {
    @Invoker("getAffectedBlockPos")
    public BlockPos invokedGetAffectedBlockPos();
}
