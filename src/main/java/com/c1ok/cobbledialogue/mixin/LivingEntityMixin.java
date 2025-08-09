package com.c1ok.cobbledialogue.mixin;

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueManager;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class LivingEntityMixin {
    //FIXME
    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void onSendChatMessageHead(OutgoingChatMessage outgoingChatMessage, boolean bl, ChatType.Bound bound, CallbackInfo ci) {
        ServerPlayer player = (ServerPlayer)(Object)this;
        System.out.println(outgoingChatMessage.content().getString());
        if (DialogueManager.INSTANCE.getActiveSessions().containsKey(player.getUUID())) {
            ci.cancel();
            DialogueManager.INSTANCE.selectOption(player.getUUID(), Integer.parseInt(outgoingChatMessage.content().getString()));
        }
    }
}
