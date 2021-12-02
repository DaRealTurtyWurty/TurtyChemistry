package io.github.darealturtywurty.turtychemistry.client.ui.core;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public abstract class ContainerMachineScreen<Type extends AbstractContainerMenu>
        extends AbstractContainerScreen<Type> {
    public ContainerMachineScreen(Type container, Inventory playerInv, Component title) {
        super(container, playerInv, title);
        this.leftPos = 0;
        this.topPos = 0;
    }
}
