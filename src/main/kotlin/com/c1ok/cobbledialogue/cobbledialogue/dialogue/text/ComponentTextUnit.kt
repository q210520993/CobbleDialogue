package com.c1ok.cobbledialogue.cobbledialogue.dialogue.text

import net.minecraft.network.chat.Component

class ComponentTextUnit constructor(component: Component):TextUnit {
    val component = component;
    override fun getString(): String {
        return component.getString()
    }
}