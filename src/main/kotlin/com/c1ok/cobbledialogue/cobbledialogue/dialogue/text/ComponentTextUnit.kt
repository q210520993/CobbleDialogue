package com.c1ok.cobbledialogue.cobbledialogue.dialogue.text

import net.minecraft.network.chat.Component

class ComponentTextUnit constructor(val component: Component):TextUnit {
    override fun getString(): String {
        return component.string
    }
}