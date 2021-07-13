package net.lightning_recipes.lib;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LightningRecipeOld {
    private Block inputBlock = null;
    private Tag<Block> inputTag = null;
    private final Block resultBlock;

    public LightningRecipeOld(Tag<Block> tag, Block resultBlock) {
        this.inputTag = tag;
        this.resultBlock = resultBlock;
    }

    public LightningRecipeOld(Block inputBlock, Block resultBlock) {
        this.inputBlock = inputBlock;
        this.resultBlock = resultBlock;
    }

    public boolean matchesInput(Block block) {
        if (this.inputTag == null) {
            return block.toString().equals(this.inputBlock.toString());
        } else {
            return block.getDefaultState().isIn(this.inputTag);
        }
    }

    public void makeRecipe(BlockState blockState, BlockPos blockPos, World world) {
        if (matchesInput(blockState.getBlock())) {
            world.setBlockState(blockPos, inputBlock.getDefaultState());
        }
    }

    public Block getInputBlock() {
        return inputBlock;
    }

    public Tag<Block> getInputTag() {
        return inputTag;
    }

    public Block getResultBlock() {
        return resultBlock;
    }

}
