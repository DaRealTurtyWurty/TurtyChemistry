package io.github.darealturtywurty.turtychemistry.common.item;

import io.github.darealturtywurty.turtychemistry.common.block.entity.AnvilBlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;

public final class BasicHammer extends ChemistryItem {
    public BasicHammer(Builder builder) {
        super(builder);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().getBlockEntity(pContext.getClickedPos()) instanceof
                AnvilBlockEntity alternateAnvil && pContext.getPlayer().isCrouching()) {
            alternateAnvil.smithItem();
        }
        return super.useOn(pContext);
    }
}
