package net.jam.gravesmash.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

public abstract class GourdBlock extends Block {
    public GourdBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    public abstract net.jam.gravesmash.block.StemBlock getStem();

    public abstract net.jam.gravesmash.block.AttachedStemBlock getAttachedStem();
}
