package com.c1ok.cobbledialogue.cobbledialogue.client

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

class CobbledialogueDataGenerator : DataGeneratorEntrypoint {

    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        val pack = fabricDataGenerator.createPack()
    }

}
