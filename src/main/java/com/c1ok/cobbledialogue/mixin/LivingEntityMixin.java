package com.c1ok.cobbledialogue.mixin;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerStatusPacketListenerImpl;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    //FIXME
    @Inject(method = "handleHandSwap",at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerChunkCache;broadcast(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/network/protocol/Packet;)V"),locals = LocalCapture.CAPTURE_FAILSOFT,cancellable = true)
    public void onSwap(Map<EquipmentSlot, ItemStack> map, CallbackInfo ci, ItemStack itemStack, ItemStack itemStack2){
        LivingEntity livingEntity = (LivingEntity)(Object)this;
        if(livingEntity instanceof ServerPlayer serverPlayer) {
            System.out.println(itemStack+":"+itemStack2);
        }
    }
}
