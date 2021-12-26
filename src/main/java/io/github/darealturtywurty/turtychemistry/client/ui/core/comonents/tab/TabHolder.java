package io.github.darealturtywurty.turtychemistry.client.ui.core.comonents.tab;

import io.github.darealturtywurty.turtychemistry.client.ui.core.ContainerMachineScreen;

public interface TabHolder<Type extends Tab> {
    TabPage[] getPages();

    ContainerMachineScreen<?> getScreen();

    Type getSelectedTab();

    Type[] getTabs();

    void setSelectedTab(Type tab);
}
